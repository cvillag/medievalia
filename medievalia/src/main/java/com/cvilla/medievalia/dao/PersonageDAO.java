package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IPersonageDAO;
import com.cvilla.medievalia.dao.mappers.CharacterMapper;
import com.cvilla.medievalia.domain.Personage;

public class PersonageDAO implements IPersonageDAO {
	
	private static final String DELETE_CHARACTER = "DELETE FROM `personaje` WHERE idPersonaje = ?";
	private static final String GET_PERSONAGE_BY_ID = "SELECT `idPersonaje` ,  `idGrupo` ,  `creador` ,  `user_name` AS  `nombreCreador` ,  `nombre` ,  `otros` ,  `validado` ,  `lugarNacimiento` ,  `lugarFallecimiento` ,  `anacimiento` ,  `mnacimiento` , `dnacimiento` ,  `afallecimiento` ,  `mfallecimiento` ,  `dfallecimiento` FROM `personaje` LEFT JOIN users ON user_id = creador WHERE idPersonaje = ? ";
	private final String GET_PERSONAGE_LIST = "SELECT  `idPersonaje` ,  `idGrupo` ,  `creador` ,  `user_name` AS  `nombreCreador` ,  `nombre` ,  `otros` ,  `validado` ,  `lugarNacimiento` ,  `lugarFallecimiento` ,  `anacimiento` ,  `mnacimiento` , `dnacimiento` ,  `afallecimiento` ,  `mfallecimiento` ,  `dfallecimiento` FROM  `personaje` LEFT JOIN users ON user_id = creador";
	private final String ADD_CHARACTER = "INSERT INTO `personaje`(`idGrupo`, `creador`, `nombre`, `otros`, `validado`, `lugarNacimiento`, `lugarFallecimiento`, `anacimiento`, `mnacimiento`, `dnacimiento`, `afallecimiento`, `mfallecimiento`, `dfallecimiento`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String NAME_REPEAT = "select count(*) from personaje where nombre = ?";
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
		try{
			return getJdbcTemplate().queryForObject(GET_PERSONAGE_BY_ID,new Object[]{idPersonage}, new CharacterMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String addPersonage(Personage p) {
		try{
			int num = jdbcTemplate.update(ADD_CHARACTER,new Object[]{p.getIdGrupo(),p.getCreador(),p.getNombre(),p.getOtros(),p.getValidado(),null,null,p.getAnacimiento(),p.getMnacimiento(),p.getDnacimiento(),p.getAfallecimiento(),p.getMfallecimiento(),p.getDfallecimiento()});
			if(num == 1)
				return "creado";
			else{
				return "noCreado";
			}
		}
		catch(Exception e){
			return "noCreado";
		}

	}
	
	public boolean nameRepeat(String name){
		try{
			return jdbcTemplate.queryForInt(NAME_REPEAT, name) == 1;
		}
		catch(Exception e){
			return true;
		}
	}

	public String deleteCharacter(Personage c) {
		try{
			if(jdbcTemplate.update(DELETE_CHARACTER, new Object[]{c.getIdPersonaje()}) == 1){
				return "borrado";
			}
			else{
				return "error";
			}
		}
		catch(Exception e){
			return "error"; 
		}
	}

}
