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
import com.cvilla.medievalia.domain.AtributoComplejoDOM;
import com.cvilla.medievalia.domain.AtributoSencilloDOM;
import com.cvilla.medievalia.domain.ObjetoDOM;
import com.cvilla.medievalia.domain.TipoAtributoComplejoDOM;
import com.cvilla.medievalia.domain.TipoObjetoDOM;
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
	private static final String GET_OBJECT_INSTANCES_BY_TYPE = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaObjeto` WHERE idObjeto = ? and validado = ?";
	private static final String GET_OBJECT_INSTANCE = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaObjeto` WHERE idInstancia = ? and idObjeto = ? and validado = ?";
	private static final String GET_OBJECT_S_ATTRIBUTES_TYPE_LIST = "SELECT `idAtributo`, `idObjeto`, `nombreAtributo`, `tipo` FROM `AtributoSencilloObjeto` WHERE idObjeto = ?";
	private static final String GET_OBJECT_DATE_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `dia`, `mes`, `anio` FROM `InstanciaAtributoDate` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_DOUBLE_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor` FROM `InstanciaAtributoDouble` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_INT_ATTRIBUTES_VALUE = "SELECT `valor` FROM `InstanciaAtributoInt` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_STRING_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor` FROM `InstanciaAtributoString` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_TEXT_ATTRIBUTES_VALUE = "SELECT `idInstancia`, `idAtributoSencillo`, `idObjeto`, `valor` FROM `InstanciaAtributoText` WHERE idObjeto = ? and idInstancia = ? and idAtributoSencillo = ?";
	private static final String GET_OBJECT_COMPLEX_ATTRIBUTES = "select	`idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, sel2.`validado` as `validadoPadre`, sel2.`textoValidacion` as `textoValidacionPadre`, sel2.`idGrupo` as `idGrupoPadre`, sel2.`creador` as `creadorPadre`, `nombreAtributo`, `nombreInstancia` as `nombreObjetoHijo`, InstanciaObjeto.validado as `validadoHijo`, InstanciaObjeto.textoValidacion as `textoValidacionHijo`, InstanciaObjeto.idGrupo as `idGrupoHijo`, InstanciaObjeto.creador as `creadorHijo` from "
																	+ "(select sel1.`idObjetoPadre` as `idObjetoPadre`, `nombreObjetoPadre`, sel1.`idObjetoHijo` as `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `nombreAtributo` from "
																		+ "(SELECT `idObjetoPadre`, nombreObjeto as `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaAtributoComplejo` "
																		+ "left join ObjetoDOM on idObjetoPadre = idObjeto WHERE idObjetoPadre = ? and idInstanciaPadre = ? and validado = ?) as sel1 "
														     		+ "left join AtributoComplejoObjeto on sel1.idObjetoPadre = AtributoComplejoObjeto.idObjetoPadre and sel1.idObjetoHijo = AtributoComplejoObjeto.idObjetoHijo) as sel2 "
													     		+ "left join InstanciaObjeto on InstanciaObjeto.idInstancia = sel2.idInstanciaHijo and InstanciaObjeto.idObjeto = sel2.idObjetoHijo";
	private static final String GET_OBJECT_COMPLEX_ATTRIBUTES_STUDENT = "select	`idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, sel2.`validado` as `validadoPadre`, sel2.`textoValidacion` as `textoValidacionPadre`, sel2.`idGrupo` as `idGrupoPadre`, sel2.`creador` as `creadorPadre`, `nombreAtributo`, `nombreInstancia` as `nombreObjetoHijo`, InstanciaObjeto.validado as `validadoHijo`, InstanciaObjeto.textoValidacion as `textoValidacionHijo`, InstanciaObjeto.idGrupo as `idGrupoHijo`, InstanciaObjeto.creador as `creadorHijo` from "
			+ "(select sel1.`idObjetoPadre` as `idObjetoPadre`, `nombreObjetoPadre`, sel1.`idObjetoHijo` as `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `nombreAtributo` from "
				+ "(SELECT `idObjetoPadre`, nombreObjeto as `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaAtributoComplejo` "
				+ "left join ObjetoDOM on idObjetoPadre = idObjeto WHERE idObjetoPadre = ? and idInstanciaPadre = ?) as sel1 "
     		+ "left join AtributoComplejoObjeto on sel1.idObjetoPadre = AtributoComplejoObjeto.idObjetoPadre and sel1.idObjetoHijo = AtributoComplejoObjeto.idObjetoHijo) as sel2 "
 		+ "left join InstanciaObjeto on InstanciaObjeto.idInstancia = sel2.idInstanciaHijo and InstanciaObjeto.idObjeto = sel2.idObjetoHijo";
	private static final String ADD_OBJECT_INSTANCE = "INSERT INTO `InstanciaObjeto`(`idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador`) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_MAX_INSTANCE_ID_BY_OBJECT = "select max(idInstancia) from InstanciaObjeto where idObjeto = ?";
	private static final String GET_OBJECT_INSTANCE_BY_NAME ="SELECT count(*) FROM `InstanciaObjeto` WHERE idObjeto = ? and nombreInstancia = ?";
	private static final String GET_OBJECT_COMPLEX_ATTRIBUTES_TYPES = "SELECT `idObjetoPadre`, `idObjetoHijo`, `NombreAtributo` FROM `AtributoComplejoObjeto` WHERE idObjetoPadre = ?";
	private static final String ADD_COMPLEX_ATTRIBUTE = "INSERT INTO `InstanciaAtributoComplejo`(`idObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`) VALUES (?,?,?,?,?,?,?,?)";
	private static final String GET_COMPLEX_ATTRIBUTE = "select	`idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, sel2.`validado` as `validadoPadre`, sel2.`textoValidacion` as `textoValidacionPadre`, sel2.`idGrupo` as `idGrupoPadre`, sel2.`creador` as `creadorPadre`, `nombreAtributo`, `nombreInstancia` as `nombreObjetoHijo`, InstanciaObjeto.validado as `validadoHijo`, InstanciaObjeto.textoValidacion as `textoValidacionHijo`, InstanciaObjeto.idGrupo as `idGrupoHijo`, InstanciaObjeto.creador as `creadorHijo` from "
			+ "(select sel1.`idObjetoPadre` as `idObjetoPadre`, `nombreObjetoPadre`, sel1.`idObjetoHijo` as `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `nombreAtributo` from "
			+ "(SELECT `idObjetoPadre`, nombreObjeto as `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaAtributoComplejo` "
			+ "left join ObjetoDOM on idObjetoPadre = idObjeto WHERE idObjetoPadre = ? and idInstanciaPadre = ? and idObjetoHijo = ? and idInstanciaHijo = ? and validado = ?) as sel1 "
 		+ "left join AtributoComplejoObjeto on sel1.idObjetoPadre = AtributoComplejoObjeto.idObjetoPadre and sel1.idObjetoHijo = AtributoComplejoObjeto.idObjetoHijo) as sel2 "
		+ "left join InstanciaObjeto on InstanciaObjeto.idInstancia = sel2.idInstanciaHijo and InstanciaObjeto.idObjeto = sel2.idObjetoHijo";
	private static final String GET_COMPLEX_ATTRIBUTE_STUDENT = "select	`idObjetoPadre`, `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, sel2.`validado` as `validadoPadre`, sel2.`textoValidacion` as `textoValidacionPadre`, sel2.`idGrupo` as `idGrupoPadre`, sel2.`creador` as `creadorPadre`, `nombreAtributo`, `nombreInstancia` as `nombreObjetoHijo`, InstanciaObjeto.validado as `validadoHijo`, InstanciaObjeto.textoValidacion as `textoValidacionHijo`, InstanciaObjeto.idGrupo as `idGrupoHijo`, InstanciaObjeto.creador as `creadorHijo` from "
			+ "(select sel1.`idObjetoPadre` as `idObjetoPadre`, `nombreObjetoPadre`, sel1.`idObjetoHijo` as `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador`, `nombreAtributo` from "
			+ "(SELECT `idObjetoPadre`, nombreObjeto as `nombreObjetoPadre`, `idObjetoHijo`, `idInstanciaPadre`, `idInstanciaHijo`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaAtributoComplejo` "
			+ "left join ObjetoDOM on idObjetoPadre = idObjeto WHERE idObjetoPadre = ? and idInstanciaPadre = ? and idObjetoHijo = ? and idInstanciaHijo = ?) as sel1 "
 		+ "left join AtributoComplejoObjeto on sel1.idObjetoPadre = AtributoComplejoObjeto.idObjetoPadre and sel1.idObjetoHijo = AtributoComplejoObjeto.idObjetoHijo) as sel2 "
		+ "left join InstanciaObjeto on InstanciaObjeto.idInstancia = sel2.idInstanciaHijo and InstanciaObjeto.idObjeto = sel2.idObjetoHijo";
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
	private static final String GET_OBJECT_BY_NAME = "SELECT `idInstancia`, `idObjeto`, `nombreInstancia`, `validado`, `textoValidacion`, `idGrupo`, `creador` FROM `InstanciaObjeto` WHERE idObjeto = ? and nombreInstancia = ?";
	private static final String RENAME_OBJECT = "UPDATE `InstanciaObjeto` SET `nombreInstancia`= ? WHERE `idObjeto` = ? and `idInstancia` = ?";

	
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
			return jdbcTemplate.query(GET_OBJECT_INSTANCES_BY_TYPE, new Object[]{tipo.getTipoDOM(),Constants.OBJETO_VALIDADO}, new ObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public ObjetoDOM getObjectInstance(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.queryForObject(GET_OBJECT_INSTANCE, new Object[]{id,tipo.getTipoDOM(),Constants.OBJETO_VALIDADO}, new ObjetoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public List<AtributoSencilloDOM> getAtributosSencillos(TipoObjetoDOM tipo, int id) {
		List<AtributoSencilloDOM> la = null;
		try{
			la = jdbcTemplate.query(GET_OBJECT_S_ATTRIBUTES_TYPE_LIST, new Object[]{tipo.getTipoDOM()}, new AtributoSencilloDOMMapper());
			for(AtributoSencilloDOM a : la){
				switch(a.getTipoAtributo()){
				case Constants.TIPO_ATRIBUTO_FECHA:
					try{
						SpecialDate sd = jdbcTemplate.queryForObject(GET_OBJECT_DATE_ATTRIBUTES_VALUE, new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new SpecialDateDOMMapper());
						a.setValor(sd);
						break;
					}
					catch (Exception e){
						a.setValor(null);
					}
				case Constants.TIPO_ATRIBUTO_DOUBLE:
					try{
						Double d = jdbcTemplate.queryForObject(GET_OBJECT_DOUBLE_ATTRIBUTES_VALUE,new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new DoubleDOMMapper());
						a.setValor(d);
						break;
					}
					catch (Exception e){
						a.setValor(null);
					}
				case Constants.TIPO_ATRIBUTO_INT:
					try{
						Integer i = jdbcTemplate.queryForInt(GET_OBJECT_INT_ATTRIBUTES_VALUE,new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()});
						a.setValor(i);
						break;
					}
					catch (Exception e){
						a.setValor(null);
					}
				case Constants.TIPO_ATRIBUTO_STRING:
					try{
						String s = jdbcTemplate.queryForObject(GET_OBJECT_STRING_ATTRIBUTES_VALUE, new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new StringDOMMapper());
						a.setValor(s);
						break;
					}
					catch (Exception e){
						a.setValor(null);
					}
				case Constants.TIPO_ATRIBUTO_TEXT:
					try{
						String t = jdbcTemplate.queryForObject(GET_OBJECT_TEXT_ATTRIBUTES_VALUE, new Object[]{tipo.getTipoDOM(),id,a.getIdAtributo()}, new StringDOMMapper());
						a.setValor(t);
						break;
					}
					catch (Exception e){
						a.setValor(null);
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

	public List<AtributoComplejoDOM> getAtributosComplejos(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.query(GET_OBJECT_COMPLEX_ATTRIBUTES, new Object[]{tipo.getTipoDOM(),id,Constants.OBJETO_VALIDADO}, new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}
	
	public List<AtributoComplejoDOM> getAtributosComplejosNotVal(TipoObjetoDOM tipo, int id) {
		try{
			return jdbcTemplate.query(GET_OBJECT_COMPLEX_ATTRIBUTES_STUDENT, new Object[]{tipo.getTipoDOM(),id}, new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String createObjectInstance(ObjetoDOM o) {
		try{
			if(jdbcTemplate.queryForInt(GET_OBJECT_INSTANCE_BY_NAME,new Object[]{o.getTipo().getTipoDOM(),o.getNombre()}) == 0){
				int maxId = jdbcTemplate.queryForInt(GET_MAX_INSTANCE_ID_BY_OBJECT, new Object[]{o.getTipo().getTipoDOM()});
				o.setIdInstancia(maxId+1);
				if(jdbcTemplate.update(ADD_OBJECT_INSTANCE, new Object[]{o.getIdInstancia(),o.getTipo().getTipoDOM(),o.getNombre(),o.getValidado(),o.getTextoValidacion(),o.getGrupo(),o.getCreador()}) == 1){
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

	public String addComplexAttribute(AtributoComplejoDOM ao, int idInstanciaPadre) {
		try{
			int i = jdbcTemplate.update(ADD_COMPLEX_ATTRIBUTE, new Object[]{ao.getTipoPadre().getTipoDOM(),ao.getTipoHijo().getTipoDOM(),idInstanciaPadre,ao.getInstanciaHijo().getIdInstancia(),ao.getValidado(),ao.getTextoValidacion(),ao.getIdGrupo(),ao.getCreador()});
			if(i == 1)
				return "añadido";
			else
				return "errorDB";
		}
		catch(Exception e){
			return "errorDB";
		}
	}

	public AtributoComplejoDOM getAtributoComplejo(int tipoDOM, int padre,int tipoHijo, int hijo) {
		try{
			return jdbcTemplate.queryForObject(GET_COMPLEX_ATTRIBUTE, new Object[]{tipoDOM,padre,tipoHijo,hijo,Constants.OBJETO_VALIDADO},new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}
	
	public AtributoComplejoDOM getAtributoComplejoNotVal(int tipoDOM, int padre,int tipoHijo, int hijo) {
		try{
			return jdbcTemplate.queryForObject(GET_COMPLEX_ATTRIBUTE_STUDENT, new Object[]{tipoDOM,padre,tipoHijo,hijo},new AtributoComplejoDOMMapper());
		}
		catch(Exception e){
			return null;
		}
	}

	public String remAtributoComplejo(AtributoComplejoDOM acd, int padre) {
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

	public String updateSimpleAttributes(ObjetoDOM obj) {
		int idi = obj.getIdInstancia();
		int idt = obj.getTipo().getTipoDOM();
		try{
			for(AtributoSencilloDOM a : obj.getAtributosSencillos()){
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

	public ObjetoDOM getObjectByName(TipoObjetoDOM tipo, String nombre) {
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
}
