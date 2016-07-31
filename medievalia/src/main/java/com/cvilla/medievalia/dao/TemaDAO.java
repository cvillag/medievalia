package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.ITemaDAO;
import com.cvilla.medievalia.dao.mappers.TemaGrupoMapper;
import com.cvilla.medievalia.dao.mappers.TemaMapper;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;
import com.cvilla.medievalia.domain.TemaGrupo;
import com.cvilla.medievalia.domain.User;

public class TemaDAO implements ITemaDAO {

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
	
	private static String GET_TEMA_BY_ID = "select idTema, nombre from tema where idTema = ?";
	private static String GET_TEMA_BY_GROUP = "select sel2.idTema, idGroup, nombreGrupo, tema.nombre as nombreTema from tema right join (select sel1.idTema, sel1.idGroup, groups.name as nombreGrupo from groups right join (select idTema, idGroup from temaGrupo where idGroup = ?) as sel1 on groups.idGroup = sel1.idGroup) as sel2 on sel2.idTema = tema.idTema";
	
	public Tema getTemaById(int id) {
		try{
			return jdbcTemplate.queryForObject(GET_TEMA_BY_ID,new Object[]{id}, new TemaMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public List<TemaGrupo> getTemaListByGroup(Group g) {
		try{
			return jdbcTemplate.query(GET_TEMA_BY_GROUP, new Object[]{g.getIdGrupo()},new TemaGrupoMapper() );
		}
		catch(Exception e){
			return null;
		}
	}
}
