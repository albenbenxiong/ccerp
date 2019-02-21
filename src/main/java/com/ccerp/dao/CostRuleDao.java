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

import com.ccerp.bean.condation.CostRuleCondition;
import com.ccerp.bean.services.CostRuleServicesBean;
import com.ccerp.bean.services.CostRuleUpdateBean;
import com.ccerp.utils.GsonUtils;

@Repository
public class CostRuleDao {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Map<String,String> costRuleSetDel(final String sessionId,final String ruleId){
		logger.info("------costRuleSetDel----->sessionId:"+sessionId+",ruleId:"+ruleId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_rule_set_del(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, ruleId);
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
				logger.info("<------costRuleSetDel-----message:"+result);
				return resultMap;
			}
		});
	}
	
	
	public Map<String,String> costRuleSetAdd(final CostRuleCondition condition){
		
		logger.info("------costRuleSetAdd----->CostRuleCondition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs=con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_rule_set_add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getCostType());
				cs.setString(3, condition.getPayOrg2Code());
				cs.setString(4, condition.getComempFlag());
				cs.setString(5, condition.getPeopleGroupId());
				cs.setString(6, condition.getCostedFlag());
				cs.setString(7, condition.getElementCode());
				cs.setString(8, condition.getDsegment1());
				cs.setString(9, condition.getDsegment2());
				cs.setString(10, condition.getDsegment3());
				cs.setString(11, condition.getDsegment4());
				cs.setString(12, condition.getDsegment5());
				cs.setString(13, condition.getDsegment6());
				cs.setString(14, condition.getDsegment7());
				cs.setString(15, condition.getDsegment8());
				cs.setString(16, condition.getDsegment9());
				cs.setString(17, condition.getDsegment10());
				cs.setString(18, condition.getCsegment1());
				cs.setString(19, condition.getCsegment2());
				cs.setString(20, condition.getCsegment3());
				cs.setString(21, condition.getCsegment4());
				cs.setString(22, condition.getCsegment5());
				cs.setString(23, condition.getCsegment6());
				cs.setString(24, condition.getCsegment7());
				cs.setString(25, condition.getCsegment8());
				cs.setString(26, condition.getCsegment9());
				cs.setString(27, condition.getCsegment10());
				cs.setString(28, condition.getDsegment2Ow());
				cs.setString(29, condition.getDsegment8Ow());
				cs.setString(30, condition.getDsegment9Ow());
				cs.setString(31, condition.getCsegment2Ow());
				cs.setString(32, condition.getCsegment8Ow());
				cs.setString(33, condition.getCsegment9Ow());
				cs.registerOutParameter(34, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(34);
				Map<String,String> resultMap =new HashMap<String, String>();
				resultMap.put(result, null);
				logger.info("<------costRuleSetAdd-----message:"+result);
				return resultMap;
			}
		});
		
	}
	

	public Map<String,String> costRuleSetUpdate(final CostRuleCondition condition){
		logger.info("------costRuleSetUpdate----->CostRuleCondition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs=con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_rule_set_upd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getRuleId());
				cs.setString(3, condition.getCostType());
				cs.setString(4, condition.getPayOrg2Code());
				cs.setString(5, condition.getComempFlag());
				cs.setString(6, condition.getPeopleGroupId());
				cs.setString(7, condition.getCostedFlag());
				cs.setString(8, condition.getElementCode());
				cs.setString(9, condition.getDsegment1());
				cs.setString(10, condition.getDsegment2());
				cs.setString(11, condition.getDsegment3());
				cs.setString(12, condition.getDsegment4());
				cs.setString(13, condition.getDsegment5());
				cs.setString(14, condition.getDsegment6());
				cs.setString(15, condition.getDsegment7());
				cs.setString(16, condition.getDsegment8());
				cs.setString(17, condition.getDsegment9());
				cs.setString(18, condition.getDsegment10());
				cs.setString(19, condition.getCsegment1());
				cs.setString(20, condition.getCsegment2());
				cs.setString(21, condition.getCsegment3());
				cs.setString(22, condition.getCsegment4());
				cs.setString(23, condition.getCsegment5());
				cs.setString(24, condition.getCsegment6());
				cs.setString(25, condition.getCsegment7());
				cs.setString(26, condition.getCsegment8());
				cs.setString(27, condition.getCsegment9());
				cs.setString(28, condition.getCsegment10());
				cs.setString(29, condition.getDsegment2Ow());
				cs.setString(30, condition.getDsegment8Ow());
				cs.setString(31, condition.getDsegment9Ow());
				cs.setString(32, condition.getCsegment2Ow());
				cs.setString(33, condition.getCsegment8Ow());
				cs.setString(34, condition.getCsegment9Ow());
				cs.registerOutParameter(35, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(35);
				Map<String,String> resultMap =new HashMap<String, String>();
				resultMap.put(result, null);
				logger.info("<------costRuleSetUpdate-----message:"+result);
				return resultMap;
			}
		});
		
	}
	
	public Map<String,CostRuleServicesBean> costRuleSetInfo(final CostRuleCondition condition){
		logger.info("------costRuleSetInfo----->CostRuleCondition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_rule_set_info(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1,condition.getRows());
				cs.setString(2, condition.getPages());
				cs.setString(3, condition.getSessionId());
				cs.setString(4, condition.getPayOrg2Code());
				cs.setString(5, condition.getCostType());
				cs.setString(6, condition.getPeopleGroupId());
				cs.setString(7, condition.getElementCode());
				cs.setString(8, condition.getComempFlag());
				cs.registerOutParameter(9, Types.CLOB);
				cs.registerOutParameter(10, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,CostRuleServicesBean>>() {
			@Override
			public Map<String, CostRuleServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(10);
				Map<String,CostRuleServicesBean> resultMap = new HashMap<String, CostRuleServicesBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					String resultJson =cs.getString(9);
					resultMap.put(result, GsonUtils.jsonToObject(resultJson, CostRuleServicesBean.class));
				}else{
					resultMap.put(result, null);
				}
				logger.info("<------costRuleSetInfo-----message:"+result);
				return resultMap;
			}
		});
	}
	
	
	
	//cux_hr_phfsi_rec_api_pkg.get_cost_rule_set_updinfo
	
	/*p_session_id IN VARCHAR2,
	p_rule_id    IN VARCHAR2,
	p_json       OUT CLOB,
	p_message    OUT VARCHAR2*/
	public Map<String,CostRuleUpdateBean> getCostRuleSetUpdInfo(final String sessionId,final String ruleId ){
		logger.info("------getCostRuleSetUpdInfo----->sessionId:"+sessionId+",ruleId:"+ruleId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {                    
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_rule_set_updinfo(?,?,?,?)}");
				cs.setString(1,sessionId);
				cs.setString(2,ruleId);
				cs.registerOutParameter(3, Types.CLOB);
				cs.registerOutParameter(4, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,CostRuleUpdateBean>>() {
			@Override
			public Map<String, CostRuleUpdateBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(4);
				Map<String,CostRuleUpdateBean> resultMap = new HashMap<String, CostRuleUpdateBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					String resultJson =cs.getString(3);
					logger.info("------json----->resultJson:"+resultJson);
					resultMap.put(result, GsonUtils.jsonToObject(resultJson, CostRuleUpdateBean.class));
				}else{
					resultMap.put(result, null);
				}
				logger.info("<------getCostRuleSetUpdInfo-----message:"+result);
				return resultMap;
			}
		});
	}
	
}
