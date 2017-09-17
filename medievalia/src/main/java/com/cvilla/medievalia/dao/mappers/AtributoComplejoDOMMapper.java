package com.cvilla.medievalia.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cvilla.medievalia.domain.AtributoComplejoDOM;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;

public class AtributoComplejoDOMMapper implements RowMapper<AtributoComplejoDOM>{
	
	public AtributoComplejoDOM mapRow(ResultSet rs, int rowNum) throws SQLException {
		AtributoComplejoDOM a = new AtributoComplejoDOM();
		TipoObjetoDOM tipoPadre = new TipoObjetoDOM();
		tipoPadre.setTipoDOM(rs.getInt("idObjetoPadre"));
		tipoPadre.setNombreDOM(rs.getString("nombreObjetoPadre"));
		a.setTipoPadre(tipoPadre);
		TipoObjetoDOM tipoHijo = new TipoObjetoDOM();
		tipoHijo.setTipoDOM(rs.getInt("idObjetoHijo"));
		tipoHijo.setNombreDOM(rs.getString("nombreObjetoHijo"));
		a.setTipoHijo(tipoHijo);
		ObjetoDOM iHijo = new ObjetoDOM();
		iHijo.setIdInstancia(rs.getInt("idInstanciaHijo"));
		iHijo.setTipo(tipoPadre);
		iHijo.setNombre(rs.getString("nombreObjetoHijo"));
		iHijo.setValidado(rs.getInt("validadoHijo"));
		iHijo.setTextoValidacion(rs.getString("textoValidacionHijo"));
		iHijo.setGrupo(rs.getInt("idGrupoHijo"));
		iHijo.setCreador(rs.getInt("creadorHijo"));
		a.setInstanciaHijo(iHijo);
		a.setNombreAtributo(rs.getString("nombreAtributo"));
		a.setCreador(rs.getInt("creadorPadre"));
		a.setIdGrupo(rs.getInt("idGrupoPadre"));
		a.setTextoValidacion(rs.getString("textoValidacionHijo"));
		a.setValidado(rs.getInt("validadoPadre"));
		return a;
	}
}
