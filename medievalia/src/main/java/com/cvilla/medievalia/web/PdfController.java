package com.cvilla.medievalia.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cvilla.medievalia.dao.ObjetoDAO;
import com.cvilla.medievalia.domain.InstanciaAtributoComplejo;
import com.cvilla.medievalia.domain.InstanciaAtributoSencillo;
import com.cvilla.medievalia.domain.InstanciaObjeto;
import com.cvilla.medievalia.domain.TipoAtributoComplejo;
import com.cvilla.medievalia.domain.TipoObjeto;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.service.intf.IObjectManager;
import com.cvilla.medievalia.utils.Constants;
 
/**
 * A Spring controller that allows the users to download a PDF document
 * generated by the iText library.
 *
 * @author www.codejava.net
 *
 */

@Controller
public class PdfController {
 
	@Autowired
	private IObjectManager objectManager;

    @RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadExcel(HttpServletRequest request) {
        // create some sample data
        
        HttpSession sesion = request.getSession();
        @SuppressWarnings("unchecked")
        List<InstanciaObjeto> listag = (List<InstanciaObjeto>) sesion.getAttribute("listainforme");
        TipoObjeto tipo = (TipoObjeto) sesion.getAttribute("tipoObjeto");
        List<InstanciaObjeto> listaComp = new ArrayList<InstanciaObjeto>();
		for(InstanciaObjeto io : listag){
			InstanciaObjeto ioc = objectManager.getObjetoDOM(tipo, io.getIdInstancia());
			fillNames(ioc);
			listaComp.add(ioc);
		}
		
		sesion.removeAttribute("listainforme");
		sesion.setAttribute("listainforme",listaComp);
 
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("pdfView", "listBooks", listag);
    }
    
    private void fillNames(InstanciaObjeto o){
    	for(InstanciaAtributoComplejo iao : o.getAtributosComplejos()){
    		fillNames(iao);
    	}
    }

    private void fillNames(InstanciaAtributoComplejo iao){
    	if(iao.getInstanciaHijo().getTipo().getTipoDOM() == Constants.OBJETO_CNC){
    		InstanciaObjeto n2 = objectManager.getObjetoDOM(iao.getInstanciaHijo().getTipo(), iao.getInstanciaHijo().getNombre());
    		
    		String sub = fill(n2);
    		iao.getInstanciaHijo().setNombre(iao.getInstanciaHijo().getNombre() + sub);
    	}
    	TipoObjeto t = objectManager.getTipoObjetoDOM(iao.getIdTipoObjetoRelacion());
    	if(t != null){
			InstanciaObjeto n3 = objectManager.getObjetoDOM(t, iao.getInstanciaObjetoRelacion().getIdInstancia());
			String sub = fill(n3);
			iao.getInstanciaObjetoRelacion().setNombre(iao.getInstanciaObjetoRelacion().getNombre()+sub);
    	}
    }
    
    private String fill(InstanciaObjeto x){
    	String sub = "";
    	if(x != null){
			for(InstanciaAtributoSencillo ias : x.getAtributosSencillos()){
				if(ias.getIdAtributo() == Constants.OBJETO_ATT_CNC && ias.getValor() != null){
					sub = " ," + ((InstanciaObjeto)ias.getValor()).getNombre();
				}
			}
		}
    	return sub;
    }
}


