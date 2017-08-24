package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IPersonageDAO;
import com.cvilla.medievalia.dao.mappers.CharacterMapper;
import com.cvilla.medievalia.domain.Personage;

public class PersonageDAO implements IPersonageDAO {
	
	private final String GET_PERSONAGE_LIST = "SELECT  `idPersonaje` ,  `idGrupo` ,  `creador` ,  `user_name` AS  `nombreCreador` ,  `nombre` ,  `otros` ,  `validado` ,  `lugarNacimiento` ,  `lugarFallecimiento` ,  `anacimiento` ,  `mnacimiento` , `dnacimiento` ,  `afallecimiento` ,  `mfallecimiento` ,  `dfallecimiento` FROM  `personaje` LEFT JOIN users ON user_id = creador";

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
	
	public List<Personage> getPersonageList() {
		try{
			List<Personage> l = jdbcTemplate.query(GET_PERSONAGE_LIST, new CharacterMapper());
			return l;
		}
		catch(Exception e){
			return null;
		}
	}

	public Personage getPersonage(int idPersonage) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPersonage(Personage p) {
		// TODO Auto-generated method stub

	}

}
