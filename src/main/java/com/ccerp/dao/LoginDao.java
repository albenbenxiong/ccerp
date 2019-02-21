package com.ccerp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class LoginDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public String doLogin(final String userName,final String password){
		logger.info("------doLogin----->UserName:"+userName+",password:"+password);
		return  jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement callablesStatement =con.prepareCall("{call cux_hr_global_comm_pkg.valid_login(?,?,?)}");
				callablesStatement.setString(1, userName);
				callablesStatement.setString(2, password);
				callablesStatement.registerOutParameter(3, Types.VARCHAR);
				return callablesStatement;
			}}, new CallableStatementCallback<String>(){
				@Override
				public String doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					cs.execute();
					String result =cs.getString(3);
					logger.info("<------doLogin------------"+result);
					return result;
				}
		});
	}
	
	
	public String changePassword(final String userName,final String oldPwd,final String newPwdInput1,final String newPwdInput2){
		logger.info("------changePassword----->UserName:"+userName+",oldPwd:"+oldPwd+",newPwdInput1:"+newPwdInput1+",newPwdInput2:"+newPwdInput2);
		return  jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement callablesStatement =con.prepareCall("{call cux_hr_global_comm_pkg.change_password(?,?,?,?,?)}");
				callablesStatement.setString(1, userName);
				callablesStatement.setString(2, oldPwd);
				callablesStatement.setString(3, newPwdInput1);
				callablesStatement.setString(4, newPwdInput2);
				callablesStatement.registerOutParameter(5, Types.VARCHAR);
				return callablesStatement;
			}}, new CallableStatementCallback<String>(){
				@Override
				public String doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					cs.execute();
					String result =cs.getString(5);
					logger.info("<-------changePassword-----------"+result);
					return result;
				}
		});
		
	}
	
	
}
