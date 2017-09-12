package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IPersonageDAO;
import com.cvilla.medievalia.dao.mappers.CharacterMapper;
import com.cvilla.medievalia.dao.mappers.ChargeMapper;
import com.cvilla.medievalia.domain.Charge;
import com.cvilla.medievalia.domain.Personage;
import com.cvilla.medievalia.utils.Constants;

public class PersonageDAO implements IPersonageDAO {
	
	private final String UPDATE_PERSONAGE = "UPDATE `personaje` SET `otros`=?,`anacimiento`=?,`mnacimiento`=?,`dnacimiento`=?,`afallecimiento`=?,`mfallecimiento`=?,`dfallecimiento`=? WHERE idPersonaje = ?";
	private final String DELETE_CHARACTER = "DELETE FROM `personaje` WHERE idPersonaje = ?";
	private final String GET_PERSONAGE_BY_ID = "SELECT `idPersonaje` ,  `idGrupo` ,  `creador` ,  `user_name` AS  `nombreCreador` ,  `nombre` ,  `otros` ,  `validado` ,  `lugarNacimiento` ,  `lugarFallecimiento` ,  `anacimiento` ,  `mnacimiento` , `dnacimiento` ,  `afallecimiento` ,  `mfallecimiento` ,  `dfallecimiento` FROM `personaje` LEFT JOIN users ON user_id = creador WHERE idPersonaje = ? ";
	private final String GET_PERSONAGE_BY_NAME = "SELECT `idPersonaje` ,  `idGrupo` ,  `creador` ,  `user_name` AS  `nombreCreador` ,  `nombre` ,  `otros` ,  `validado` ,  `lugarNacimiento` ,  `lugarFallecimiento` ,  `anacimiento` ,  `mnacimiento` , `dnacimiento` ,  `afallecimiento` ,  `mfallecimiento` ,  `dfallecimiento` FROM `personaje` LEFT JOIN users ON user_id = creador WHERE nombre = ? ";
	private final String GET_PERSONAGE_LIST = "SELECT  `idPersonaje` ,  `idGrupo` ,  `creador` ,  `user_name` AS  `nombreCreador` ,  `nombre` ,  `otros` ,  `validado` ,  `lugarNacimiento` ,  `lugarFallecimiento` ,  `anacimiento` ,  `mnacimiento` , `dnacimiento` ,  `afallecimiento` ,  `mfallecimiento` ,  `dfallecimiento` FROM  `personaje` LEFT JOIN users ON user_id = creador";
	private final String ADD_CHARACTER = "INSERT INTO `personaje`(`idGrupo`, `creador`, `nombre`, `otros`, `validado`, `lugarNacimiento`, `lugarFallecimiento`, `anacimiento`, `mnacimiento`, `dnacimiento`, `afallecimiento`, `mfallecimiento`, `dfallecimiento`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String NAME_REPEAT = "select count(*) from personaje where nombre = ?";
	private final String RENAME_PERSONAGE = "UPDATE `personaje` SET `nombre`= ? WHERE idPersonaje = ?";
	private static final String GET_CHARGES_NOT_IN_PERSONAGE = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado`, null as `nameCreator` FROM cargo where idCargo not in (select idCargo from rel_personaje_cargo where idPersonaje = ? and validado = ?) and validado = ?";
	private static final String GET_CHARGES_IN_PERSONAGE = "SELECT `idCargo`, `idGroup`, `nombre`, `creador`, `validado`, null as `nameCreator` FROM cargo where idCargo in (select idCargo from rel_personaje_cargo where idPersonaje = ? and validado = ?) and validado = ?";
	
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

	public Personage getPersonage(String name) {
		try{
			return getJdbcTemplate().queryForObject(GET_PERSONAGE_BY_NAME,new Object[]{name}, new CharacterMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String renamePersonage(int idPers, String nombre) {
		try{
			if(jdbcTemplate.update(RENAME_PERSONAGE, new Object[]{nombre,idPers}) == 1){
				return "cambiado";
			}
			else{
				return "error";
			}
		}
		catch(Exception e){
			return "error"; 
		}
	}

	public String updatePersonage(Personage p, int idPersonaje) {
		try{
			if(getJdbcTemplate().update(UPDATE_PERSONAGE, new Object[]{p.getOtros(),p.getAnacimiento(),p.getMnacimiento(),p.getDnacimiento(),p.getAfallecimiento(),p.getMfallecimiento(),p.getDfallecimiento(),idPersonaje})   	 == 1){
				return "cambiado";
			}
			else{
				return "error";
			}
		}
		catch(Exception e){
			return "error";
		}
	}
	
	public List<Charge> getChargeListNotInPersonage(int idPersonaje) {
		try{
			return getJdbcTemplate().query(GET_CHARGES_NOT_IN_PERSONAGE, new Object[]{idPersonaje,Constants.OBJETO_VALIDADO,Constants.OBJETO_VALIDADO},new ChargeMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Charge> getChargeListInPersonage(int idPersonaje) {
		try{
			return getJdbcTemplate().query(GET_CHARGES_IN_PERSONAGE, new Object[]{idPersonaje,Constants.OBJETO_VALIDADO,Constants.OBJETO_VALIDADO},new ChargeMapper());
		}
		catch(Exception e){
			return null;
		}
	}

}
