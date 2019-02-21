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
import com.ccerp.bean.condation.SocinsCondition;
import com.ccerp.bean.services.SocinsServicesBean;
import com.ccerp.utils.GsonUtils;

@Repository
public class SocinsDao {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, SocinsServicesBean> socinsDataInfo(final SocinsCondition condition) {
		logger.info("------socinsInfo--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement ct = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_socins_data_info(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				ct.setString(1, condition.getRows());
				ct.setString(2, condition.getPages());
				ct.setString(3, condition.getSessionId());
				ct.setString(4, condition.getPeriod());
				ct.setString(5, condition.getPayOrg2Code());
				ct.setString(6, condition.getOrg2Code());
				ct.setString(7, condition.getOrgId());
				ct.setString(8, condition.getEmpName());
				ct.setString(9, condition.getEmpNumber());
				ct.setString(10, condition.getStatus());
				ct.setString(11, condition.getCheckStatus());
				ct.registerOutParameter(12, Types.CLOB);
				ct.registerOutParameter(13, Types.VARCHAR);
				return ct;
			}
		}, new CallableStatementCallback<Map<String, SocinsServicesBean>>() {
			@Override
			public Map<String, SocinsServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result = cs.getString(13);
				Map<String, SocinsServicesBean> mapssb = new HashMap<String, SocinsServicesBean>();
				if ("SUCCESS".equalsIgnoreCase(result)) {
					String json = cs.getString(12);// ClobUtils.clobToString(cs.getClob(2));
					SocinsServicesBean ssb = GsonUtils.jsonToObject(json,SocinsServicesBean.class);
					mapssb.put(result, ssb);
				} else {
					mapssb.put(result, null);
				}
				logger.info("<------socinsInfo---------"+result);
				return mapssb;
			}
		});
	}

	public Map<String, String> socinsDataCopy(final String sessionId,final String periodFrom, final String periodTo,final String payOrg2Code) {
		logger.info("------socinsDataCopy--------->sessionId:"+sessionId+",periodFrom:"+periodFrom+",periodTo:"+periodTo+",payOrg2Code:"+payOrg2Code);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call  cux_hr_phfsi_rec_api_pkg.get_socins_data_copy(?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, periodFrom);
				cs.setString(3, periodTo);
				cs.setString(4, payOrg2Code);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(5);
				resultMap.put(result, null);
				logger.info("<------socinsDataCopy---------"+result);
				return resultMap;
			}
		});
	}

	public Map<String, String> socinsDataDel(final String sessionId,final String socinsId) {
		logger.info("------socinsDataDel--------->sessionId:"+sessionId+",socinsId:"+socinsId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call  cux_hr_phfsi_rec_api_pkg.get_socins_data_del(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, socinsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(3);
				resultMap.put(result, null);
				logger.info("<------socinsDataDel---------"+result);
				return resultMap;
			}
		});
	}

	public Map<String, String> socinsDataAdd(final SocinsCondition condition) {
		logger.info("------socinsDataAdd--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call  cux_hr_phfsi_rec_api_pkg.get_socins_data_add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getPeriod());
				cs.setString(3, condition.getPayOrg2Code());
				cs.setString(4, condition.getOrg2Code());
				cs.setString(5, condition.getPersonId());
				cs.setString(6, condition.getEndowmentEe());
				cs.setString(7, condition.getMedicalEe());
				cs.setString(8, condition.getUnempEe());
				cs.setString(9, condition.getLarmedEe());
				cs.setString(10, condition.getEndowmentEr());
				cs.setString(11, condition.getMedicalEr());
				cs.setString(12, condition.getUnempEr());
				cs.setString(13, condition.getInjEr());
				cs.setString(14, condition.getMaterEr());
				cs.setString(15, condition.getLarmedEr());
				cs.registerOutParameter(16, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(16);
			    resultMap.put(result, null);
				logger.info("<------socinsDataAdd---------"+result);
				return resultMap;
			}
		});
	}

	public Map<String, String> socinsDataUpdate(final SocinsCondition condition) {
		logger.info("------socinsDataUpdate--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_socins_data_upd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, condition.getSessionId());
				cs.setString(2, condition.getSocinsId());
				cs.setString(3, condition.getPeriod());
				cs.setString(4, condition.getPayOrg2Code());
				cs.setString(5, condition.getOrg2Code());
				cs.setString(6, condition.getPersonId());
				cs.setString(7, condition.getEndowmentEe());
				cs.setString(8, condition.getMedicalEe());
				cs.setString(9, condition.getUnempEe());
				cs.setString(10, condition.getLarmedEe());
				cs.setString(11, condition.getEndowmentEr());
				cs.setString(12, condition.getMedicalEr());
				cs.setString(13, condition.getUnempEr());
				cs.setString(14, condition.getInjEr());
				cs.setString(15, condition.getMaterEr());
				cs.setString(16, condition.getLarmedEr());
				cs.registerOutParameter(17, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				Map<String, String> resultMap = new HashMap<String, String>();
				cs.execute();
				String result = cs.getString(17);
			    resultMap.put(result, null);
				logger.info("<------socinsDataUpdate---------"+result);
				return resultMap;
			}
		});
	}

	public Map<String, SocinsServicesBean> socinsHisDataInfo(final BaseCondition condition) {
		logger.info("------socinsHisDataInfo--------->condition:"+condition);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement ct = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_socins_his_data_info(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				ct.setString(1, condition.getRows());
				ct.setString(2, condition.getPages());
				ct.setString(3, condition.getSessionId());
				ct.setString(4, condition.getPeriodFrom());
				ct.setString(5, condition.getPeriodTo());
				ct.setString(6, condition.getPayOrg2Code());
				ct.setString(7, condition.getOrg2Code());
				ct.setString(8, condition.getOrgId());
				ct.setString(9, condition.getEmpName());
				ct.setString(10, condition.getEmpNumber());
				ct.setString(11, condition.getStatus());
				ct.setString(12, condition.getCheckStatus());
				ct.registerOutParameter(13, Types.CLOB);
				ct.registerOutParameter(14, Types.VARCHAR);
				return ct;
			}
		}, new CallableStatementCallback<Map<String, SocinsServicesBean>>() {
			@Override
			public Map<String, SocinsServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result = cs.getString(14);
				Map<String, SocinsServicesBean> mapssb = new HashMap<String, SocinsServicesBean>();
				if ("SUCCESS".equalsIgnoreCase(result)) {
					String json = cs.getString(13);// ClobUtils.clobToString(cs.getClob(2));
					SocinsServicesBean ssb = GsonUtils.jsonToObject(json,SocinsServicesBean.class);
					mapssb.put(result, ssb);
				} else {
					mapssb.put(result, null);
				}
				logger.info("<------socinsHisDataInfo---------"+result);
				return mapssb;
			}
		});

	}

}
