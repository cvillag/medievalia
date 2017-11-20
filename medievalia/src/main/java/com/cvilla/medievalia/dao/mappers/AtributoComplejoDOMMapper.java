package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Fechas;
import com.cvilla.medievalia.utils.SpecialDate;

public class AtributoComplejoDOMMapper implements RowMapper<InstanciaAtributoComplejoDOM>{
	
	public InstanciaAtributoComplejoDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		InstanciaAtributoComplejoDOM a = new InstanciaAtributoComplejoDOM();
		TipoObjetoDOM tipoPadre = new TipoObjetoDOM();
		tipoPadre.setTipoDOM(rs.getInt("idObjetoPadre"));
		tipoPadre.setNombreDOM(rs.getString("nombreObjetoPadre"));
		a.setTipoPadre(tipoPadre);
		TipoObjetoDOM tipoHijo = new TipoObjetoDOM();
		tipoHijo.setTipoDOM(rs.getInt("idObjetoHijo"));
		tipoHijo.setNombreDOM(rs.getString("nombreObjetoHijo"));
		a.setTipoHijo(tipoHijo);
		InstanciaObjetoDOM iHijo = new InstanciaObjetoDOM();
		iHijo.setIdInstancia(rs.getInt("idInstanciaHijo"));
		iHijo.setTipo(tipoPadre);
		iHijo.setNombre(rs.getString("nombreObjetoHijo"));
		iHijo.setValidado(rs.getInt("validadoHijo"));
		iHijo.setTextoValidacion(rs.getString("textoValidacionHijo"));
		iHijo.setGrupo(rs.getInt("idGrupoHijo"));
		User u = new User();
		u.setId(rs.getInt("creadorHijo"));
		iHijo.setCreador(u);
		a.setInstanciaHijo(iHijo);
		a.setNombreAtributo(rs.getString("nombreAtributo"));
		a.setCreador(rs.getInt("creadorPadre"));
		a.setIdGrupo(rs.getInt("idGrupoPadre"));
		a.setTextoValidacion(rs.getString("textoValidacionPadre"));
		a.setValidado(rs.getInt("validadoPadre"));
		a.setTextoLeido(rs.getInt("textoLeidoAC"));
		a.setIdTipoObjetoRelacion(rs.getInt("idObjetoRelacion"));
		InstanciaObjetoDOM irel = new InstanciaObjetoDOM();
		irel.setIdInstancia(rs.getInt("idInstanciaRelacion"));
		irel.setTipo(new TipoObjetoDOM(rs.getInt("idObjetoRelacion"),rs.getString("nombreObjeto")));
		irel.setNombre(rs.getString("nombreInstancia"));
		a.setInstanciaObjetoRelacion(irel);
		
		a.setConFecha(rs.getInt("conFecha"));
		if(a.getConFecha() == 1){
			SpecialDate si = new SpecialDate();
			si.setDia(rs.getInt("diaI"));
			si.setMes(rs.getInt("mesI"));
			si.setAnio(rs.getInt("anioI"));
			SpecialDate sf = new SpecialDate();
			sf.setDia(rs.getInt("diaF"));
			sf.setMes(rs.getInt("mesF"));
			sf.setAnio(rs.getInt("anioF"));
			if(Fechas.fechaIncorrecta(si)){
				a.setFechaInicio(null);
			}
			else{
				a.setFechaInicio(si);
			}
			if(Fechas.fechaIncorrecta(sf)){
				a.setFechaFin(null);
			}
			else{
				a.setFechaFin(sf);
			}
		}
		
		return a;
	}
}
