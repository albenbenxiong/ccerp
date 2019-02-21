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

import com.ccerp.bean.condation.BaseCondition;
import com.ccerp.bean.condation.PhfCondition;
import com.ccerp.bean.services.PhfServicesBean;
import com.ccerp.utils.GsonUtils;

@Repository
public class PhfDao {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	
	public Map<String,String> phfDataAdd(final PhfCondition condition){
		logger.info("------phfDataAdd--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_phf_data_add(?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getPeriod());
				cs.setString(3, condition.getPayOrg2Code());
				cs.setString(4, condition.getOrg2Code());
				cs.setString(5, condition.getPersonId());
				cs.setString(6, condition.getPhfEe());
				cs.setString(7, condition.getPhfEr());
				cs.registerOutParameter(8, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String> >() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result =cs.getString(8);
				resultMap.put(result, null);
				logger.info("<------phfDataAdd---------"+result);
				return resultMap;
			}
		});
	}
	
	
	
	public Map<String,String> phfDataDel(final String sessionId,final String phfId){
		logger.info("-----------phfDataDel--------->sessionId:"+sessionId+",phfId:"+phfId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_phf_data_del(?,?,?)}");
				cs.setString(1,sessionId);
				cs.setString(2, phfId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String> >() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result =cs.getString(3);
				resultMap.put(result, null);
				logger.info("<------phfDataDel---------"+result);
				return resultMap;
			}
		});
	} 
	
	public Map<String,String> phfDataUpdate(final PhfCondition condition){
		logger.info("-----------phfDataUpdate--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_phf_data_upd(?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getPhfId());
				cs.setString(3, condition.getPeriod());
				cs.setString(4, condition.getPayOrg2Code());
				cs.setString(5, condition.getOrg2Code());
				cs.setString(6, condition.getPersonId());
				cs.setString(7, condition.getPhfEe());
				cs.setString(8, condition.getPhfEr());
				cs.registerOutParameter(9, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String> >() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result =cs.getString(9);
				resultMap.put(result, null);
				logger.info("<------phfDataUpdate---------"+result);
				return resultMap;
			}
		});
	}
	
	
	public Map<String,PhfServicesBean> phfDataInfo(final PhfCondition condition){
		
		logger.info("-----------phfDataInfo--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_phf_data_info(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getRows());
				cs.setString(2, condition.getPages());
				cs.setString(3, condition.getSessionId());
				cs.setString(4, condition.getPeriod());
				cs.setString(5, condition.getPayOrg2Code());
				cs.setString(6, condition.getOrg2Code());
				cs.setString(7, condition.getOrgId());
				cs.setString(8, condition.getEmpName());
				cs.setString(9, condition.getEmpNumber());
				cs.setString(10, condition.getStatus());
				cs.setString(11, condition.getCheckStatus());
				cs.registerOutParameter(12, Types.CLOB);
				cs.registerOutParameter(13, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,PhfServicesBean> >() {
			@Override
			public Map<String,PhfServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(13);
				Map<String,PhfServicesBean> resultMap = new HashMap<String, PhfServicesBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					String  json =cs.getString(12);//ClobUtils.clobToString(cs.getClob(2));
					PhfServicesBean ssb=GsonUtils.jsonToObject(json, PhfServicesBean.class);
					resultMap.put(result, ssb);
				}else{
					resultMap.put(result, null);
				}
				logger.info("<--------phfDataInfo---------"+result);
				return resultMap;
			}
		});
	}
	public Map<String,String> phfDataCopy(final String sessionId,final String periodFrom,final String periodTo,final String payOrg2Code){
		logger.info("------phfDataCopy--------->sessionId:"+sessionId+",periodFrom:"+periodFrom+",periodTo:"+periodTo+",payOrg2Code:"+payOrg2Code);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_phf_data_copy(?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, periodFrom);
				cs.setString(3, periodTo);
				cs.setString(4, payOrg2Code);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String> >() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result =cs.getString(5);
				resultMap.put(result, null);
			    logger.info("<--------phfDataCopy---------"+result);
				return resultMap;
			}
		});
	}
	public Map<String,PhfServicesBean> phfHisDataInfo(final BaseCondition condition){
		logger.info("------phfHisDataInfo--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_phfsi_his_data_info(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getRows());
				cs.setString(2, condition.getPages());
				cs.setString(3, condition.getSessionId());
				cs.setString(4, condition.getPeriodFrom());
				cs.setString(5, condition.getPeriodTo());
				cs.setString(6, condition.getPayOrg2Code());
				cs.setString(7, condition.getOrg2Code());
				cs.setString(8, condition.getOrgId());
				cs.setString(9, condition.getEmpName());
				cs.setString(10, condition.getEmpNumber());
				cs.setString(11, condition.getStatus());
				cs.setString(12, condition.getCheckStatus());
				cs.registerOutParameter(13, Types.CLOB);
				cs.registerOutParameter(14, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,PhfServicesBean> >() {
			@Override
			public Map<String, PhfServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(14);
				Map<String,PhfServicesBean> resultMap = new HashMap<String, PhfServicesBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					String  json =cs.getString(13);//ClobUtils.clobToString(cs.getClob(2));
					PhfServicesBean ssb=GsonUtils.jsonToObject(json, PhfServicesBean.class);
					resultMap.put(result, ssb);
				}else{
					resultMap.put(result, null);
				}
				logger.info("<--------phfHisDataInfo---------"+result);
				return resultMap;
			}
		});
	}
	
	
	
}
