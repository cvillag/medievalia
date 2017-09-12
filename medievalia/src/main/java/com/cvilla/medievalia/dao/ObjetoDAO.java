package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IObjetoDAO;
import com.cvilla.medievalia.dao.mappers.ObjetoDOMMapper;
import com.cvilla.medievalia.dao.mappers.TipoObjetoDOMMapper;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;


public class ObjetoDAO implements IObjetoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static final String GET_OBJECT_TYPE_LIST = "SELECT `idObjeto`, `nombreObjeto` FROM `ObjetoDOM`";
	private static final String GET_OBJECT_TYPE = "SELECT `idObjeto`, `nombreObjeto` FROM `ObjetoDOM` where idObjeto = ?";
	private static final String GET_OBJECT_INSTANCES_BY_TYPE = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaObjeto` WHERE idObjeto = ?";
	

	public List<TipoObjetoDOM> getObjectTypeList() {
		try{
			return jdbcTemplate.query(GET_OBJECT_TYPE_LIST, new TipoObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public TipoObjetoDOM getObjectType(int idType) {
		try{
			return jdbcTemplate.queryForObject(GET_OBJECT_TYPE, new Object[]{idType}, new TipoObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public List<ObjetoDOM> getObjectListByTipe(TipoObjetoDOM tipo) {
		try{
			return jdbcTemplate.query(GET_OBJECT_INSTANCES_BY_TYPE, new Object[]{tipo.getTipoDOM()}, new ObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

}
