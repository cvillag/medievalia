package com.cvilla.medievalia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cvilla.medievalia.dao.intfc.IObjetoDAO;
import com.cvilla.medievalia.dao.mappers.AtributoComplejoDOMMapper;
import com.cvilla.medievalia.dao.mappers.AtributoSencilloDOMMapper;
import com.cvilla.medievalia.dao.mappers.DoubleDOMMapper;
import com.cvilla.medievalia.dao.mappers.ObjetoDOMMapper;
import com.cvilla.medievalia.dao.mappers.SpecialDateDOMMapper;
import com.cvilla.medievalia.dao.mappers.StringDOMMapper;
import com.cvilla.medievalia.dao.mappers.TipoAtributoComplejoDOMMapper;
import com.cvilla.medievalia.dao.mappers.TipoObjetoDOMMapper;
import com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM;
import com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM;
import com.cvilla.medievalia.domain.Group;
import com.cvilla.medievalia.domain.InstanciaObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
import com.cvilla.medievalia.domain.User;
import com.cvilla.medievalia.utils.Constants;
import com.cvilla.medievalia.utils.SpecialDate;


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
	private static final String GET_OBJECT_INSTANCES_BY_TYPE = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` WHERE idObjeto = ? and validado = ?";
	private static final String GET_OBJECT_INSTANCES_BY_TYPE_GROUP = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` WHERE idObjeto = ? and validado = ? and idGrupo = ?";
	private static final String GET_OBJECT_INSTANCE = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` WHERE idInstancia = ? and idObjeto = ? and validado = ?";
	private static final String GET_OBJECT_S_ATTRIBUTES_TYPE_LIST = "SELECT `idAtributo`, `idObjeto`, `nombreAtributo`, `tipo`, `subtipo` FROM `AtributoSencilloObjeto` WHERE idObjeto = ?";
	private static final String GET_OBJECT_DATE_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `dia`, `mes`, `anio` FROM `InstanciaAtributoDate` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_DOUBLE_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor` FROM `InstanciaAtributoDouble` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_INT_ATTRIBUTES_VALUE = "SELECT `valor` FROM `InstanciaAtributoInt` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_STRING_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor` FROM `InstanciaAtributoString` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_TEXT_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor` FROM `InstanciaAtributoText` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_COMPLEX_ATTRIBUTES = "select `idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validadoPadre`, `textoValidacionHijo`, `idGrupoPadre`, `creadorPadre`, `textoLeidoAC`, `nombreAtributo`, `nombreObjetoHijo`, `validadoHijo`, `textoValidacionPadre`, `idGrupoHijo`, `creadorHijo`, `idObjetoRelacion`,`idInstanciaRelacion`, `nombreObjeto`, `nombreInstancia` from( "
			+ "select `idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validadoPadre`, `textoValidacionHijo`, `idGrupoPadre`, `creadorPadre`, `textoLeidoAC`, `nombreAtributo`, `nombreObjetoHijo`, `validadoHijo`, `textoValidacionPadre`, `idGrupoHijo`, `creadorHijo`, `idObjetoRelacion`,`idInstanciaRelacion`, `nombreObjeto` from( "
				+"select `idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, sel2.`validado` as `validadoPadre`, sel2.`textoValidacion` as `textoValidacionHijo`, 			sel2.`idGrupo` as `idGrupoPadre`, sel2.`creador` as `creadorPadre`, sel2.`textoLeidoAC`, `nombreAtributo`, `nombreInstancia` as `nombreObjetoHijo`, InstanciaObjeto.validado as `validadoHijo`, 	InstanciaObjeto.textoValidacion as `textoValidacionPadre`, InstanciaObjeto.idGrupo as `idGrupoHijo`, InstanciaObjeto.creador as `creadorHijo`, sel2.`idObjetoRelacion`,sel2.`idInstanciaRelacion` from "
					+"(select sel1.`idObjetoPadre` as `idObjetoPadre`, `nombreObjetoPadre`, sel1.`idObjetoHijo` as `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, 			`creador`, `textoLeido` as `textoLeidoAC`, `nombreAtributo`, sel1.`idObjetoRelacion`,sel1.`idInstanciaRelacion` from "
						+ "(SELECT `idObjetoPadre`, nombreObjeto as `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido`, `idObjetoRelacion`,`idInstanciaRelacion` FROM `InstanciaAtributoComplejo` "
						+ "left join ObjetoDOM on idObjetoPadre = idObjeto WHERE idObjetoPadre = ? and idInstanciaPadre = ?) as sel1 "
					+ "left join AtributoComplejoObjeto on sel1.idObjetoPadre = AtributoComplejoObjeto.idObjetoPadre and sel1.idObjetoHijo = AtributoComplejoObjeto.idObjetoHijo) as sel2 "
				+"left join InstanciaObjeto on InstanciaObjeto.idInstancia = sel2.idInstanciaHijo and InstanciaObjeto.idObjeto = sel2.idObjetoHijo) as sel3 "
			+ "left join ObjetoDOM on `idObjetoRelacion` = `idObjeto`) as sel4 "
		+ "left join InstanciaObjeto on `idObjeto` = `idObjetoRelacion` and `idInstancia` = `idInstanciaRelacion`";
	private static final String GET_OBJECT_COMPLEX_ATTRIBUTES_STUDENT = GET_OBJECT_COMPLEX_ATTRIBUTES;
	private static final String ADD_OBJECT_INSTANCE = "INSERT INTO `InstanciaObjeto`(`idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido`) VALUES (?,?,?,?,?,?,?,?)";
	private static final String GET_MAX_INSTANCE_ID_BY_OBJECT = "select max(idInstancia) from InstanciaObjeto where idObjeto = ?";
	private static final String GET_OBJECT_INSTANCE_BY_NAME ="SELECT count(*) FROM `InstanciaObjeto` WHERE idObjeto = ? and nombreInstancia = ?";
	private static final String GET_OBJECT_COMPLEX_ATTRIBUTES_TYPES = "SELECT `idObjetoPadre`, `idObjetoHijo`, `NombreAtributo`, `idObjetoRelacion` FROM `AtributoComplejoObjeto` WHERE idObjetoPadre = ?";
	private static final String ADD_COMPLEX_ATTRIBUTE = "INSERT INTO `InstanciaAtributoComplejo`(`idObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`,`idObjetoRelacion`,`idInstanciaRelacion`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_COMPLEX_ATTRIBUTE = "select `idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validadoPadre`, `textoValidacionHijo`, `idGrupoPadre`, `creadorPadre`, `textoLeidoAC`, `nombreAtributo`, `nombreObjetoHijo`, `validadoHijo`, `textoValidacionPadre`, `idGrupoHijo`, `creadorHijo`, `idObjetoRelacion`,`idInstanciaRelacion`, `nombreObjeto`, `nombreInstancia` from( "
		+ "select `idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validadoPadre`, `textoValidacionHijo`, `idGrupoPadre`, `creadorPadre`, `textoLeidoAC`, `nombreAtributo`, `nombreObjetoHijo`, `validadoHijo`, `textoValidacionPadre`, `idGrupoHijo`, `creadorHijo`, `idObjetoRelacion`,`idInstanciaRelacion`, `nombreObjeto` from( "
			+ "select `idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, sel2.`validado` as `validadoPadre`, sel2.`textoValidacion` as `textoValidacionHijo`, 			sel2.`idGrupo` as `idGrupoPadre`, sel2.`creador` as `creadorPadre`, sel2.`textoLeidoAC`, `nombreAtributo`, `nombreInstancia` as `nombreObjetoHijo`, InstanciaObjeto.validado as `validadoHijo`, 	InstanciaObjeto.textoValidacion as `textoValidacionPadre`, InstanciaObjeto.idGrupo as `idGrupoHijo`, InstanciaObjeto.creador as `creadorHijo`, sel2.`idObjetoRelacion`,sel2.`idInstanciaRelacion` from "
				+ "(select sel1.`idObjetoPadre` as `idObjetoPadre`, `nombreObjetoPadre`, sel1.`idObjetoHijo` as `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, 			`creador`, `textoLeido` as `textoLeidoAC`, `nombreAtributo`, sel1.`idObjetoRelacion`,sel1.`idInstanciaRelacion` from "
					+ "(SELECT `idObjetoPadre`, nombreObjeto as `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido`, `idObjetoRelacion`,`idInstanciaRelacion` FROM `InstanciaAtributoComplejo` "
					+ "left join ObjetoDOM on idObjetoPadre = idObjeto WHERE idObjetoPadre = ? and idInstanciaPadre = ? and idObjetoHijo = ? and idInstanciaHijo = ? and validado = ?) as sel1 "
				+ "left join AtributoComplejoObjeto on sel1.idObjetoPadre = AtributoComplejoObjeto.idObjetoPadre and sel1.idObjetoHijo = AtributoComplejoObjeto.idObjetoHijo) as sel2 "
			+ "left join InstanciaObjeto on InstanciaObjeto.idInstancia = sel2.idInstanciaHijo and InstanciaObjeto.idObjeto = sel2.idObjetoHijo) as sel3 "
		+ "left join ObjetoDOM on `idObjetoRelacion` = `idObjeto`) as sel4"
	+"left join InstanciaObjeto on `idObjeto` = `idObjetoRelacion` and `idInstancia` = `idInstanciaRelacion`";
	private static final String GET_COMPLEX_ATTRIBUTE_STUDENT = GET_COMPLEX_ATTRIBUTE;
	private static final String DELETE_COMPLEX_ATTRIBUTE = "DELETE FROM `InstanciaAtributoComplejo` WHERE `idObjetoPadre` = ? and `idObjetoHijo` = ? and `idInstanciaPadre` = ? and `idInstanciaHijo` = ?";
	private static final String UPDATE_SIMPLE_ATTRIBUTE_DATE = "UPDATE `InstanciaAtributoDate` SET `dia`=?,`mes`=?,`anio`=? WHERE `idInstancia`=? and `idAtributoSencillo`=? and `idObjeto`=?";
	private static final String UPDATE_SIMPLE_ATTRIBUTE_INT = "UPDATE `InstanciaAtributoInt` SET `valor`=? WHERE `idInstancia`=? and `idAtributoSencillo`=? and `idObjeto`=?";
	private static final String UPDATE_SIMPLE_ATTRIBUTE_DOUBLE = "UPDATE `InstanciaAtributoDouble` SET `valor`=? WHERE `idInstancia`=? and `idAtributoSencillo`=? and `idObjeto`=?";
	private static final String UPDATE_SIMPLE_ATTRIBUTE_STRING = "UPDATE `InstanciaAtributoString` SET `valor`=? WHERE `idInstancia`=? and `idAtributoSencillo`=? and `idObjeto`=?";
	private static final String UPDATE_SIMPLE_ATTRIBUTE_TEXT = "UPDATE `InstanciaAtributoText` SET `valor`=? WHERE `idInstancia`=? and `idAtributoSencillo`=? and `idObjeto`=?";
	
	private static final String INSERT_SIMPLE_ATTRIBUTE_DATE = "INSERT INTO `InstanciaAtributoDate`(`idInstancia`, `idAtributoSencillo`, `idObjeto`, `dia`, `mes`, `anio`) VALUES (?,?,?,?,?,?)";
	private static final String INSERT_SIMPLE_ATTRIBUTE_INT = "INSERT INTO `InstanciaAtributoInt`(`idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor`) VALUES (?,?,?,?)";
	private static final String INSERT_SIMPLE_ATTRIBUTE_DOUBLE = "INSERT INTO `InstanciaAtributoDouble`(`idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor`) VALUES (?,?,?,?)";
	private static final String INSERT_SIMPLE_ATTRIBUTE_STRING = "INSERT INTO `InstanciaAtributoString`(`idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor`) VALUES (?,?,?,?)";
	private static final String INSERT_SIMPLE_ATTRIBUTE_TEXT = "INSERT INTO `InstanciaAtributoText`(`idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor`) VALUES (?,?,?,?)";
	private static final String GET_OBJECT_BY_NAME = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` WHERE idObjeto = ? and nombreInstancia = ?";
	private static final String RENAME_OBJECT = "UPDATE `InstanciaObjeto` SET `nombreInstancia`= ? WHERE `idObjeto` = ? and `idInstancia` = ?";
	private static final String DELETE_OBJECT = "DELETE FROM `InstanciaObjeto` WHERE `idInstancia` = ? and `idObjeto` = ?";
	private static final String GET_OBJECT_COMPLEX_ATTRIBUTES_NO_VAL = GET_OBJECT_COMPLEX_ATTRIBUTES + " order by validadoPadre";
	private static final String VALIDATE_ATRIBUTOC = "UPDATE `InstanciaAtributoComplejo` SET `validado`= ?,`textoValidacion`= ?, `textoLeido` = 0 WHERE `idObjetoPadre` = ? and `idObjetoHijo` = ? and `idInstanciaPadre` = ? and `idInstanciaHijo` = ?";
	private static final String COMMENT_ATRIBUTOC = "UPDATE `InstanciaAtributoComplejo` SET `textoValidacion`= ?, `textoLeido` = 0 WHERE `idObjetoPadre` = ? and `idObjetoHijo` = ? and `idInstanciaPadre` = ? and `idInstanciaHijo` = ?";
	private static final String GET_VALIDATED_OBJECT_INSTANCE_WITH_NOT_VALIDATED_AC_LIST_BY_GROUP = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` where validado = ? and idObjeto = ? and idInstancia IN (SELECT `idInstanciaPadre` FROM `InstanciaAtributoComplejo` WHERE idGrupo = ? and idObjetoPadre = ? and validado = ?)";
	private static final String VALIDATE_OBJECT_INSTANCE = "UPDATE `InstanciaObjeto` SET `validado`= ?,`textoValidacion`= ?,`textoLeido`=? WHERE `idObjeto` = ? and `idInstancia` = ?";
	
	private static final String GET_OBJECT_INSTANCES_BY_TYPE_USER = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` WHERE idObjeto = ? and idGrupo = ? and creador = ?";
	private static final String GET_VALIDATED_OBJECT_INSTANCE_WITH_NOT_VALIDATED_AC_LIST_BY_USER = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` where idObjeto = ? and creador != ? and idInstancia IN (SELECT `idInstanciaPadre` FROM `InstanciaAtributoComplejo` WHERE idGrupo = ? and idObjetoPadre = ? and creador = ?)";
	private static final String GET_OBJECT_ATTRIBUTES_OBJECT = "SELECT InstanciaObjeto.`idInstancia`, InstanciaObjeto.`idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `textoLeido` FROM `InstanciaObjeto` right join ( SELECT `idObjetoHijo`, `idInstanciaHijo` FROM `InstanciaAtributoObjeto` WHERE idObjeto = ? and idObjetoHijo = ? and idInstancia = ? and idAtributoSencillo = ?) as sel1 on InstanciaObjeto.idObjeto = sel1.idObjetoHijo and InstanciaObjeto.idInstancia = sel1.idInstanciaHijo";
	private static final String DELETE_SIMPLE_ATRIBUTE_OBJECT = "DELETE FROM `InstanciaAtributoObjeto` WHERE `idObjeto` = ? and `idInstancia` = ? and `idAtributoSencillo` = ? and `idObjetoHijo` = ?";
	private static final String INSERT_SIMPLE_ATRIBUTE_OBJECT = "INSERT INTO `InstanciaAtributoObjeto`(`idObjeto`, `idInstancia`, `idAtributoSencillo`, `idObjetoHijo`, `idInstanciaHijo`) VALUES (?,?,?,?,?)";
	private static final String EXIST_SIMPLE_ATRIBUTE_OBJECT = "SELECT count(*) FROM `AtributoSencilloObjeto` WHERE `idAtributo` = ? and `idObjeto` = ? and `subtipo` = ?";
	private static final String EXIST_SIMPLE_ATRIBUTE_OBJECT_INSTANCE = "SELECT count(*) FROM `InstanciaAtributoObjeto` WHERE `idObjeto` = ? and `idInstancia` = ? and `idAtributoSencillo` = ? and `idObjetoHijo` = ?";
	private static final String UPDATE_SIMPLE_ATRIBUTE_OBJECT = "UPDATE `InstanciaAtributoObjeto` SET `idInstanciaHijo`= ? WHERE `idObjeto`= ? and `idInstancia`= ? and `idAtributoSencillo`= ?  and `idObjetoHijo`= ?";
	private static final String CHECK_COMMENT_OBJECT = "UPDATE `InstanciaObjeto` SET `textoLeido`=? WHERE `idInstancia`=? and `idObjeto`=?";
	
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

	public List<InstanciaObjetoDOM> getObjectListByTipe(TipoObjetoDOM tipo) {
		try{
			return jdbcTemplate.query(GET_OBJECT_INSTANCES_BY_TYPE, new Object[]{tipo.getTipoDOM(),Constants.OBJETO_VALIDADO}, new ObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public InstanciaObjetoDOM getObjectInstance(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.queryForObject(GET_OBJECT_INSTANCE, new Object[]{id,tipo.getTipoDOM(),Constants.OBJETO_VALIDADO}, new ObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public List<InstanciaAtributoSencilloDOM> getAtributosSencillos(TipoObjetoDOM tipo, int id) {
		List<InstanciaAtributoSencilloDOM> la = null;
		try{
			la = jdbcTemplate.query(GET_OBJECT_S_ATTRIBUTES_TYPE_LIST, new Object[]{tipo.getTipoDOM()}, new AtributoSencilloDOMMapper());
			for(InstanciaAtributoSencilloDOM a : la){
				switch(a.getTipoAtributo()){
				case Constants.TIPO_ATRIBUTO_FECHA:
					try{
						SpecialDate sd = jdbcTemplate.queryForObject(GET_OBJECT_DATE_ATTRIBUTES_VALUE, new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new SpecialDateDOMMapper());
						a.setValor(sd);
						break;
					}
					catch (Exception e){
						a.setValor(null);
						break;
					}
				case Constants.TIPO_ATRIBUTO_DOUBLE:
					try{
						Double d = jdbcTemplate.queryForObject(GET_OBJECT_DOUBLE_ATTRIBUTES_VALUE,new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new DoubleDOMMapper());
						a.setValor(d);
						break;
					}
					catch (Exception e){
						a.setValor(null);
						break;
					}
				case Constants.TIPO_ATRIBUTO_INT:
					try{
						Integer i = jdbcTemplate.queryForInt(GET_OBJECT_INT_ATTRIBUTES_VALUE,new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()});
						a.setValor(i);
						break;
					}
					catch (Exception e){
						a.setValor(null);
						break;
					}
				case Constants.TIPO_ATRIBUTO_STRING:
					try{
						String s = jdbcTemplate.queryForObject(GET_OBJECT_STRING_ATTRIBUTES_VALUE, new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new StringDOMMapper());
						a.setValor(s);
						break;
					}
					catch (Exception e){
						a.setValor(null);
						break;
					}
				case Constants.TIPO_ATRIBUTO_TEXT:
					try{
						String t = jdbcTemplate.queryForObject(GET_OBJECT_TEXT_ATTRIBUTES_VALUE, new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new StringDOMMapper());
						a.setValor(t);
						break;
					}
					catch (Exception e){
						a.setValor(null);
						break;
					}
				case Constants.TIPO_ATRIBUTO_OBJECT:
					try{
						InstanciaObjetoDOM o = jdbcTemplate.queryForObject(GET_OBJECT_ATTRIBUTES_OBJECT, new Object[]{tipo.getTipoDOM(),a.getSubtipo(),id,a.getIdAtributo()},new ObjetoDOMMapper());
						a.setValor(o);
						break;
					}
					catch(Exception e){
						a.setValor(null);
						break;
					}
				default: throw new Exception();
				}
			}
			return la;
		}
		catch(Exception e){
			return la;
		}
	}

	public List<InstanciaAtributoComplejoDOM> getAtributosComplejos(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.query(GET_OBJECT_COMPLEX_ATTRIBUTES, new Object[]{tipo.getTipoDOM(),id}, new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}
	
	public List<InstanciaAtributoComplejoDOM> getAtributosComplejosNotVal(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.query(GET_OBJECT_COMPLEX_ATTRIBUTES_STUDENT, new Object[]{tipo.getTipoDOM(),id}, new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String createObjectInstance(InstanciaObjetoDOM o) {
		try{
			if(jdbcTemplate.queryForInt(GET_OBJECT_INSTANCE_BY_NAME,new Object[]{o.getTipo().getTipoDOM(),o.getNombre()}) == 0){
				int maxId = jdbcTemplate.queryForInt(GET_MAX_INSTANCE_ID_BY_OBJECT, new Object[]{o.getTipo().getTipoDOM()});
				o.setIdInstancia(maxId+1);
				if(jdbcTemplate.update(ADD_OBJECT_INSTANCE, new Object[]{o.getIdInstancia(),o.getTipo().getTipoDOM(),o.getNombre(),o.getValidado(),o.getTextoValidacion(),o.getGrupo(),o.getCreador().getId(),o.getTextoLeido()}) == 1){
					return "creado";
				}
				else{
					return "errorDB";
				}
			}
			else{
				return "nombreRepetido";
			}
		}
		catch(Exception e){
			return "errorDB";
		}
	}

	public List<TipoAtributoComplejoDOM> getTiposAtributosCompleos(TipoObjetoDOM tipo) {
		try{
			return jdbcTemplate.query(GET_OBJECT_COMPLEX_ATTRIBUTES_TYPES, new Object[]{tipo.getTipoDOM()}, new TipoAtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String addComplexAttribute(InstanciaAtributoComplejoDOM ao, int idInstanciaPadre) {
		try{
			int i = jdbcTemplate.update(ADD_COMPLEX_ATTRIBUTE, new Object[]{ao.getTipoPadre().getTipoDOM(),ao.getTipoHijo().getTipoDOM(),idInstanciaPadre,ao.getInstanciaHijo().getIdInstancia(),ao.getValidado(),ao.getTextoValidacion(),ao.getIdGrupo(),ao.getCreador(),ao.getIdTipoObjetoRelacion(),ao.getInstanciaObjetoRelacion().getIdInstancia()});
			if(i == 1)
				return "añadido";
			else
				return "errorDB";
		}
		catch(Exception e){
			return "errorDB";
		}
	}

	public InstanciaAtributoComplejoDOM getAtributoComplejo(int tipoDOM, int padre,int tipoHijo, int hijo) {
		try{
			return jdbcTemplate.queryForObject(GET_COMPLEX_ATTRIBUTE, new Object[]{tipoDOM,padre,tipoHijo,hijo,Constants.OBJETO_VALIDADO},new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}
	
	public InstanciaAtributoComplejoDOM getAtributoComplejoNotVal(int tipoDOM, int padre,int tipoHijo, int hijo) {
		try{
			return jdbcTemplate.queryForObject(GET_COMPLEX_ATTRIBUTE_STUDENT, new Object[]{tipoDOM,padre,tipoHijo,hijo},new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String remAtributoComplejo(InstanciaAtributoComplejoDOM acd, int padre) {
		try{
			int i = jdbcTemplate.update(DELETE_COMPLEX_ATTRIBUTE, new Object[]{acd.getTipoPadre().getTipoDOM(),acd.getTipoHijo().getTipoDOM(),padre,acd.getInstanciaHijo().getIdInstancia()});
			if(i == 1){
				return "borrado";
			}
			else{
				return "error";
			}
		}
		catch(Exception e){
			return "errorBD";
		}
	}

	public String updateSimpleAttributes(InstanciaObjetoDOM obj) {
		int idi = obj.getIdInstancia();
		int idt = obj.getTipo().getTipoDOM();
		try{
			for(InstanciaAtributoSencilloDOM a : obj.getAtributosSencillos()){
				if(a.getTipoAtributo() == Constants.TIPO_ATRIBUTO_FECHA){
					SpecialDate d = (SpecialDate) a.getValor();
					int i = jdbcTemplate.update(UPDATE_SIMPLE_ATTRIBUTE_DATE, new Object[]{d.getDia(),d.getMes(),d.getAnio(),idi,a.getIdAtributo(),idt});
					if(i != 1){
						i = jdbcTemplate.update(INSERT_SIMPLE_ATTRIBUTE_DATE, new Object[]{idi,a.getIdAtributo(),idt,d.getDia(),d.getMes(),d.getAnio()});
						if( i != 1){
							return "errorDB";
						}
					}
				}
				else if(a.getTipoAtributo() == Constants.TIPO_ATRIBUTO_INT){
					Integer d = (Integer) a.getValor();
					int i = jdbcTemplate.update(UPDATE_SIMPLE_ATTRIBUTE_INT, new Object[]{d.intValue(),idi,a.getIdAtributo(),idt});
					if(i != 1){
						i = jdbcTemplate.update(INSERT_SIMPLE_ATTRIBUTE_INT, new Object[]{idi,a.getIdAtributo(),idt,d.intValue()});
						if(i != 1){
							return "errorDB";
						}
					}
				}
				else if(a.getTipoAtributo() == Constants.TIPO_ATRIBUTO_DOUBLE){
					Double d = (Double) a.getValor();
					int i = jdbcTemplate.update(UPDATE_SIMPLE_ATTRIBUTE_DOUBLE, new Object[]{d.doubleValue(),idi,a.getIdAtributo(),idt});
					if(i != 1){
						i = jdbcTemplate.update(INSERT_SIMPLE_ATTRIBUTE_DOUBLE, new Object[]{idi,a.getIdAtributo(),idt,d.doubleValue()});
						if(i != 1){
							return "errorDB";
						}
					}
				}
				else if(a.getTipoAtributo() == Constants.TIPO_ATRIBUTO_STRING){
					String d = (String) a.getValor();
					int i = jdbcTemplate.update(UPDATE_SIMPLE_ATTRIBUTE_STRING, new Object[]{d,idi,a.getIdAtributo(),idt});
					if(i != 1){
						i = jdbcTemplate.update(INSERT_SIMPLE_ATTRIBUTE_STRING, new Object[]{idi,a.getIdAtributo(),idt,d});
						if(i != 1){
							return "errorDB";	
						}
					}
				}
				else if(a.getTipoAtributo() == Constants.TIPO_ATRIBUTO_TEXT){
					String d = (String) a.getValor();
					int i = jdbcTemplate.update(UPDATE_SIMPLE_ATTRIBUTE_TEXT, new Object[]{d,idi,a.getIdAtributo(),idt});
					if(i != 1){
						i = jdbcTemplate.update(INSERT_SIMPLE_ATTRIBUTE_TEXT, new Object[]{idi,a.getIdAtributo(),idt,d});
						if(i != 1){
							return "errorDB";
						}
					}
				}
				else if(a.getTipoAtributo() == Constants.TIPO_ATRIBUTO_OBJECT){
					InstanciaObjetoDOM o = (InstanciaObjetoDOM) a.getValor();
					if(o != null){
						if(o.getIdInstancia() == 0){
							if(atributoSimpleObjetoInstanciaExists(obj.getTipo().getTipoDOM(),obj.getIdInstancia(),a.getIdAtributo(),a.getSubtipo(),o.getIdInstancia())){
								int res = jdbcTemplate.update(DELETE_SIMPLE_ATRIBUTE_OBJECT, new Object[]{obj.getTipo().getTipoDOM(),obj.getIdInstancia(),a.getIdAtributo(),a.getSubtipo()});
								return "ok";
							}
						}
						else{
							if(!atributoSimpleObjetoInstanciaExists(obj.getTipo().getTipoDOM(),obj.getIdInstancia(),a.getIdAtributo(),a.getSubtipo(),o.getIdInstancia())){
								int res = jdbcTemplate.update(INSERT_SIMPLE_ATRIBUTE_OBJECT, new Object[]{obj.getTipo().getTipoDOM(),obj.getIdInstancia(),a.getIdAtributo(),a.getSubtipo(),o.getIdInstancia()});
								if(res != 1){
									return "errorDB";
								}
							}
							else{
								int res = jdbcTemplate.update(UPDATE_SIMPLE_ATRIBUTE_OBJECT, new Object[]{o.getIdInstancia(),obj.getTipo().getTipoDOM(),obj.getIdInstancia(),a.getIdAtributo(),a.getSubtipo()});
								if(res != 1){
									return "errorDB";
								}
							}
						}
					}
				}
				else{
					return "errorAtributos";
				}
			}
			return "ok";
		}
		catch(Exception e){
			return "errorDB";
		}
	}

	public InstanciaObjetoDOM getObjectByName(TipoObjetoDOM tipo, String nombre) {
		try{
			return jdbcTemplate.queryForObject(GET_OBJECT_BY_NAME, new Object[]{tipo.getTipoDOM(),nombre},new ObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String renameObject(TipoObjetoDOM tipo, int id, String nombre) {
		try{
			int i = jdbcTemplate.update(RENAME_OBJECT, new Object[]{nombre,tipo.getTipoDOM(),id});
			if(i == 1)
				return "ok";
			else
				return "errorDB";
		}
		catch(Exception e){
			return "errorDB";
		}
	}

	public String deleteObjetoDOM(InstanciaObjetoDOM obj) {
		try{
			int i = jdbcTemplate.update(DELETE_OBJECT, new Object[]{obj.getIdInstancia(),obj.getTipo().getTipoDOM()});
			if(i == 1){
				return "borrado";
			}
			else{
				return "error";
			}
		}
		catch(Exception e){
			return "errorBD";
		}
	}

	public List<InstanciaObjetoDOM> getTeachersObjetoDOMList(TipoObjetoDOM tipo,	Group groupA) {
		try{
			List<InstanciaObjetoDOM> li1 = jdbcTemplate.query(GET_OBJECT_INSTANCES_BY_TYPE_GROUP, new Object[]{tipo.getTipoDOM(),Constants.OBJETO_NO_VALIDADO,groupA.getIdGrupo()}, new ObjetoDOMMapper());
			List<InstanciaObjetoDOM> li2 = jdbcTemplate.query(GET_VALIDATED_OBJECT_INSTANCE_WITH_NOT_VALIDATED_AC_LIST_BY_GROUP, new Object[]{Constants.OBJETO_VALIDADO,tipo.getTipoDOM(),groupA.getIdGrupo(),tipo.getTipoDOM(),Constants.OBJETO_NO_VALIDADO}, new ObjetoDOMMapper());
			li1.addAll(li2);
			return li1;
		}
		catch(Exception e){
			return null;
		}
	}

	public InstanciaObjetoDOM getObjectInstanceNotVal(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.queryForObject(GET_OBJECT_INSTANCE, new Object[]{id,tipo.getTipoDOM(),Constants.OBJETO_NO_VALIDADO}, new ObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public List<InstanciaAtributoComplejoDOM> getAtributosComplejosNoVal(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.query(GET_OBJECT_COMPLEX_ATTRIBUTES_NO_VAL, new Object[]{tipo.getTipoDOM(),id}, new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String validateAtributoComplejo(int tipoDOM, int idPadre, int tipoHijo, int idHijo, String textV) {
		try{
			int i = jdbcTemplate.update(VALIDATE_ATRIBUTOC, new Object[]{Constants.OBJETO_VALIDADO,textV,tipoDOM,tipoHijo,idPadre,idHijo});
			if(i == 1){
				return "validado";
			}
			else{
				return "errorBD";
			}
		}
		catch(Exception e){
			return "errorBD";
		}
	}

	public String commentAtributoComplejoNoVal(int tipoDOM, int idPadre, int tipoHijo, int idHijo, String textV) {
		try{
			int i = jdbcTemplate.update(COMMENT_ATRIBUTOC, new Object[]{textV,tipoDOM,tipoHijo,idPadre,idHijo});
			if(i == 1){
				return "novalidado";
			}
			else{
				return "errorBD";
			}
		}
		catch(Exception e){
			return "errorBD";
		}
	}

	public String validateObjectInstance(InstanciaObjetoDOM obj, String text) {
		try{
			int i = jdbcTemplate.update(VALIDATE_OBJECT_INSTANCE, new Object[]{Constants.OBJETO_VALIDADO,text,Constants.TEXTO_NO_LEIDO,obj.getTipo().getTipoDOM(),obj.getIdInstancia()});
			if(i == 1){
				return "validado";
			}
			else{
				return "errorBD";
			}
		}
		catch(Exception e){
			return "errorBD";
		}
	}

	public String commentObjectInstance(InstanciaObjetoDOM obj, String text) {
		try{
			int i = jdbcTemplate.update(VALIDATE_OBJECT_INSTANCE, new Object[]{Constants.OBJETO_NO_VALIDADO,text,Constants.TEXTO_NO_LEIDO,obj.getTipo().getTipoDOM(),obj.getIdInstancia()});
			if(i == 1){
				return "novalidado";
			}
			else{
				return "errorBD";
			}
		}
		catch(Exception e){
			return "errorBD";
		}
	}

	public List<InstanciaObjetoDOM> getStudentObjetoDOMList(TipoObjetoDOM tipo,	Group groupA, User user) {
		try{
			List<InstanciaObjetoDOM> li1 = jdbcTemplate.query(GET_OBJECT_INSTANCES_BY_TYPE_USER, new Object[]{tipo.getTipoDOM(),groupA.getIdGrupo(),user.getId()}, new ObjetoDOMMapper());
			List<InstanciaObjetoDOM> li2 = jdbcTemplate.query(GET_VALIDATED_OBJECT_INSTANCE_WITH_NOT_VALIDATED_AC_LIST_BY_USER, new Object[]{tipo.getTipoDOM(),user.getId(),groupA.getIdGrupo(),tipo.getTipoDOM(),user.getId()}, new ObjetoDOMMapper());
			li1.addAll(li2);
			return li1;
		}
		catch(Exception e){
			return null;
		}
	}

	public boolean atributoSimpleObjetoExists(int idAtributo, int tipoDOM,int subtipo) {
		try{
			int i = jdbcTemplate.queryForInt(EXIST_SIMPLE_ATRIBUTE_OBJECT,new Object[]{idAtributo,tipoDOM,subtipo});
			return i == 1;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean atributoSimpleObjetoInstanciaExists(int idObjeto, int idInstancia, int idAtributoSencillo, int idObjetoHijo, int idInstanciaHijo) {
		try{
			int i = jdbcTemplate.queryForInt(EXIST_SIMPLE_ATRIBUTE_OBJECT_INSTANCE,new Object[]{idObjeto,idInstancia,idAtributoSencillo,idObjetoHijo});
			return i == 1;
		}
		catch(Exception e){
			return false;
		}
	}

	public String setObjectTextReaded(InstanciaObjetoDOM obj) {
		try{
			int i = jdbcTemplate.update(CHECK_COMMENT_OBJECT, new Object[]{Constants.TEXTO_LEIDO,obj.getIdInstancia(),obj.getTipo().getTipoDOM()});
			if(i == 1){
				return "ok";
			}
			else{
				return "errorBD";
			}
		}
		catch(Exception e){
			return "errorBD";
		}
	}
}
