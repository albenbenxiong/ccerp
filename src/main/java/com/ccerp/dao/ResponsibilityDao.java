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

import com.ccerp.bean.services.CustomMenu;
import com.ccerp.bean.services.Responsibility;
import com.ccerp.utils.GsonUtils;
@Repository
public class ResponsibilityDao {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Map<String,List<Responsibility>> findRespList(final String userName){
		logger.info("-----------findRespList-------------->:"+userName);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_global_comm_pkg.get_resp_list(?,?,?)}");
				 statement.setString(1, userName);
				 statement.registerOutParameter(2, Types.CLOB);
				 statement.registerOutParameter(3, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,List<Responsibility>>>() {
			@Override
			public Map<String,List<Responsibility>> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				Map<String,List<Responsibility>> returnMap = new HashMap<String, List<Responsibility>>();
				String  message =cs.getString(3);
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(2);//ClobUtils.clobToString(cs.getClob(2));
					List<Responsibility> resultList=GsonUtils.jsonToObjectList(json, Responsibility.class);
					returnMap.put(message, resultList);
				}else{
					returnMap.put(message, null);
				}
				logger.info("<----------findRespList----------"+message);
				return returnMap;
			}
		});
		
	}
	
	public Map<String,String> changeResponsibility(final String userName,final String respCode){
		logger.info("----------changeResponsibility-------------->userName:"+userName+"respCode:"+respCode);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_global_comm_pkg.set_session(?,?,?,?)}");
				 statement.setString(1, userName);
				 statement.setString(2, respCode);
				 statement.registerOutParameter(3, Types.CLOB);
				 statement.registerOutParameter(4, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String,String> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(4);
				Map<String,String> resultmap= new HashMap<String,String>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(3);//ClobUtils.clobToString(cs.getClob(2));
					Map<String,String> result1 =GsonUtils.jsonToMap(json);
					String result=result1.get(result1.keySet().iterator().next());
					resultmap.put(message, result);
				}else{
					resultmap.put(message,null);
				}
				logger.info("<----------changeResponsibility----------"+message);
				return resultmap;
			}
		});
		
	}
	
	
	public Map<String,String> getCurPeriod(){
		logger.info("----------getCurPeriod-------------->");
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_global_comm_pkg.get_cur_period(?,?)}");
				 statement.registerOutParameter(1, Types.CLOB);
				 statement.registerOutParameter(2, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String,String> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(2);
				Map<String,String> resultmap= new HashMap<String,String>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(1);//ClobUtils.clobToString(cs.getClob(2));
					Map<String,String> result1 =GsonUtils.jsonToMap(json);
					String result=result1.get(result1.keySet().iterator().next());
					resultmap.put(message, result);
				}else{
					resultmap.put(message,null);
				}
				logger.info("<----------getCurPeriod----------"+message);
				return resultmap;
			}
		});
	}
	
	// 
	/*p_resp_id IN VARCHAR2,
	p_app_id  IN VARCHAR2,
	p_json    OUT CLOB,
	p_message OUT VARCHAR2*/
	public Map<String,String> getMenulList(final String respId, final String appId){
		logger.info("----------getMenulList-------------->");
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_global_comm_pkg.get_menu_list(?,?,?,?)}");
				 statement.setString(1, respId);
				 statement.setString(2, appId);
				 statement.registerOutParameter(3, Types.CLOB);
				 statement.registerOutParameter(4, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String,String> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(4);
				Map<String,String> resultmap= new HashMap<String,String>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(3);//ClobUtils.clobToString(cs.getClob(2));
					Map<String,String> result1 =GsonUtils.jsonToMap(json);
					String result=result1.get(result1.keySet().iterator().next());
					resultmap.put(message, result);
				}else{
					resultmap.put(message,null);
				}
				logger.info("<----------getMenulList----------"+message);
				return resultmap;
			}
		});
	}
	
	
	/*p_rows          IN VARCHAR2,
	p_pages         IN VARCHAR2,
	p_session_id IN NUMBER,
	p_json           OUT CLOB,
	p_message     OUT VARCHAR2*/
	//{"Rows"：[{"Promt":"","URL":""}],"Total":"50"}
	public Map<String,CustomMenu> getCustomMenu(final String rows, final String pages,final String sessionId){
		logger.info("----------getCustomMenu-------------->");
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_icx_custom_menu(?,?,?,?,?)}");
				 statement.setString(1, rows);
				 statement.setString(2, pages);
				 statement.setString(3, sessionId);
				 statement.registerOutParameter(4, Types.CLOB);
				 statement.registerOutParameter(5, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,CustomMenu>>() {
			@Override
			public Map<String,CustomMenu> doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				String  message =cs.getString(5);
				Map<String,CustomMenu> resultmap= new HashMap<String,CustomMenu>();
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(4);//ClobUtils.clobToString(cs.getClob(2));
					CustomMenu result = GsonUtils.jsonToObject(json, CustomMenu.class);
					resultmap.put(message, result);
				}else{
					resultmap.put(message,null);
				}
				logger.info("<----------getMenulList----------"+message);
				return resultmap;
			}
		});
	}
}
