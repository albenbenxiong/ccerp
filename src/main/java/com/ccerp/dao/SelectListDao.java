package com.ccerp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ccerp.bean.condation.SelectListCondition;
import com.ccerp.bean.services.CostStatusServicesBean;
import com.ccerp.bean.services.CostTypeLov;
import com.ccerp.bean.services.ElementCatalog;
import com.ccerp.bean.services.ElementLov;
import com.ccerp.bean.services.EmpOrg;
import com.ccerp.bean.services.EmpPop;
import com.ccerp.bean.services.EpeopleGroupLov;
import com.ccerp.bean.services.Org;
import com.ccerp.bean.services.PayOrg;
import com.ccerp.bean.services.ValueSetComLov;
import com.ccerp.utils.GsonUtils;

@Repository
public class SelectListDao {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public  Map<String,List<PayOrg>>  getPayOrg2Lov(final String sessionId){
		logger.info("---------------getPayOrg2Lov------------->sessionId:"+sessionId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_org2_lov(?,?,?)}");
				 statement.setString(1, sessionId);
				 statement.registerOutParameter(2, Types.CLOB);
				 statement.registerOutParameter(3, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,List<PayOrg>>>() {
			@Override
			public Map<String,List<PayOrg>> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				Map<String,List<PayOrg>> returnMap = new HashMap<String,List<PayOrg>>();
				String  message =cs.getString(3);
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(2);
					List<PayOrg> resultList=GsonUtils.jsonToObjectList(json, PayOrg.class);
					returnMap.put(message, resultList);
				}else{
					returnMap.put(message, null);
				}
				logger.info("<---------------getPayOrg2Lov-------------"+message);
				return returnMap;
			}
		});
		
	}
	public Map<String,List<Org>>getOrg2Lov(final String sessionId){
		logger.info("-----------getOrg2Lov---------->sessionId:"+sessionId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_lov(?,?,?)}");
				 statement.setString(1, sessionId);
				 statement.registerOutParameter(2, Types.CLOB);
				 statement.registerOutParameter(3, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,List<Org>>>() {
			@Override
			public Map<String,List<Org>> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				Map<String,List<Org>> returnMap = new HashMap<String,List<Org>>();
				String  message =cs.getString(3);
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(2);//ClobUtils.clobToString(cs.getClob(2));
					List<Org> resultList=GsonUtils.jsonToObjectList(json, Org.class);
					returnMap.put(message, resultList);
				}else{
					returnMap.put(message, null);
				}
				logger.info("<-----------getOrg2Lov----------"+message);
				return returnMap;
			}
		});
	}
	
	
	public Map<String, List<ElementCatalog>> getElementCataLog(final String sessionId){
		logger.info("-----------getElementCataLog---------->sessionId:"+sessionId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_element_catalog_lov(?,?,?)}");
				 statement.setString(1, sessionId);
				 statement.registerOutParameter(2, Types.CLOB);
				 statement.registerOutParameter(3, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback< Map<String, List<ElementCatalog>>>() {
			@Override
			public  Map<String, List<ElementCatalog>> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(3);
				 Map<String, List<ElementCatalog>> result = new HashMap<String, List<ElementCatalog>>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(2);//ClobUtils.clobToString(cs.getClob(2));
					List<ElementCatalog> value =GsonUtils.jsonToObjectList(json,ElementCatalog.class);
					result.put(message, value);
				}else{
					result.put(message, null);
				}
				logger.info("<-----------getElementCataLog----------"+message);
				return result;
			}
		});
		
	}

	//后期早增加缓存处理
	public Map<String, EmpPop> getEmpPopup(final SelectListCondition cond){
		logger.info("-----------getEmpPopup---------->rows:"+cond.getP_rows()+",pages:"+cond.getP_pages()+",sessionId:"+cond.getP_session_id()+
				    ",period:"+cond.getP_period()+",orgName:"+cond.getP_org_name()+",empName:"+cond.getP_emp_name()+",empNumber:"+cond.getP_emp_number());
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_emp_popup(?,?,?,?,?,?,?,?,?)}");
				 statement.setString(1, cond.getP_rows());
				 statement.setString(2, cond.getP_pages());
				 statement.setString(3, cond.getP_session_id());
				 statement.setString(4, cond.getP_period());
				 statement.setString(5, cond.getP_org_name());
				 statement.setString(6, cond.getP_emp_name());
				 statement.setString(7, cond.getP_emp_number());
				 statement.registerOutParameter(8, Types.CLOB);
				 statement.registerOutParameter(9, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback< Map<String, EmpPop>>() {
			@Override
			public  Map<String, EmpPop> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(9);
				Map<String, EmpPop> resultMap = new HashMap<String, EmpPop>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(8);//ClobUtils.clobToString(cs.getClob(2));
					EmpPop result =GsonUtils.jsonToObject(json, EmpPop.class);
					resultMap.put(message, result);
				}else{
					resultMap.put(message, null);
				}
				logger.info("<-----------getEmpPopup----------"+message);
				return resultMap;
			}
		});
		
	}
	
	
	public Map<String, EmpOrg> getEmpOrg(final String rows,final String pages,final String sessionId,final String orgName){
		logger.info("-----------getEmpOrg---------->rows:"+rows+",pages:"+pages+",sessionId:"+sessionId+",orgName:"+orgName);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_emp_org_lov(?,?,?,?,?,?)}");
				 statement.setString(1, rows);
				 statement.setString(2, pages);
				 statement.setString(3, sessionId);
				 statement.setString(4, orgName);
				 statement.registerOutParameter(5, Types.CLOB);
				 statement.registerOutParameter(6, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback< Map<String, EmpOrg>>() {
			@Override
			public  Map<String, EmpOrg> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(6);
				Map<String, EmpOrg> result = new HashMap<String, EmpOrg>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(5);//ClobUtils.clobToString(cs.getClob(2));
					EmpOrg value =GsonUtils.jsonToObject(json,EmpOrg.class);
					result.put(message, value);
				}else{
					result.put(message, null);
				}
				logger.info("<-----------getEmpOrg----------"+message);
				return result;
			}
		});
		
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_element_lov
	
//	p_rows           IN VARCHAR2,
//	p_pages          IN VARCHAR2,
//	p_session_id     IN VARCHAR2,
//	p_get_element_name IN VARCHAR2,
//	p_json           OUT CLOB,
//	p_message        OUT VARCHAR2
	public Map<String,ElementLov> getElementLov(final String rows,final String pages,final String sessionId,final String elementName){
		logger.info("-----------getElementLov---------->rows:"+rows+",pages:"+pages+",sessionId:"+sessionId+",elementName:"+elementName);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_element_lov(?,?,?,?,?,?)}");
				cs.setString(1, rows);
				cs.setString(2, pages);
				cs.setString(3, sessionId);
				cs.setString(4, elementName);
				cs.registerOutParameter(5, Types.CLOB);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,ElementLov>>() {
			@Override
			public Map<String, ElementLov> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,ElementLov> resultMap= new HashMap<String,ElementLov>();
				String resultMessage =cs.getString(6);
				if("SUCCESS".equalsIgnoreCase(resultMessage)){
					String resultJson =cs.getString(5);
					resultMap.put(resultMessage, GsonUtils.jsonToObject(resultJson, ElementLov.class));
				}else{
					resultMap.put(resultMessage, null);
				}
				logger.info("<-----------getElementLov----------"+resultMessage);
				return resultMap;
			}
		});
	}
	//cux_hr_phfsi_rec_api_pkg.get_people_group_lov
	/*p_rows           IN VARCHAR2,
	p_pages          IN VARCHAR2,
	p_session_id     IN VARCHAR2,
	p_people_group_name       IN VARCHAR2,
	p_json           OUT CLOB,
	p_message        OUT VARCHAR2*/
	
	public Map<String,EpeopleGroupLov> getPeopleGroupLov(final String rows,final String pages,final String sessionId,final String peopleGroupName){
		logger.info("-----------getPeopleGroupLov---------->rows:"+rows+",pages:"+pages+",sessionId:"+sessionId+",peopleGroupName:"+peopleGroupName);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_people_group_lov(?,?,?,?,?,?)}");
				cs.setString(1, rows);
				cs.setString(2, pages);
				cs.setString(3, sessionId);
				cs.setString(4, peopleGroupName);
				cs.registerOutParameter(5, Types.CLOB);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,EpeopleGroupLov>>() {
			@Override
			public Map<String, EpeopleGroupLov> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,EpeopleGroupLov> resultMap= new HashMap<String,EpeopleGroupLov>();
				String resultMessage =cs.getString(6);
				if("SUCCESS".equalsIgnoreCase(resultMessage)){
					String resultJson =cs.getString(5);
					resultMap.put(resultMessage, GsonUtils.jsonToObject(resultJson, EpeopleGroupLov.class));
				}else{
					resultMap.put(resultMessage, null);
				}
				logger.info("<-----------getPeopleGroupLov----------"+resultMessage);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_cost_type_lov
	/*p_session_id     IN VARCHAR2,
	p_cost_type_name       IN VARCHAR2,
	p_json           OUT CLOB,
	p_message        OUT VARCHAR2*/
	public Map<String,List<CostTypeLov>> getCostTypeLov(final String sessionId,final String cosTypeName){
		logger.info("----------getCostTypeLov---------->SessionId:"+sessionId+";cosTypeName:"+cosTypeName);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_type_lov(?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, cosTypeName);
				cs.registerOutParameter(3, Types.CLOB);
				cs.registerOutParameter(4, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,List<CostTypeLov>>>() {
			@Override
			public Map<String,List<CostTypeLov>> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,List<CostTypeLov>> resultMap= new HashMap<String,List<CostTypeLov>>();
				String resultMessage =cs.getString(4);
				if("SUCCESS".equalsIgnoreCase(resultMessage)){
					String resultJson =cs.getString(3);
					resultMap.put(resultMessage, GsonUtils.jsonToObjectList(resultJson, CostTypeLov.class));
				}else{
					resultMap.put(resultMessage, null);
				}
				logger.info("<-----------getCostTypeLov----------"+resultMessage);
				return resultMap;
			}
		});
	}
	
/*	p_rows           IN VARCHAR2,
	p_pages          IN VARCHAR2,
	p_session_id     IN VARCHAR2,
	p_value_set_name IN VARCHAR2,
	p_value_meaning  IN VARCHAR2,
	p_json           OUT CLOB,
	p_message        OUT VARCHAR2*/
	
	//cux_hr_phfsi_rec_api_pkg.get_value_set_com_lov
	public Map<String,ValueSetComLov> getValueSetComLov(final String rows,final String pages,final String sessionId,final String valueSetName,final String valueMeaning){
		logger.info("-----------getValueSetComLov---------->rows:"+rows+",pages:"+pages+",sessionId:"+sessionId+",valueSetName:"+valueSetName+",valueMeaning:"+valueMeaning);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_value_set_com_lov(?,?,?,?,?,?,?)}");
				cs.setString(1, rows);
				cs.setString(2, pages);
				cs.setString(3, sessionId);
				cs.setString(4, valueSetName);
				cs.setString(5, valueMeaning);
				cs.registerOutParameter(6, Types.CLOB);
				cs.registerOutParameter(7, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,ValueSetComLov>>() {
			@Override
			public Map<String,ValueSetComLov> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,ValueSetComLov> resultMap= new HashMap<String,ValueSetComLov>();
				String resultMessage =cs.getString(7);
				if("SUCCESS".equalsIgnoreCase(resultMessage)){
					String resultJson =cs.getString(6);
					resultMap.put(resultMessage, GsonUtils.jsonToObject(resultJson, ValueSetComLov.class));
				}else{
					resultMap.put(resultMessage, null);
				}
				logger.info("<-----------getValueSetComLov----------"+resultMessage);
				return resultMap;
			}
		});
	}
	
    //cux_hr_phfsi_rec_api_pkg.get_cost_status_lov
	
	/*p_session_id       IN VARCHAR2,
	p_cost_status_name IN VARCHAR2,
	p_json             OUT CLOB,
	p_message */
	public Map<String, List<CostStatusServicesBean>> getCostStatusLov(final String sessionId,final String costStatusName){
		logger.info("-----------getCostStatusLov---------->sessionId:"+sessionId+",costStatusName:"+costStatusName);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_status_lov(?,?,?,?)}");
				 statement.setString(1, sessionId);
				 statement.setString(2, costStatusName);
				 statement.registerOutParameter(3, Types.CLOB);
				 statement.registerOutParameter(4, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback< Map<String, List<CostStatusServicesBean>>>() {
			@Override
			public  Map<String, List<CostStatusServicesBean>> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(4);
				 Map<String, List<CostStatusServicesBean>> result = new HashMap<String, List<CostStatusServicesBean>>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(3);//ClobUtils.clobToString(cs.getClob(2));
					List<CostStatusServicesBean> value =GsonUtils.jsonToObjectList(json,CostStatusServicesBean.class);
					result.put(message, value);
				}else{
					result.put(message, null);
				}
				logger.info("<-----------getCostStatusLov----------"+message);
				return result;
			}
		});
		
	}

	
}
