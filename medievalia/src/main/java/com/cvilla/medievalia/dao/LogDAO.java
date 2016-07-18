package com.cvilla.medievalia.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.ILogDAO;
import com.cvilla.medievalia.dao.mappers.LogMapper;
import com.cvilla.medievalia.domain.Log;
import com.cvilla.medievalia.service.LogManager;

public class LogDAO implements ILogDAO {

	private static final String INSERT_LOG = "INSERT INTO `log`(`idUser`, `idAction`, `time`, `description`, `success`) VALUES (?,?,NOW(),?,?)";
	private static final String NUM_LOG_PER_USER = "select count(*) from log where idUser = ?";
	private static final String PAGED_ACTION_LIST_ASC = "select idUser,time,idLog,idAction,description,success,actionName, user_long_name as userLongName, user_name as userName from users right join (SELECT `idUser`, `time`, action.idAction, `idLog`, `description`, `success`, action_name as `actionName`  FROM `log` left join `action` on `action`.idAction = `log`.idAction WHERE	idUser = ? order by idLog asc limit ?,?) as sel1 on users.user_id = sel1.idUser";
	private static final String PAGED_ACTION_LIST_DESC = "select idUser,time,idLog,idAction,description,success,actionName, user_long_name as userLongName, user_name as userName from users right join (SELECT `idUser`, `time`, action.idAction, `idLog`, `description`, `success`, action_name as `actionName`  FROM `log` left join `action` on `action`.idAction = `log`.idAction WHERE	idUser = ? order by idLog desc limit ?,?) as sel1 on users.user_id = sel1.idUser";
	
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
	
	public boolean log(int idUser, int idAction, String desc, int succ) {
		try{
			return jdbcTemplate.update(INSERT_LOG,new Object[]{idUser,idAction,desc,succ}) == 1;
		}
		catch(Exception e){
			return false;
		}
	}

	public Log getLogById(int id) {
		// TODO : Auto
		return null;
	}

	public List<Log> getLogByUser(int idUser, int pag, int tamPag, boolean order) {
		try{
			int off = (pag-1) * tamPag;
			if(order){
				return jdbcTemplate.query(PAGED_ACTION_LIST_ASC, new Object[]{idUser,off,tamPag}, new LogMapper());
			}
			else{
				return jdbcTemplate.query(PAGED_ACTION_LIST_DESC, new Object[]{idUser,off,tamPag}, new LogMapper());
			}
		}
		catch(Exception e){
			
		}
		return null;
	}
	
	public int getNumLogByUser(int idUser){
		try{
			return jdbcTemplate.queryForInt(NUM_LOG_PER_USER, new Object[]{idUser});
		}
		catch(Exception e){
			return 0;
		}
	}

	public Log getLogByDate(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

	public Log getLogByDateRange(Date d1, Date d2) {
		// TODO Auto
		return null;
	}

}
