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

import com.ccerp.bean.condation.PayOrgApprCondition;
import com.ccerp.bean.services.CostJtTrsServicesBeanBase;
import com.ccerp.bean.services.CostSkTrsServicesBean;
import com.ccerp.bean.services.CostTrsServicesBean;
import com.ccerp.bean.services.CostJtTrsServicesBean;
import com.ccerp.bean.services.Org2OrApprInfoPhfTrsBean;
import com.ccerp.bean.services.Org2OrApprInfoSocinsTrsBean;
import com.ccerp.bean.services.Org2OrApprInfoSupmedTrsBean;
import com.ccerp.bean.services.PayOrg2DataViewPhfBean;
import com.ccerp.bean.services.PayOrg2DataViewSocinsBean;
import com.ccerp.bean.services.PayOrg2DataViewSupmedBean;
import com.ccerp.utils.GsonUtils;

@Repository
public class CostTrsDao {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public Map<String, Object> getJtjfCostTrsInfo(final PayOrgApprCondition poa,final String jtStatus,final String jfStatus) {
		logger.info("---------getJtjfCostTrsInfo----------->rows:"+poa.getRows()+",pages:"+poa.getPages()+",sessionId:"+poa.getSessionId()
				+",period:"+poa.getPeriod()+",payOrg2Code:"+poa.getPayOrg2Code()+",jtStatus:"+jtStatus+",jfStatus:"+jfStatus);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_jtjf_cost_trs_info(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getElementCatalog());
				cs.setString(7, jtStatus);
				cs.setString(8, jfStatus);
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
					Object object = switchByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getJtjfCostTrsInfo----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
				return resultMap;
			}
			private Object switchByElementCatalog(String p_element_catalog,
					String json) {
				if ("WX".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSocinsTrsBean poisb = GsonUtils.jsonToObject(
							json, Org2OrApprInfoSocinsTrsBean.class);
					return poisb;
				}
				if ("GJJ".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoPhfTrsBean psb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoPhfTrsBean.class);
					return psb;
				}
				if ("BCYL".equalsIgnoreCase(p_element_catalog)) {
					Org2OrApprInfoSupmedTrsBean ssb = GsonUtils.jsonToObject(json,
							Org2OrApprInfoSupmedTrsBean.class);
					return ssb;
				}
				return null;
			}
		});
	}
	
	
	public Map<String, Object> getJtjfCostTrsView(final PayOrgApprCondition poa) {
		
		logger.info("---------getJtjfCostTrsView----------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_jtjf_cost_trs_view(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getElementCatalog());
				cs.setString(7, poa.getEmpNumber());
				cs.setString(8, poa.getEmpName());
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
					Object object = switchDataViewByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getJtjfCostTrsView----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
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

	//cux_hr_phfsi_rec_api_pkg.get_jtjf_step1
	//生成计提凭证/生成缴费凭证第一步
	/*p_session_id    IN VARCHAR2,p_rec_status_id  IN VARCHAR2,p_cost_type     IN VARCHAR2, p_message       OUT VARCHAR2*/
	public Map<String,String> getJtjfStep1(final String sessionId,final String recStatusId,final String costType){
		logger.info("---------getJtjfStep1----------->sessionId:"+sessionId+",recStatusId:"+recStatusId+",costType:"+costType);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_jtjf_step1(?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, recStatusId);
				cs.setString(3, costType);
				cs.registerOutParameter(4, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(4);
				resultMap.put(result, null);
				logger.info("---------getJtjfStep1----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	
	public Map<String,String> getJtjfStep2(final String sessionId,final String payPeriod,final String recStatusId,final String costType){
		logger.info("---------getJtjfStep2----------->sessionId:"+sessionId+",recStatusId:"+recStatusId+",costType:"+costType);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_jtjf_step2(?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, payPeriod);
				cs.setString(3, recStatusId);
				cs.setString(4, costType);
				cs.registerOutParameter(5, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(5);
				resultMap.put(result, null);
				logger.info("---------getJtjfStep2----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_sk_step1_lp
	//生成缴费凭证第一步
	/*//
	 * 
	 * p_session_id    IN VARCHAR2,
p_rec_status_id IN VARCHAR2,
p_message       OUT VARCHAR2
	*/
	
	public Map<String,String> getSkStep1Lp(final String sessionId,final String recStatusId){
		logger.info("---------getSkStep1Lp----------->sessionId:"+sessionId+",recStatusId:"+recStatusId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_sk_step1_lp(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, recStatusId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(3);
				resultMap.put(result, null);
				logger.info("---------getSkStep1Lp----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_sk_step2_lp
	//生成缴费凭证第二步
	/*//
	 * 
	p_session_id    IN VARCHAR2,
p_rec_status_id IN VARCHAR2,
p_message       OUT VARCHAR2
	*/
	public Map<String,String> getSkStep2Lp(final String sessionId,final String recStatusId){
		logger.info("---------getSkStep2Lp----------->sessionId:"+sessionId+",recStatusId:"+recStatusId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_sk_step2_lp(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, recStatusId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(3);
				resultMap.put(result, null);
				logger.info("---------getSkStep2Lp----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_sk_step3
	//生成缴费凭证第3步
	/*//
	 * 
	p_session_id      IN VARCHAR2,
p_pay_period      IN VARCHAR2,
p_pay_org2_code   IN VARCHAR2,
p_org2_code       IN VARCHAR2,
p_element_catalog IN VARCHAR2,
p_message         OUT VARCHAR2
	*/
	public Map<String,String> getSkStep3(final String sessionId,final String payPeriod,final String payOrg2Code,final String org2Code,final String elementCatalog){
		
		logger.info("---------getSkStep3Lp----------->sessionId:"+sessionId+",payPeriod:"+payPeriod+",payOrg2Code:"+payOrg2Code+",org2Code:"+org2Code+",elementCatalog:"+elementCatalog);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_sk_step3(?,?,?,?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, payPeriod);
				cs.setString(3, payOrg2Code);
				cs.setString(4, org2Code);
				cs.setString(5, elementCatalog);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(6);
				resultMap.put(result, null);
				logger.info("---------getSkStep3Lp----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_cost_run
	//成本计算结果查询(重新计算)
	/*p_session_id  IN NUMBER,
	p_cost_trs_id IN NUMBER,
	p_message     OUT VARCHAR2*/
	public Map<String,String> getCostRun(final String sessionId,final String costTrsId){
		
		logger.info("---------getCostRun----------->sessionId:"+sessionId+",costTrsId:"+costTrsId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_run(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, costTrsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(3);
				resultMap.put(result, null);
				logger.info("---------getCostRun----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_init_cost_trs_rb
	//成本计算结果查询(取消凭证)
	/*p_session_id  IN NUMBER,
	p_cost_trs_id IN NUMBER,
	p_message     OUT VARCHAR2*/
	public Map<String,String> getInitCostTrsRb(final String sessionId,final String costTrsId){
		logger.info("---------getInitCostTrsRb----------->sessionId:"+sessionId+",costTrsId:"+costTrsId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_init_cost_trs_rb(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, costTrsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(3);
				resultMap.put(result, null);
				logger.info("---------getInitCostTrsRb----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_to_gl
	//成本计算结果查询(传动到总账)
	/*p_session_id  IN NUMBER,
	p_cost_trs_id IN NUMBER,
	p_message     OUT VARCHAR2*/
	public Map<String,String> getToG1(final String sessionId,final String costTrsId){
		logger.info("---------getToG1----------->sessionId:"+sessionId+",costTrsId:"+costTrsId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_to_gl(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, costTrsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(3);
				resultMap.put(result, null);
				logger.info("---------getToG1----------->message:"+result);
				return resultMap;
			}
		});
	}
	//cux_hr_phfsi_rec_api_pkg.get_to_gl_vr
	//成本计算结果查询(虚拟传送)
	/*p_session_id  IN NUMBER,
	p_cost_trs_id IN NUMBER,
	p_message     OUT VARCHAR2*/
	public Map<String,String> getToG1Vr(final String sessionId,final String costTrsId){
		logger.info("---------getToG1Vr----------->sessionId:"+sessionId+",costTrsId:"+costTrsId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_to_gl_vr(?,?,?)}");
				cs.setString(1, sessionId);
				cs.setString(2, costTrsId);
				cs.registerOutParameter(3, Types.VARCHAR);
				return cs;
			}
		}, new CallableStatementCallback<Map<String,String>>() {
			@Override
			public Map<String, String> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				Map<String,String> resultMap = new HashMap<String, String>();
				String result =cs.getString(3);
				resultMap.put(result, null);
				logger.info("---------getToG1Vr----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	
	
	
	
	
	//cux_hr_phfsi_rec_api_pkg.get_jt_trs_info
	//计提/缴费/收款凭证查询
	/*p_rows       IN VARCHAR2,
p_pages      IN VARCHAR2,
p_session_id IN VARCHAR2,
p_pay_period IN VARCHAR2, 
p_batch_name IN VARCHAR2,
p_run_stats  IN VARCHAR2,
p_element_catalog IN VARCHAR2, 
p_cost_type       IN VARCHAR2, 
p_pay_org2_code IN VARCHAR2,
p_json          OUT CLOB,
p_message       OUT VARCHAR2*/
	
	public Map<String,CostJtTrsServicesBean> getJtTrsInfo(final String rows,final String pages,final String sessionId,final String payPeriod,
			final String batchName,final String runStats,final String elementCatalog,final String costType,final String payOrg2Code){
		
		logger.info("---------getJtTrsInfo----------->rows:"+rows+",pages:"+pages+",sessionId:"+sessionId+",payPeriod:"+payPeriod+",batchName:"+batchName
				+",runStats:"+runStats+",elementCatalog:"+elementCatalog+",costType:"+costType+",payOrg2Code:"+payOrg2Code);
		
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_jt_trs_info(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, rows);
				cs.setString(2, pages);
				cs.setString(3, sessionId);
				cs.setString(4, payPeriod);
				cs.setString(5, batchName);
				cs.setString(6, runStats);
				cs.setString(7, elementCatalog);
				cs.setString(8, costType);
				cs.setString(9, payOrg2Code);
				cs.registerOutParameter(10, Types.CLOB);
				cs.registerOutParameter(11, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,CostJtTrsServicesBean>>() {
			@Override
			public Map<String, CostJtTrsServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(11);
				Map<String, CostJtTrsServicesBean> resultMap = new HashMap<String, CostJtTrsServicesBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					logger.info(cs.getString(10));
					CostJtTrsServicesBean  jsb=GsonUtils.jsonToObject(cs.getString(10), CostJtTrsServicesBean.class);
					List<CostJtTrsServicesBeanBase>  resultaa=jsb.getRows();
					for (CostJtTrsServicesBeanBase costJtTrsServicesBeanBase : resultaa) {
						logger.info("------>"+costJtTrsServicesBeanBase);
					}
					resultMap.put(result, jsb);
				}else{
					resultMap.put(result, null);
				}
				logger.info("---------getJtTrsInfo----------->message:"+result);
				return resultMap;
			}
		});
	}
	

	//cux_hr_phfsi_rec_api_pkg.get_cost_trs_info
	//成本计算结果查询(刷新)
	/*p_rows        IN VARCHAR2,
p_pages       IN VARCHAR2,
p_session_id  IN VARCHAR2,
p_cost_trs_id IN VARCHAR2,
p_json        OUT CLOB,
p_message     OUT VARCHAR2*/
	
	public Map<String,CostTrsServicesBean> getCostTrsInfo(final String rows,final String pages,final String sessionId,final String costTrsId){
		logger.info("---------getCostTrsInfo----------->rows:"+rows+",pages:"+pages+",sessionId:"+sessionId+",costTrsId:"+costTrsId);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_cost_trs_info(?,?,?,?,?,?)}");
				cs.setString(1, rows);
				cs.setString(2, pages);
				cs.setString(3, sessionId);
				cs.setString(4, costTrsId);
				cs.registerOutParameter(5, Types.CLOB);
				cs.registerOutParameter(6, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,CostTrsServicesBean>>() {
			@Override
			public Map<String, CostTrsServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(6);
				Map<String, CostTrsServicesBean> resultMap = new HashMap<String, CostTrsServicesBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					CostTrsServicesBean  ctsb=GsonUtils.jsonToObject(cs.getString(5), CostTrsServicesBean.class);
					resultMap.put(result, ctsb);
				}else{
					resultMap.put(result, null);
				}
				logger.info("---------getCostTrsInfo----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	public Map<String,CostSkTrsServicesBean> getSkTrsInfo(final String rows,final String pages,final String sessionId,final String period,
			final String elementCatalog,final String payOrg2Code,final String org2Code,final String runStats){
		logger.info("---------getSkTrsInfo----------->rows:"+rows+",pages:"+pages
				+",sessionId:"+sessionId+",period:"+period+",elementCatalog:"+elementCatalog+",payOrg2Code:"+payOrg2Code+",org2Code:"+org2Code);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs =con.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_sk_cost_trs_info(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, rows);
				cs.setString(2, pages);
				cs.setString(3, sessionId);
				cs.setString(4, period);
				cs.setString(5, payOrg2Code);
				cs.setString(6, org2Code);
				cs.setString(7, elementCatalog);
				cs.setString(8, runStats);
				cs.registerOutParameter(9, Types.CLOB);
				cs.registerOutParameter(10, Types.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback<Map<String,CostSkTrsServicesBean>>() {
			@Override
			public Map<String, CostSkTrsServicesBean> doInCallableStatement(
					CallableStatement cs) throws SQLException,
					DataAccessException {
				cs.execute();
				String result =cs.getString(10);
				Map<String, CostSkTrsServicesBean> resultMap = new HashMap<String, CostSkTrsServicesBean>();
				if("SUCCESS".equalsIgnoreCase(result)){
					CostSkTrsServicesBean  csb=GsonUtils.jsonToObject(cs.getString(9), CostSkTrsServicesBean.class);
					resultMap.put(result, csb);
				}else{
					resultMap.put(result, null);
				}
				logger.info("---------getSkTrsInfo----------->message:"+result);
				return resultMap;
			}
		});
	}
	
	
	//getSkCostTrsView
public Map<String, Object> getSkCostTrsView(final PayOrgApprCondition poa) {
		
		logger.info("---------getSkCostTrsView----------->PayOrgApprCondition:"+poa);
		return jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con
						.prepareCall("{call cux_hr_phfsi_rec_api_pkg.get_sk_cost_trs_view(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, poa.getRows());
				cs.setString(2, poa.getPages());
				cs.setString(3, poa.getSessionId());
				cs.setString(4, poa.getPeriod());
				cs.setString(5, poa.getPayOrg2Code());
				cs.setString(6, poa.getOrg2Code());
				cs.setString(7, poa.getElementCatalog());
				cs.setString(8, poa.getEmpName());
				cs.setString(9, poa.getEmpNumber());
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
					Object object = switchDataViewByElementCatalog(
							poa.getElementCatalog(), json);
					resultMap.put(message, object);
				} else {
					resultMap.put(message, null);
				}
				logger.info("<-----------getSkCostTrsView----------message:"+message+",elementCatalog:"+poa.getElementCatalog());
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
}
