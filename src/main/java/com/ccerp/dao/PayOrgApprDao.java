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

import com.ccerp.bean.condation.PayOrgApprCondition;
import com.ccerp.bean.services.Org2OrApprInfoPhfBean;
import com.ccerp.bean.services.Org2OrApprInfoSocinsBean;
import com.ccerp.bean.services.Org2OrApprInfoSupmedBean;
import com.ccerp.bean.services.Org2PayInfo;
import com.ccerp.bean.services.PayOrg2DataViewPhfBean;
import com.ccerp.bean.services.PayOrg2DataViewSocinsBean;
import com.ccerp.bean.services.PayOrg2DataViewSupmedBean;
import com.ccerp.utils.GsonUtils;

@Repository
public class PayOrgApprDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> getPayOrg2Info(final PayOrgApprCondition poa) {
		logger.info("-----------getPayOrg2Info---------->PayOrgApprCondition"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_org2_info(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getStatus());
				cs.registerOutParameter(9, Types.CLOB);
				cs.registerOutParameter(10, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(10);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(9);
					Object object = switchByElementCatalog(poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getPayOrg2Info----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}

			private Object switchByElementCatalog(String p_element_catalog,
					String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSocinsBean poisb = GsonUtils.jsonToObject(
							json, Org2OrApprInfoSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoPhfBean psb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSupmedBean ssb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});

	}


	public Map<String, Object> getPayOrg2DataView(final PayOrgApprCondition poa) {
		
		logger.info("-----------getPayOrg2DataView---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_org2_data_view(?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getOrgId());
				cs.setString(9, poa.getEmpName());
				cs.setString(10, poa.getEmpNumber());
				cs.registerOutParameter(11, Types.CLOB);
				cs.registerOutParameter(12, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(12);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(11);
					Object object = switchDataViewByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getPayOrg2DataView----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}
			private Object switchDataViewByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSocinsBean poisb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewPhfBean psb = GsonUtils.jsonToObject(json,
							PayOrg2DataViewPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSupmedBean ssb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});
	}

	// 代垫单位 确认和取消
	public Map<String, String> getPayOrg2Cf(final String sessionId, final String period,final String payOrg2Code,final String elementCatalog,final String org2Code) {
		logger.info("-----------getPayOrg2Cf---------->sessionId:"+sessionId+",period:"+period+",payOrg2Code:"+payOrg2Code+",elementCatalog:"+elementCatalog);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				/*CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_org2_cf(?,?,?,?,?)}");*/
				
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_phfsi_rec_pub(?,?,?,?,?,?)}");
				
				
				cs.setString(1, sessionId);
				cs.setString(2, period);
				cs.setString(3, payOrg2Code);
				cs.setString(4, org2Code);
				cs.setString(5, elementCatalog);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(6);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(message, null);
				logger.info("<-----------getPayOrg2Cf----------message:"+message+",elementCatalog:"+elementCatalog);
				return resultMap;
			}
		});

	}

	public Map<String, String> getPayOrg2Cancel(final String sessionId, final String period,final String payOrg2Code,final String elementCatalog,final String org2Code) {
		logger.info("-----------getPayOrg2Cancel---------->sessionId:"+sessionId+",period:"+period+",payOrg2Code:"+payOrg2Code+",elementCatalog:"+elementCatalog);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_org2_cancel(?,?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, period);
				cs.setString(3, payOrg2Code);
				cs.setString(4, org2Code);
				cs.setString(5, elementCatalog);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(6);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(message, null);
				logger.info("<-----------getPayOrg2Cancel----------message:"+message+",elementCatalog:"+elementCatalog);
				return resultMap;
			}
		});
	}

	//----------------------------|^发布社保数据以及查询预览 撤回^|-------------------------------
	// 支付
	public Map<String, Object> getOrg2Info(final PayOrgApprCondition poa) {
		logger.info("-----------getOrg2Info---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException { 
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_info(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getStatus());
				cs.registerOutParameter(9, Types.CLOB);
				cs.registerOutParameter(10, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(10);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(9);
					Object object = switchOrg2OrApprInfoByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getOrg2Info----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}

			private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSocinsBean poisb = GsonUtils.jsonToObject(
							json, Org2OrApprInfoSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoPhfBean psb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSupmedBean ssb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});

	}

	public Map<String, Object> getOrg2DataView(final PayOrgApprCondition poa) {
		logger.info("-----------getOrg2DataView---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_data_view(?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getOrgId());
				cs.setString(9, poa.getEmpName());
				cs.setString(10, poa.getEmpNumber());
				cs.registerOutParameter(11, Types.CLOB);
				cs.registerOutParameter(12, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(12);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(11);
					Object object = switchOrg2OrApprInfoByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getOrg2DataView----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}

			private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {

				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSocinsBean poisb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewPhfBean psb = GsonUtils.jsonToObject(json,
							PayOrg2DataViewPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSupmedBean ssb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});

	}
	
	public Map<String,String> getOrg2DataSave(final String sessionId, final String phfsiId, final String elementCatalog, final String checkStatus,final String errMessage){
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_data_save(?,?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, phfsiId);
				cs.setString(3, elementCatalog);
				cs.setString(4, checkStatus);
				cs.setString(5, errMessage);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}},new CallableStatementCallback<Map<String, String>>() {
				@Override
				public Map<String, String> doInCallableStatement(
						CallableStatement cs) throws SQLException,
						DataAccessException {
					cs.execute();
					String resultMessage =cs.getString(6);
					Map<String ,String> result = new HashMap<String ,String>();
					result.put(resultMessage,null);
					return result;
				}});
	}
	
	public Map<String, String> getOrg2Cf(final String sessionId,final String recStatusId,final String org2Comments) {
		logger.info("-----------getOrg2Cf---------->sessionId:"+sessionId+",recStatusId:"+recStatusId+",org2Comments:"+org2Comments);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_cf(?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, recStatusId);
				cs.setString(3, org2Comments);
				cs.registerOutParameter(4, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(4);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(message, null);
				logger.info("<-----------getOrg2Cf----------message:"+message);
				return resultMap;
			}
		});

	}

	public Map<String, String> getOrg2Cancel(final String sessionId,final String recStatusId,final String org2Comments ) {
		logger.info("-----------getOrg2Cancel---------->sessionId:"+sessionId+",recStatusId:"+recStatusId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_cancel(?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, recStatusId);
				cs.setString(3, org2Comments);
				cs.registerOutParameter(4, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(4);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(message, null);
				logger.info("<-----------getOrg2Cancel----------message:"+message);
				return resultMap;
			}
		});

	}
	
	
	//--------------------------|^支付单位查询预览更新 确认取消^|--------------------------

	public Map<String, Object> getApprInfo(final PayOrgApprCondition poa) {
		logger.info("-----------getApprInfo---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException { 
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_appr_det_info(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getStatus());
				cs.setString(9, poa.getPayStatus());
				cs.registerOutParameter(10, Types.CLOB);
				cs.registerOutParameter(11, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(11);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(10);
					Object object = switchOrg2OrApprInfoByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getApprInfo----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}

			private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSocinsBean poisb = GsonUtils.jsonToObject(
							json, Org2OrApprInfoSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoPhfBean psb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSupmedBean ssb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});

	}

	// 集团详细
	public Map<String, Object> getApprDataView(final PayOrgApprCondition poa) {
		logger.info("-----------getApprDataView---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException { 
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_appr_data_view(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getOrgId());
				cs.setString(9, poa.getEmpName());
				cs.setString(10, poa.getEmpNumber());
				cs.setString(11, poa.getComempFlag());
				cs.registerOutParameter(12, Types.CLOB);
				cs.registerOutParameter(13, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(13);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(12);
					Object object = switchOrg2OrApprInfoByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				
				logger.info("<-----------getApprDataView----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}

			private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSocinsBean poisb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewPhfBean psb = GsonUtils.jsonToObject(json,
							PayOrg2DataViewPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSupmedBean ssb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});

	}

	
	public Map<String, String> getApprDataSave(final String sessionId, final String phfsiId, final String elementCatalog,final String checkStatus,final String errMessage){
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_appr_data_save(?,?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, phfsiId);
				cs.setString(3, elementCatalog);
				cs.setString(4, checkStatus);
				cs.setString(5, errMessage);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultMessage =cs.getString(6);
				Map<String,String> result= new HashMap<String,String>();
				result.put(resultMessage, null);
				return result;
			}
		});
	}
	
	
	
	// 集团人力部数据取消确认 支付单位
	public Map<String, String> getApprCf(final String sessionId,final String recStatusId, final String period,final String payOrg2Code,final String org2Code,final String elementCatalog
			,final String payOrg2Comments) {
		logger.info("-----------getApprCf---------->sessionId:"+sessionId+",period:"+period+",payOrg2Code:"+payOrg2Code+",org2Code:"+org2Code);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_appr_cf(?,?,?,?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, recStatusId);
				cs.setString(3, period);
				cs.setString(4, payOrg2Code);
				cs.setString(5, org2Code);
				cs.setString(6, elementCatalog);
				cs.setString(7, payOrg2Comments);
				cs.registerOutParameter(8, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(8);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(message, null);
				logger.info("<-----------getApprCf----------message:"+message+",elementCatalog:"+elementCatalog);
				return resultMap;
			}
		});

	}

	public Map<String, String> getApprCancel(final String sessionId,final String recStatusId, final String period,final String payOrg2Code,final String org2Code,final String elementCatalog
			,final String payOrg2Comments) {
		logger.info("-----------getApprCancel---------->sessionId:"+sessionId+",period:"+period+",payOrg2Code:"+payOrg2Code+",org2Code:"+org2Code);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_appr_cancel(?,?,?,?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, recStatusId);
				cs.setString(3, period);
				cs.setString(4, payOrg2Code);
				cs.setString(5, org2Code);
				cs.setString(6, elementCatalog);
				cs.setString(7, payOrg2Comments);
				cs.registerOutParameter(8, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(8);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(message, null);
				logger.info("<-----------getApprCancel----------message:"+message+",elementCatalog:"+elementCatalog);
				return resultMap;
			}
		});

	}

	public Map<String, Org2PayInfo> getOrg2PayInfo(final PayOrgApprCondition poa) {
		logger.info("-----------getOrg2PayInfo---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException { 
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_pay_info(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriodFrom());
				cs.setString(5, poa.getPeriodTo());
				cs.setString(6, poa.getPayOrg2Code());
				cs.setString(7, poa.getOrg2Code());
				cs.setString(8, poa.getElementCatalog());
				cs.setString(9, poa.getPayStatus());
				cs.registerOutParameter(10, Types.CLOB);
				cs.registerOutParameter(11, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Org2PayInfo>>() {
			@Override
			public Map<String, Org2PayInfo> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(11);
				Map<String, Org2PayInfo> resultMap = new HashMap<String, Org2PayInfo>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(10);
					Org2PayInfo org2PayInfo =GsonUtils.jsonToObject(json,Org2PayInfo.class);
					resultMap.put(message, org2PayInfo);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getOrg2PayInfo----------message:"+message);
				return resultMap;
			}

		/*	private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSocinsBean poisb = GsonUtils.jsonToObject(
							json, Org2OrApprInfoSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoPhfBean psb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSupmedBean ssb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoSupmedBean.class);
					return ssb;
				}
				return null;
			}*/
		});
	}
	public Map<String, Object> getOrg2PayDataView(final PayOrgApprCondition poa) {
		logger.info("-----------getOrg2PayDataView---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_pay_data_view(?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getOrgId());
				cs.setString(9, poa.getEmpName());
				cs.setString(10, poa.getEmpNumber());
				cs.registerOutParameter(11, Types.CLOB);
				cs.registerOutParameter(12, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(12);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(11);
					Object object = switchDataViewByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getOrg2PayDataView----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}

			private Object switchDataViewByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSocinsBean poisb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewPhfBean psb = GsonUtils.jsonToObject(json,
							PayOrg2DataViewPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					PayOrg2DataViewSupmedBean ssb = GsonUtils.jsonToObject(
							json, PayOrg2DataViewSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});

	}

	@Deprecated
	public Map<String, Object> getOrg2ToPay(final PayOrgApprCondition poa) {
		logger.info("-----------getOrg2ToPay---------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException { 
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_to_pay(?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.registerOutParameter(8, Types.CLOB);
				cs.registerOutParameter(9, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String message = cs.getString(9);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(message)) {
					String json = cs.getString(8);
					Object object = switchOrg2OrApprInfoByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getOrg2ToPay----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}
			private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSocinsBean poisb = GsonUtils.jsonToObject(
							json, Org2OrApprInfoSocinsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoPhfBean psb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoPhfBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSupmedBean ssb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoSupmedBean.class);
					return ssb;
				}
				return null;
			}
		});
	}

	
	
	public Map<String, String> getOrg2ToPayCf(final String sessionId, final String payTrsId,final String payName,final String comments) {
		logger.info("-----------getOrg2ToPayCf---------->sessionId:"+sessionId+",payTrsId:"+payTrsId+",payName:"+payName+",comments:"+comments);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_to_pay_cf(?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, payTrsId);
				cs.setString(3, payName);
				cs.setString(4, comments);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String, String> resultMap = new HashMap<String, String>();
				String result = cs.getString(5);
				resultMap.put(result, null);
				logger.info("<-----------getOrg2ToPayCf----------message:"+result);
				return resultMap;
			}
		});

	}
	
	
	
	public Map<String, String> getOrg2ToPayCancel(final String sessionId, final String payTrsId) {
		logger.info("-----------getOrg2ToPayCancel---------->sessionId:"+sessionId+",payTrsId:"+payTrsId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_org2_to_pay_cancel(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, payTrsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String, String> resultMap = new HashMap<String, String>();
				String result = cs.getString(3);
				resultMap.put(result, null);
				logger.info("<-----------getOrg2ToPayCancel----------message:"+result);
				return resultMap;
			}
		});

	}

}
