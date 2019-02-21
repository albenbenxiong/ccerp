package com.ccerp.dao;

import java.sql.CallableStatement;
import java.sql.Clob;
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

import com.ccerp.bean.condation.PayOrgApprCondition;
import com.ccerp.bean.services.PayOrg2DataViewPhfBean;
import com.ccerp.bean.services.PayOrg2DataViewSocinsBean;
import com.ccerp.bean.services.PayOrg2DataViewSupmedBean;
import com.ccerp.bean.services.payObject.AccManagerInfo;
import com.ccerp.bean.services.payObject.BatchEntry;
import com.ccerp.bean.services.payObject.JfAccountInfo;
import com.ccerp.bean.services.payObject.JtAccountInfo;
import com.ccerp.bean.services.payObject.PayFlow;
import com.ccerp.bean.services.payObject.PayPhfData;
import com.ccerp.bean.services.payObject.PaySocinsData;
import com.ccerp.bean.services.payObject.PaySupmedData;
import com.ccerp.bean.services.payObject.SkAccountInfo;
import com.ccerp.bean.services.status.JfStatusLov;
import com.ccerp.bean.services.status.JtStatusLov;
import com.ccerp.utils.ClobUtils;
import com.ccerp.utils.GsonUtils;


@Repository
public class QazDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, String> payStep1And2(final String recStatusId,final String step) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_pay_step1(?,?)}";
				if ("step2".equals(step)) {
					sql = "{call cux_hr_phfsi_rec_api_pkg.get_pay_step2(?,?)}";
				}
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, recStatusId);
				cs.registerOutParameter(2, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result = cs.getString(2);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(result, null);
				return resultMap;
			}
		});
	}

	/*
	 * p_session_id IN NUMBER, p_pay_org2_code IN VARCHAR2, p_org2_code IN
	 * VARCHAR2, p_element_catalog IN VARCHAR2, p_json OUT CLOB, p_message OUT
	 * VARCHAR2
	 * 
	 * cux_hr_phfsi_rec_api_pkg.get_pay_step3
	 */
	public Map<String, Object> payStep3(final String sessionId,	final String payOrg2Code, final String org2Code,final String elementCatalog) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_pay_step3(?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, sessionId);
				cs.setString(2, payOrg2Code);
				cs.setString(3, org2Code);
				cs.setString(4, elementCatalog);
				cs.registerOutParameter(5, Types.CLOB);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(6);
				
				Map<String,Object> resultMap = new HashMap<String, Object>();
				if("SUCCESS".equalsIgnoreCase(result)){
					Clob json1 =cs.getClob(5);
					String json =ClobUtils.clobToString(json1);
					System.out.println("-------------------->"+json);
					Object object = switchOrg2OrApprInfoByElementCatalog(elementCatalog, json);
					resultMap.put(result, object);
				}else{
					resultMap.put(result, null);
				}
				return resultMap;
			}
			
			
			private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					PaySocinsData paySocinsData = GsonUtils.jsonToObject(json, PaySocinsData.class);
					return paySocinsData;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					PayPhfData psb = GsonUtils.jsonToObject(json,
							PayPhfData.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					PaySupmedData ssb = GsonUtils.jsonToObject(
							json, PaySupmedData.class);
					return ssb;
				}
				return null;
			}
		});
	}

	/*
	 p_rows            IN VARCHAR2,
p_pages           IN VARCHAR2,
p_session_id      IN VARCHAR2,
p_pay_org2_code   IN VARCHAR2,
p_org2_code       IN VARCHAR2,
p_pay_name        IN VARCHAR2,
p_pay_status      IN VARCHAR2,
p_element_catalog IN VARCHAR2,
p_json            OUT CLOB,
p_message         OUT VARCHAR2
	 */

	// cux_hr_phfsi_rec_api_pkg.get_org2_pay_flow_into
	// 未完成
	public Map<String, PayFlow> org2PayFlowInto(final PayOrgApprCondition condition,final String payName,final String payStatus) {

		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_org2_pay_flow_into(?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, condition.getRows());
				cs.setString(2, condition.getPages());
				cs.setString(3, condition.getSessionId());
				cs.setString(4, condition.getPayOrg2Code());
				cs.setString(5, condition.getOrg2Code());
				cs.setString(6, payName);
				cs.setString(7, payStatus);
				cs.setString(8, condition.getElementCatalog());
				cs.registerOutParameter(9, Types.CLOB);
				cs.registerOutParameter(10, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, PayFlow>>() {

			@Override
			public Map<String, PayFlow> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(10);
				Map<String, PayFlow> resultMap = new HashMap<String, PayFlow>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(9);
					PayFlow payFlow =GsonUtils.jsonToObject(json, PayFlow.class);
					resultMap.put(resultKey, payFlow);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
		});
	}

	// cux_hr_phfsi_rec_api_pkg.get_org2_pay_into

	/*
	 * p_session_id IN VARCHAR2, p_pay_trs_id IN VARCHAR2, p_element_catalog IN
	 * VARCHAR2, p_json OUT CLOB, p_message OUT VARCHAR2
	 */

	public Map<String, Object> org2PayInto(final String sessionId,final String payTrsId, final String elementCatalog) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_org2_pay_into (?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, sessionId);
				cs.setString(2, payTrsId);
				cs.setString(3, elementCatalog);

				cs.registerOutParameter(4, Types.CLOB);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(5);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(4);
					System.out.println("-------------------->>>"+json);
					Object object=switchOrg2OrApprInfoByElementCatalog(elementCatalog,json);
					resultMap.put(resultKey, object);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
			private Object switchOrg2OrApprInfoByElementCatalog(
					String p_element_catalog, String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					PaySocinsData paySocinsData = GsonUtils.jsonToObject(
							json, PaySocinsData.class);
					return paySocinsData;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					PayPhfData psb = GsonUtils.jsonToObject(json,
							PayPhfData.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					PaySupmedData ssb = GsonUtils.jsonToObject(
							json, PaySupmedData.class);
					return ssb;
				}
				return null;
			}
		});
	}

	public void flagMethod() {}

	public Map<String, SkAccountInfo>skAccountInfo(final PayOrgApprCondition poc,final String payPeriod,final String payName,final String costStatus,final String batchName) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_sk_account_info (?,?,?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, poc.getRows());
				cs.setString(2, poc.getPages());
				cs.setString(3, poc.getSessionId());
				cs.setString(4, payPeriod);
				cs.setString(5, poc.getPayOrg2Code());
				cs.setString(6, poc.getOrg2Code());
				cs.setString(7, payName);
				cs.setString(8, poc.getElementCatalog());
				cs.setString(9, costStatus);
				cs.setString(10,batchName);
				cs.registerOutParameter(11, Types.CLOB);
				cs.registerOutParameter(12, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, SkAccountInfo>>() {
			@Override
			public Map<String, SkAccountInfo> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(12);
				Map<String, SkAccountInfo> resultMap = new HashMap<String, SkAccountInfo>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(11);
					SkAccountInfo sainfo = GsonUtils.jsonToObject(json, SkAccountInfo.class);
					resultMap.put(resultKey, sainfo);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_pay_org2_sk_cf
	/*p_session_id IN VARCHAR2,
	p_pay_trs_id IN VARCHAR2,*/
	public Map<String,String> getPayOrg2SkCf(final String sessionId,final String payTrsId){
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_org2_sk_cf(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, payTrsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result= cs.getString(3);
				Map<String,String> resultMap = new HashMap<String,String>();
				resultMap.put(result, null);
				return resultMap;
			}
		});
	}
	
	public Map<String,String> getPayOrg2SkCancel(final String sessionId,final String payTrsId){
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_pay_org2_sk_cancel(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, payTrsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result= cs.getString(3);
				Map<String,String> resultMap = new HashMap<String,String>();
				resultMap.put(result, null);
				return resultMap;
			}
		});
	}
	
	
/*	p_session_id IN VARCHAR2,
	p_pay_trs_id IN VARCHAR2,
	p_cost_type  in varchar2,
	p_json       OUT CLOB,
	p_message    OUT VARCHAR2
	*/
	//BatchName cux_hr_phfsi_rec_api_pkg.get_init_batch_name
	public Map<String ,List<BatchEntry>> getBatcheName(final String sessionId,final String payTrsId,final String costType){
		
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_init_batch_name(?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, payTrsId);
				cs.setString(3, costType);
				cs.registerOutParameter(4, Types.CLOB);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String ,List<BatchEntry>>>() {
			@Override
			public Map<String, List<BatchEntry>> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultMessage=cs.getString(5);
				Map<String, List<BatchEntry>> resultMap= new HashMap<String, List<BatchEntry>>();
				if("SUCCESS".equalsIgnoreCase(resultMessage)){
					String json=cs.getString(4);
					List<BatchEntry> batchEnty= GsonUtils.jsonToObjectList(json, BatchEntry.class);
					resultMap.put(resultMessage, batchEnty);
				}else{
					resultMap.put(resultMessage, null);
				}
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_init_account_sk
	
	/*p_session_id IN VARCHAR2,
	p_pay_trs_id IN VARCHAR2,
	p_pay_period IN VARCHAR2,
	p_batch_name IN VARCHAR2,
	p_message    OUT VARCHAR2*/
	public Map<String, String>initAccountSk(final String sessionId,final String payTrsId,final String payPeriod,final String batchName) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_init_account_sk(?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, sessionId);
				cs.setString(2, payTrsId);
				cs.setString(3, payPeriod);
				cs.setString(4, batchName);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(5);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(resultKey, null);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_jt_status_lov
	
/*	p_session_id               IN VARCHAR2,
	p_json                        OUT CLOB,
	p_message                  OUT VARCHAR2*/
	public Map<String, List<JtStatusLov>>getJtStatusLov(final String sessionId) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_jt_status_lov(?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1,sessionId);
				cs.registerOutParameter(2, Types.CLOB);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, List<JtStatusLov>>>() {
			@Override
			public Map<String, List<JtStatusLov>> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(3);
				Map<String, List<JtStatusLov>> resultMap = new HashMap<String, List<JtStatusLov>>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(2);
					List<JtStatusLov> resultList =GsonUtils.jsonToObjectList(json, JtStatusLov.class);
					resultMap.put(resultKey, resultList);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
		});
	}
	
	
	public Map<String, List<JfStatusLov>>getJfStatusLov(final String sessionId) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_jf_status_lov(?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1,sessionId);
				cs.registerOutParameter(2, Types.CLOB);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, List<JfStatusLov>>>() {
			@Override
			public Map<String, List<JfStatusLov>> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(3);
				Map<String, List<JfStatusLov>> resultMap = new HashMap<String, List<JfStatusLov>>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(2);
					List<JfStatusLov> resultList =GsonUtils.jsonToObjectList(json, JfStatusLov.class);
					resultMap.put(resultKey, resultList);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_jt_account_info
	/*p_rows            IN VARCHAR2,
p_pages           IN VARCHAR2,
p_session_id      IN VARCHAR2,
p_period          IN VARCHAR2,
p_pay_period      IN VARCHAR2,
p_pay_org2_code   IN VARCHAR2,
p_org2_code       IN VARCHAR2,
p_element_catalog IN VARCHAR2,
p_jt_status       IN VARCHAR2,
p_json            OUT CLOB,
p_message         OUT VARCHAR2*/
	public Map<String, JtAccountInfo>getJtAccountInfo(final PayOrgApprCondition poc,final String payPeriod,final String jtStatus) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_jt_account_info(?,?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, poc.getRows());
				cs.setString(2, poc.getPages());
				cs.setString(3, poc.getSessionId());
				cs.setString(4, poc.getPeriod());
				cs.setString(5, payPeriod);
				cs.setString(6, poc.getPayOrg2Code());
				cs.setString(7, poc.getOrg2Code());
				cs.setString(8, poc.getElementCatalog());
				cs.setString(9, jtStatus);
				cs.registerOutParameter(10, Types.CLOB);
				cs.registerOutParameter(11, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, JtAccountInfo>>() {
			@Override
			public Map<String, JtAccountInfo> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(11);
				Map<String, JtAccountInfo> resultMap = new HashMap<String, JtAccountInfo>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(10);
					JtAccountInfo jai = GsonUtils.jsonToObject(json, JtAccountInfo.class);
					resultMap.put(resultKey, jai);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
		});
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_jf_account_info
	
	/*
	p_rows                     IN VARCHAR2,
	p_pages                    IN VARCHAR2,
	p_session_id            IN VARCHAR2,
	p_period                   IN VARCHAR2,
	p_pay_period          IN VARCHAR2,
	p_pay_org2_code    IN VARCHAR2,
	p_org2_code            IN VARCHAR2,
	p_element_catalog   IN VARCHAR2,
	p_jf_status              IN VARCHAR2,
	p_json                    OUT CLOB,
	p_message             OUT VARCHAR2*/
	
	public Map<String, JfAccountInfo>getJfAccountInfo(final PayOrgApprCondition poc,final String payPeriod,final String jfStatus) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_jf_account_info(?,?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, poc.getRows());
				cs.setString(2, poc.getPages());
				cs.setString(3, poc.getSessionId());
				cs.setString(4, poc.getPeriod());
				cs.setString(5, payPeriod);
				cs.setString(6, poc.getPayOrg2Code());
				cs.setString(7, poc.getOrg2Code());
				cs.setString(8, poc.getElementCatalog());
				cs.setString(9, jfStatus);
				cs.registerOutParameter(10, Types.CLOB);
				cs.registerOutParameter(11, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, JfAccountInfo>>() {
			@Override
			public Map<String, JfAccountInfo> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(11);
				Map<String, JfAccountInfo> resultMap = new HashMap<String, JfAccountInfo>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(10);
					JfAccountInfo jai = GsonUtils.jsonToObject(json, JfAccountInfo.class);
					resultMap.put(resultKey, jai);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
		});
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_jtjf_account_data_view
	
	
	/*p_rows                  IN VARCHAR2,
	p_pages                  IN VARCHAR2,
	p_session_id          IN VARCHAR2,
	p_period                 IN VARCHAR2,
	p_pay_org2_code   IN VARCHAR2,
	p_org2_code          IN VARCHAR2,
	p_element_catalog IN VARCHAR2,
	p_org_id                IN VARCHAR2,
	p_emp_name         IN VARCHAR2,
	p_emp_number      IN VARCHAR2,
	p_json                    OUT CLOB  ,
	p_message             OUT VARCHAR2*/
	public Map<String, Object>getJtJfAccountDataView(final PayOrgApprCondition poc,final String orgId) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_jtjf_account_data_view(?,?,?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, poc.getRows());
				cs.setString(2, poc.getPages());
				cs.setString(3, poc.getSessionId());
				cs.setString(4, poc.getPeriod());
				cs.setString(5, poc.getPayOrg2Code());
				cs.setString(6, poc.getOrg2Code());
				cs.setString(7, poc.getElementCatalog());
				cs.setString(8, orgId);
				cs.setString(9, poc.getEmpName());
				cs.setString(10, poc.getEmpNumber());
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
				String resultKey = cs.getString(12);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(11);
					Object object = switchOrg2OrApprInfoByElementCatalog(
							poc.getElementCatalog(), json);
					resultMap.put(resultKey, object);
				} else {
					resultMap.put(resultKey, null);
				}
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
	
	//cux_hr_phfsi_rec_api_pkg.get_init_account_jtjf_s1
	/*p_rec_status_id  IN VARCHAR2,
	p_cost_type      IN VARCHAR2,
	p_can_entry_flag OUT VARCHAR2,
	p_message        OUT VARCHAR2*/
	public Map<String, String>getInitAccountJtJfS1(final String recStatusId,final String costType,final String canEntryFlag) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_init_account_jtjf_s1(?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, recStatusId);
				cs.setString(2, costType);
				cs.setString(3, canEntryFlag);
				cs.registerOutParameter(4, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(4);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(resultKey, null);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_init_account_jtjf_s2
	//p_rec_status_id IN VARCHAR2,
//	p_cost_type     IN VARCHAR2,
	//p_message       OUT VARCHAR2
	public Map<String, String>getInitAccountJtJfS2(final String recStatusId,final String costType) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_init_account_jtjf_s2(?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, recStatusId);
				cs.setString(2, costType);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(3);
				Map<String, String> resultMap = new HashMap<String, String>();
			    resultMap.put(resultKey, null);
				return resultMap;
			}
		});
	}
	//cux_hr_phfsi_rec_api_pkg.get_init_account_jtjf_s3
	
	/*
	p_session_id      IN VARCHAR2,
	p_cost_type       IN VARCHAR2,
	p_pay_period      IN VARCHAR2,
	p_pay_org2_code   IN VARCHAR2,
	p_element_catalog IN VARCHAR2,
	p_batch_name      IN VARCHAR2,
	p_message         OUT VARCHAR2
	*/
	public Map<String, String>getInitAccountJtJfS3(final String sessionId,final String costType
			,final String payPeriod,final String payOrg2Code,final String elementCatalog,final String batchName) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_init_account_jtjf_s3(?,?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, sessionId);
				cs.setString(2, costType);
				cs.setString(3, payPeriod);
				cs.setString(4, payOrg2Code);
				cs.setString(5, elementCatalog);
				cs.setString(6, batchName);
				cs.registerOutParameter(7, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(7);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put(resultKey, null);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_acc_manage_info
	/*p_rows                  IN VARCHAR2,
p_pages                  IN VARCHAR2,
p_session_id          IN VARCHAR2,
p_pay_period        IN VARCHAR2, 
p_batch_name        IN VARCHAR2, 
p_run_stats            IN VARCHAR2,
p_element_catalog  IN VARCHAR2, 
p_cost_type           IN VARCHAR2, 
p_pay_org2_code   IN VARCHAR2,
p_json                    OUT CLOB,
p_message              OUT VARCHAR2*/
	
	public Map<String, AccManagerInfo>getAccManageInfo(final PayOrgApprCondition poc,final String payPeriod,final String batchName,final String runStatus,final String costType) {
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)throws SQLException {
				String sql = "{call cux_hr_phfsi_rec_api_pkg.get_acc_manage_info(?,?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, poc.getRows());
				cs.setString(2, poc.getPages());
				cs.setString(3, poc.getSessionId());
				cs.setString(4, payPeriod);
				cs.setString(5, batchName);
				cs.setString(6, runStatus);
				cs.setString(7, poc.getElementCatalog());
				cs.setString(8, costType);
				cs.setString(9, poc.getPayOrg2Code());
				cs.registerOutParameter(10, Types.CLOB);
				cs.registerOutParameter(11, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String, AccManagerInfo>>() {
			@Override
			public Map<String, AccManagerInfo> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String resultKey = cs.getString(11);
				Map<String, AccManagerInfo> resultMap = new HashMap<String, AccManagerInfo>();
				if ("SUCCESS".equalsIgnoreCase(resultKey)) {
					String json = cs.getString(10);
					AccManagerInfo jai = GsonUtils.jsonToObject(json, AccManagerInfo.class);
					resultMap.put(resultKey, jai);
				} else {
					resultMap.put(resultKey, null);
				}
				return resultMap;
			}
		});
	}
	
	

}
