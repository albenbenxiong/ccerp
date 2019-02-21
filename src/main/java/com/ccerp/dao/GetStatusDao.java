package com.ccerp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ccerp.bean.services.status.BaseStatusLov;
import com.ccerp.bean.services.status.CheckStatusLov;
import com.ccerp.bean.services.status.PayStatusLov;
import com.ccerp.utils.GsonUtils;



@Repository
public class GetStatusDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String,List<CheckStatusLov>> getCheckStatus(final String sessionId){
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_check_status_lov(?,?,?)}");
				 statement.setString(1, sessionId);
				 statement.registerOutParameter(2, Types.CLOB);
				 statement.registerOutParameter(3, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,List<CheckStatusLov>>>() {
			@Override
			public Map<String, List<CheckStatusLov>> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,List<CheckStatusLov>> returnMap = new HashMap<String, List<CheckStatusLov>>();
				String  message =cs.getString(3);
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(2);//ClobUtils.clobToString(cs.getClob(2));
					List<CheckStatusLov> resultList=GsonUtils.jsonToObjectList(json, CheckStatusLov.class);
					returnMap.put(message, resultList);
				}else{
					returnMap.put(message, null);
				}
				return returnMap;
			}
		});
	}
	
	public Map<String,List<PayStatusLov>> getPayStatus(final String sessionId){
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_status_lov(?,?,?)}");
				 statement.setString(1, sessionId);
				 statement.registerOutParameter(2, Types.CLOB);
				 statement.registerOutParameter(3, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,List<PayStatusLov>>>() {
			@Override
			public Map<String, List<PayStatusLov>> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,List<PayStatusLov>> returnMap = new HashMap<String, List<PayStatusLov>>();
				String  message =cs.getString(3);
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(2);//ClobUtils.clobToString(cs.getClob(2));
					List<PayStatusLov> resultList=GsonUtils.jsonToObjectList(json, PayStatusLov.class);
					returnMap.put(message, resultList);
				}else{
					returnMap.put(message, null);
				}
				return returnMap;
			}
		});
	}
	public Map<String,List<BaseStatusLov>> getBaseStatus(final String sessionId){
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				 CallableStatement statement =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_status_lov(?,?,?)}");
				 statement.setString(1, sessionId);
				 statement.registerOutParameter(2, Types.CLOB);
				 statement.registerOutParameter(3, Types.VARCHAR);
				 return statement;
			}
		},new CallableStatementCallback<Map<String,List<BaseStatusLov>>>() {
			@Override
			public Map<String, List<BaseStatusLov>> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,List<BaseStatusLov>> returnMap = new HashMap<String, List<BaseStatusLov>>();
				String  message =cs.getString(3);
				if("SUCCESS".equalsIgnoreCase(message)){
					String  json =cs.getString(2);//ClobUtils.clobToString(cs.getClob(2));
					List<BaseStatusLov> resultList=GsonUtils.jsonToObjectList(json, BaseStatusLov.class);
					returnMap.put(message, resultList);
				}else{
					returnMap.put(message, null);
				}
				return returnMap;
			}
		});
	}
}
