package com.cvilla.medievalia.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejo;
import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PdfBuilder extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        @SuppressWarnings("unchecked")
        HttpSession sesion = request.getSession();
        User user = (User) sesion.getAttribute("user");
        TipoObjeto tipo = (TipoObjeto) sesion.getAttribute("tipoObjeto");
        List<TipoAtributoComplejo> ac = (List<TipoAtributoComplejo>) sesion.getAttribute("tiposacinforme");
        @SuppressWarnings("unchecked")
		List<InstanciaObjeto> listObj = (List<InstanciaObjeto>) sesion.getAttribute("listainforme");
         
        doc.add(new Paragraph("Informe de " + tipo.getNombreDOM() + " creado por " + user.getUser_long_name()));
         
        for(InstanciaObjeto io : listObj){
	        PdfPTable table = new PdfPTable(4);
	        table.setWidthPercentage(100.0f);
	        table.setWidths(new float[] {2.0f, 3.0f, 3.0f, 1.0f});
	        table.setSpacingBefore(10);
	         
	        // define font for table header row
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(BaseColor.WHITE);
	         
	        // define table header cell
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(BaseColor.BLUE);
	        cell.setPadding(5);
	         
	        // write table header
	        cell.setPhrase(new Phrase(io.getNombre(), font));
	        cell.setColspan(4);
	        table.addCell(cell);
	         
	        PdfPCell cas = new PdfPCell();
	        cas.setPadding(5);
	        cas.setColspan(2);
	        
	        for(InstanciaAtributoSencillo ia : io.getAtributosSencillos()){
	        	cas.setPhrase(new Phrase(ia.getNombreTipoAtributo()));
	        	table.addCell(cas);
	        	cas.setPhrase(new Phrase(ia.toString()));
	        	table.addCell(cas);
	        }
	        for(TipoAtributoComplejo ta : ac){
	        	PdfPCell celtipoAC = new PdfPCell();
	        	celtipoAC.setPadding(5);
	        	celtipoAC.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        	List<InstanciaAtributoComplejo> la = getACinTAC(ta, io.getAtributosComplejos());
	        	celtipoAC.setRowspan(la.size()+1);
	        	celtipoAC.setPhrase(new Phrase(ta.getNombreAtributo()));
	        	table.addCell(celtipoAC);
	        	PdfPCell head = new PdfPCell();
	        	head.setPadding(5);
	        	head.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        	head.setPhrase(new Phrase(" "));
	        	table.addCell(head);
	        	head.setPhrase(new Phrase("Documento"));
	        	table.addCell(head);
	        	head.setPhrase(new Phrase("Página"));
	        	table.addCell(head);
	        	for(InstanciaAtributoComplejo a : la){
	        		PdfPCell c = new PdfPCell();
	        		c.setPadding(5);
	        		String n = a.getInstanciaHijo().getNombre();
	        		if(a.hasDate()){
	        			
	        			SpecialDate si = a.getFechaInicio();
	        			SpecialDate sf = a.getFechaFin();
	        			if(si != null){
	        				n += ". Desde " + si.toString();
	        			}
	        			if(sf != null){
	        				n += ". Hasta " + sf.toString();
	        			}
	        			
	        		}
	        		c.setPhrase(new Phrase(n));
	        		table.addCell(c);
	        		if(a.getInstanciaObjetoRelacion() != null){
	        			c.setPhrase(new Phrase(a.getInstanciaObjetoRelacion().getNombre()));
	        		}
	        		table.addCell(c);
	        		if(a.isConPagina()){
	        			c.setPhrase(new Phrase(a.getPaginaDoc()));
	        		}
	        		else{
	        			c.setPhrase(new Phrase(" "));
	        		}
	        		table.addCell(c);
	        		
	        		
	        	}
	        }
	        table.setKeepTogether(true);
	        doc.add(table);
        }
         
    }
    
    private List<InstanciaAtributoComplejo> getACinTAC(TipoAtributoComplejo tac, List<InstanciaAtributoComplejo> lac){
    	List<InstanciaAtributoComplejo> lista = new ArrayList<InstanciaAtributoComplejo>();
    	for(InstanciaAtributoComplejo ac : lac){
    		if(ac.getTipoHijo().getTipoDOM() == tac.getIdTipoHijo()){
    			lista.add(ac);
    		}
    	}
    	return lista;
    }
 
}
