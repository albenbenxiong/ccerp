package com.ccerp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ccerp.bean.condation.CostConCondition;
import com.ccerp.bean.services.CostConServicesBean;
import com.ccerp.utils.GsonUtils;

@Repository
public class CostConDao {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public Map<String,String> costConDel(final String  sessionId,final String costConid){
		logger.info("-------------costConDel------------->sessionId:"+sessionId+",costConId:"+costConid);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_con_del(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, costConid);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(3);
				Map<String,String> resultMap = new HashMap<String, String>();
				resultMap.put(result, null);
				logger.info("<-------------costConDel-------------message:"+result);
				return resultMap;
			}
		});
	}
	
	
	public Map<String,String> costConAdd(final CostConCondition condition){
		logger.info("-------------costConAdd------------->sessionId:"+condition.getSessionId()+",payOrg2Code:"+condition.getPayOrg2Code()+",personId:"+condition.getPersonId()+",contractCode:"+condition.getContractCode());
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_con_add(?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getPayOrg2Code());
				cs.setString(3, condition.getPersonId());
				cs.setString(4, condition.getContractCode());
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,String>>(){
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(5);
				Map<String,String> resultMap = new HashMap<String, String>();
				resultMap.put(result, null);
				logger.info("<-------------costConAdd-------------message:"+result);
				return resultMap;
			}
		});
	}
	
	public Map<String,CostConServicesBean> costConInfo(final CostConCondition condition){
		logger.info("-------------costConInfo------------->condition:"+condition);

		return jdbcTemplate.execute(new CallableStatementCreator(){
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_con_info(?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getRows());
				cs.setString(2, condition.getPages());
				cs.setString(3, condition.getSessionId());
				cs.setString(4, condition.getPayOrg2Code());
				cs.setString(5, condition.getOrgId());
				cs.setString(6, condition.getEmpNumber());
				cs.setString(7, condition.getEmpName());
				cs.registerOutParameter(8, Types.CLOB);
				cs.registerOutParameter(9, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,CostConServicesBean>>() {
			@Override
			public Map<String, CostConServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(9);
				Map<String,CostConServicesBean> resultMap = new HashMap<String, CostConServicesBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					resultMap.put(result, GsonUtils.jsonToObject(cs.getString(8), CostConServicesBean.class));
				}else{
					resultMap.put(result, null);
				}
				logger.info("<-------------costConInfo-------------message:"+result);
				return resultMap;
			}
		});
	}
	
}
