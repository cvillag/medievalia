package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.ITemaDAO;
import com.cvilla.medievalia.dao.mappers.TemaMapper;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.Tema;

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
	
	private static String ADD_TEMA_1 = "insert into tema (nombre,idGrupo) values (?,?)";
	private static String GET_TEMA_BY_ID = "select sel2.idTema, sel2.nombre, idGrupo, nombreGrupo, count(idSubtema) as numSubtemas from  (select idTema, nombre, idGrupo, name as nombreGrupo from (select idTema, nombre, idGrupo from tema where idTema = ?) as sel1 left join groups on groups.idGroup = sel1.idGrupo) as sel2 left join subtema on subtema.idTema = sel2.idTema";
	private static String GET_TEMA_BY_NAME = "select  sel2.idTema, sel2.nombre, idGrupo, nombreGrupo, count(idSubtema) as numSubtemas from (select idTema, nombre, idGrupo, name as nombreGrupo from (select idTema, nombre, idGrupo from tema where nombre = ? and idGrupo = ?) as sel1 left join groups on groups.idGroup = sel1.idGrupo) as sel2 left join subtema on subtema.idTema = sel2.idTema";
	private static String GET_TEMA_LIST_BY_GROUP = "select sel2.idTema, sel2.nombre, idGrupo, nombreGrupo, count(idSubtema) as numSubtemas from (select idTema, nombre, idGrupo, name as nombreGrupo from (select idTema, nombre, idGrupo from tema where idGrupo = ?) as sel1 left join groups on groups.idGroup = sel1.idGrupo) as sel2 left join subtema on sel2.idTema = subtema.idTema group by idTema";
	
	public Tema getTemaById(int id) {
		try{
			return jdbcTemplate.queryForObject(GET_TEMA_BY_ID,new Object[]{id}, new TemaMapper());
		}
		catch(Exception e){
			return null;
		}
	}
	
	public Tema getTemaByName(String name, Group g) {
		try{
			return jdbcTemplate.queryForObject(GET_TEMA_BY_NAME,new Object[]{name,g.getIdGrupo()}, new TemaMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public List<Tema> getTemaListByGroup(Group g) {
		try{
			return jdbcTemplate.query(GET_TEMA_LIST_BY_GROUP, new Object[]{g.getIdGrupo()},new TemaMapper() );
		}
		catch(Exception e){
			return null;
		}
	}

	public String createTopic(String name, Group group) {
		try{
			int num = jdbcTemplate.update(ADD_TEMA_1,new Object[]{name,group.getIdGrupo()});
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
}
