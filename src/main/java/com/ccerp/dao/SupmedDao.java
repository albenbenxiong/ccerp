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
import com.ccerp.bean.condation.SupmedCondition;
import com.ccerp.bean.services.SupmedServicesBean;
import com.ccerp.utils.GsonUtils;


@Repository
public class SupmedDao {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	
	public Map<String,String> supmedDataAdd(final SupmedCondition condition){
		logger.info("------supmedDataAdd--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_supmed_data_add(?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getPeriod());
				cs.setString(3, condition.getPayOrg2Code());
				cs.setString(4, condition.getOrg2Code());
				cs.setString(5, condition.getPersonId());
				cs.setString(6, condition.getSupmedicalEe());
				cs.setString(7, condition.getSupmedicalEr());
				cs.registerOutParameter(8, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {

			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(8);
				resultMap.put(result, null);
				logger.info("<------supmedDataAdd---------"+result);
				return resultMap;
			}
		});
	}
	public Map<String,String> supmedDataDel(final String sessionId,final String supmedId){
		logger.info("------supmedDataDel--------->sessionId:"+sessionId+",supmedId:"+supmedId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_supmed_data_del(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, supmedId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(3);
				resultMap.put(result, null);
				logger.info("<------supmedDataDel---------"+result);
				return resultMap;
			}
		});
	}
	
	
	public Map<String,String> supmedDataUpdate(final SupmedCondition condition){
		logger.info("------supmedDataUpdate--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_supmed_data_upd(?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getSupmedId());
				cs.setString(3, condition.getPeriod());
				cs.setString(4, condition.getPayOrg2Code());
				cs.setString(5, condition.getOrg2Code());
				cs.setString(6, condition.getPersonId());
				cs.setString(7, condition.getSupmedicalEe());
				cs.setString(8, condition.getSupmedicalEr());
				cs.registerOutParameter(9, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(9);
				resultMap.put(result, null);
				logger.info("<------supmedDataUpdate---------"+result);
				return resultMap;
			}
		});
	}
	
	public Map<String,SupmedServicesBean> supmedDataInfo(final SupmedCondition condition){
		logger.info("------supmedDataInfo--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_supmed_data_info(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
		}, new CallableStatementCallback<Map<String,SupmedServicesBean>>() {
			@Override
			public Map<String, SupmedServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, SupmedServicesBean> resultMap = new HashMap<String, SupmedServicesBean>();
				cs.execute();
				String result = cs.getString(13);
				if ("SUCCESS".equalsIgnoreCase(result)) {
					String  json =cs.getString(12);//ClobUtils.clobToString(cs.getClob(2));
					SupmedServicesBean ssb=GsonUtils.jsonToObject(json, SupmedServicesBean.class);
					resultMap.put(result, ssb);
					
				} else {
					resultMap.put(result, null);
				}
				logger.info("<------supmedDataInfo---------"+result);
				return resultMap;
			}
		});
	}
	
	public Map<String,String> supmedDataCopy(final String sessionId,final String periodFrom,final String periodTo,final String payOrg2Code){
		logger.info("------supmedDataCopy--------->sessionId:"+sessionId+",periodFrom:"+periodFrom+",periodTo:"+periodTo+",payOrg2Code:"+payOrg2Code);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_supmed_data_copy(?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, periodFrom);
				cs.setString(3, periodTo);
				cs.setString(4, payOrg2Code);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(5);
				resultMap.put(result, null);
				logger.info("<------supmedDataCopy---------"+result);
				return resultMap;
			}
		});
	}
	
	public Map<String,SupmedServicesBean> supmedHisDataInfo(final BaseCondition condition){
		logger.info("------supmedHisDataInfo--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_supmed_his_data_info(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
		}, new CallableStatementCallback<Map<String,SupmedServicesBean>>() {
			@Override
			public Map<String, SupmedServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, SupmedServicesBean> resultMap = new HashMap<String, SupmedServicesBean>();
				cs.execute();
				String result = cs.getString(14);
				if ("SUCCESS".equalsIgnoreCase(result)) {
					String  json =cs.getString(13);//ClobUtils.clobToString(cs.getClob(2));
					SupmedServicesBean ssb=GsonUtils.jsonToObject(json, SupmedServicesBean.class);
					resultMap.put(result, ssb);
				} else {
					resultMap.put(result, null);
				}
				logger.info("<------supmedHisDataInfo---------"+result);
				return resultMap;
			}
		});
	}
	
	
	
	
	
}
