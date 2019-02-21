package com.ccerp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.base.Custom;
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
import com.ccerp.dao.QazDao;

@Controller
public class QazController {

	@Autowired
	private QazDao qazDao;
	
	@RequestMapping("/getSkAccountInfo")
	@ResponseBody
	public ClientBean getSkAccountInfo(PayOrgApprCondition poc,String payPeriod,String payName,String costStatus,String batchName){
		ClientBean cb = new ClientBean();
		Map<String, SkAccountInfo> resultMap =qazDao.skAccountInfo(poc, payPeriod, payName, costStatus, batchName);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
			SkAccountInfo skaInfo = resultMap.get(result);
			ClientBody cbody = new ClientBody();
			cbody.setRows(skaInfo.getRows());
			cbody.setTotal(Integer.parseInt(skaInfo.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	@RequestMapping("/getPayOrg2SkCf")
	@ResponseBody
	public ClientBean getPayOrg2SkCf(String sessionId, String payTrsId){
		ClientBean cb = new ClientBean();
		Map<String, String> resultMap =qazDao.getPayOrg2SkCf(sessionId, payTrsId);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	@RequestMapping("/getPayOrg2SkCancel")
	@ResponseBody
	public ClientBean getPayOrg2SkCancel(String sessionId, String payTrsId){
		ClientBean cb = new ClientBean();
		Map<String, String> resultMap =qazDao.getPayOrg2SkCancel(sessionId, payTrsId);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	@RequestMapping("/getInitBatchName")
	@ResponseBody
	//cux_hr_phfsi_rec_api_pkg.get_init_batch_name
	public ClientBean getInitBatchName(String sessionId, String payTrsId,String costType){
		ClientBean cb = new ClientBean();
		Map<String, List<BatchEntry>> resultMap =qazDao.getBatcheName(sessionId, payTrsId, costType);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
			List<BatchEntry> list= resultMap.get(result);
			ClientBody  cbody = new ClientBody();
			cbody.setRows(list);
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	@RequestMapping("/getInitAccountSk")
	@ResponseBody
	public ClientBean getInitAccountSk(String sessionId, String payTrsId, String payPeriod, String batchName){
		ClientBean cb = new ClientBean();
		Map<String, String> resultMap =qazDao.initAccountSk(sessionId, payTrsId, payPeriod, batchName);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_jt_status_lov
	
	@RequestMapping("/getJtStatusLov")
	@ResponseBody
	public ClientBean getJtStatusLov(String sessionId){
		ClientBean cb = new ClientBean();
		Map<String, List<JtStatusLov>> resultMap =qazDao.getJtStatusLov(sessionId);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
			ClientBody cbody = new ClientBody();
			cbody.setRows(resultMap.get(result));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	@RequestMapping("/getJfStatusLov")
	@ResponseBody
	public ClientBean getJfStatusLov(String sessionId){
		ClientBean cb = new ClientBean();
		Map<String, List<JfStatusLov>> resultMap =qazDao.getJfStatusLov(sessionId);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
			ClientBody cbody = new ClientBody();
			cbody.setRows(resultMap.get(result));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_jt_account_info
	
	@RequestMapping("/getJtAccountInfo")
	@ResponseBody
	public ClientBean getJtAccountInfo(PayOrgApprCondition poc,String payPeriod, String jtStatus){
		ClientBean cb = new ClientBean();
		Map<String, JtAccountInfo> resultMap =qazDao.getJtAccountInfo(poc, payPeriod, jtStatus);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
			JtAccountInfo jtInfo =resultMap.get(result);
			ClientBody cbody = new ClientBody();
			cbody.setRows(jtInfo.getRows());
			cbody.setTotal(Integer.parseInt(jtInfo.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	@RequestMapping("/getJfAccountInfo")
	@ResponseBody
	public ClientBean getJfAccountInfo(PayOrgApprCondition poc,String payPeriod, String jfStatus){
		ClientBean cb = new ClientBean();
		Map<String, JfAccountInfo> resultMap =qazDao.getJfAccountInfo(poc, payPeriod, jfStatus);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
			JfAccountInfo jfInfo =resultMap.get(result);
			ClientBody cbody = new ClientBody();
			cbody.setRows(jfInfo.getRows());
			cbody.setTotal(Integer.parseInt(jfInfo.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_acc_manage_info
	
	@RequestMapping("/getAccManageInfo")
	@ResponseBody
	public ClientBean getAccManageInfo(PayOrgApprCondition poc,String payPeriod, String batchName,String runStatus ,String costType){
		ClientBean cb = new ClientBean();
		Map<String, AccManagerInfo> resultMap =qazDao.getAccManageInfo(poc, payPeriod, batchName, runStatus, costType);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
			AccManagerInfo accManageInfo =resultMap.get(result);
			ClientBody cbody = new ClientBody();
			cbody.setRows(accManageInfo.getRows());
			cbody.setTotal(Integer.parseInt(accManageInfo.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_init_account_jtjf_s1
	@RequestMapping("/getInitAccountJtjfS1")
	@ResponseBody
	public ClientBean getInitAccountJtjfS1(String recStatusId,String costType,String canEntryFlag){
		ClientBean cb = new ClientBean();
		String [] recStatussIds =recStatusId.split(";");
		for(String id :recStatussIds){
			Map<String, String> resultMap =qazDao.getInitAccountJtJfS1(id, costType, canEntryFlag);
			String result =resultMap.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(result)){
				cb.setCode(0);
			}else{
				cb.setCode(1);
				cb.setMsg(result);
				break;
			}
		}
		
		return cb;
	}
	
	@RequestMapping("/getInitAccountJtjfS2")
	@ResponseBody
	public ClientBean getInitAccountJtjfS2(String recStatusId,String costType){
		ClientBean cb = new ClientBean();
		String [] recStatussIds =recStatusId.split(";");
		for(String id :recStatussIds){
			Map<String, String> resultMap =qazDao.getInitAccountJtJfS2(id, costType);
			String result =resultMap.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(result)){
				cb.setCode(0);
			}else{
				cb.setCode(1);
				cb.setMsg(result);
			}
		}
		return cb;
	}
	
	@RequestMapping("/getInitAccountJtjfS3")
	@ResponseBody
	public ClientBean getInitAccountJtjfS3(String sessionId, String costType, String payPeriod, String batchName,String payOrg2Code,String elementCatalog ){
		ClientBean cb = new ClientBean();
		Map<String, String> resultMap =qazDao.getInitAccountJtJfS3(sessionId, costType, payPeriod, payOrg2Code, elementCatalog, batchName);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_jtjf_account_data_view
	
	@RequestMapping("/getJtjfAccountDataView")
	@ResponseBody
	public ClientBean getJtjfAccountDataView(PayOrgApprCondition poc,String orgId){
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = qazDao.getJtJfAccountDataView(poc, orgId);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poc.getElementCatalog())) {
				PayOrg2DataViewSocinsBean oaisb = (PayOrg2DataViewSocinsBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
				Custom custom = new Custom();
				custom.setSumEndowmentEe(oaisb.getSumEndowmentEe());
				custom.setSumEndowmentEr(oaisb.getSumEndowmentEr());
				custom.setSumInjEe(oaisb.getSumInjEe());
				custom.setSumInjEr(oaisb.getSumInjEr());
				custom.setSumLarmedEe(oaisb.getSumLarmedEe());
				custom.setSumLarmedEr(oaisb.getSumLarmedEr());
				custom.setSumMaterEe(oaisb.getSumMaterEe());
				custom.setSumMaterEr(oaisb.getSumMaterEr());
				custom.setSumMedicalEe(oaisb.getSumMedicalEe());
				custom.setSumMedicalEr(oaisb.getSumMedicalEr());
				custom.setSumUnempEe(oaisb.getSumUnempEe());
				custom.setSumUnempEr(oaisb.getSumUnempEr());
				cbody.setCustom(custom);
			}
			if ("GJJ".equalsIgnoreCase(poc.getElementCatalog())) {
				PayOrg2DataViewPhfBean oaipb = (PayOrg2DataViewPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poc.getElementCatalog())) {
				PayOrg2DataViewSupmedBean oaisb = (PayOrg2DataViewSupmedBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
				Custom custom = new Custom();
				custom.setSumSupmedicalEe(oaisb.getSumSupmedicalEe());
				custom.setSumSupmedicalEr(oaisb.getSumSupmedicalEr());
				
				cbody.setCustom(custom);
			}
			cb.setClientBody(cbody);
		} else {
			cb.setCode(1);
			cb.setMsg(keyMessage);
		}
		return cb;
	}
	
	public void flagMethod() {}
	
	
	//.get_pay_step1
	@RequestMapping("/getPay/{step}")
	@ResponseBody
	public ClientBean getPayStep1and2(String recStatusId,@PathVariable ("step")String step){
		ClientBean cb = new ClientBean();
		String [] recStatusIds =recStatusId.split(";");
		for(String id:recStatusIds){
			Map<String, String> resultMap =qazDao.payStep1And2(id, step);
			String result =resultMap.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(result)){
				cb.setCode(0);
			}else{
				cb.setCode(1);
				cb.setMsg(result);
				break;
			}
		}
		return cb;
	}
	
	@RequestMapping("/getPay/step3")
	@ResponseBody
	public ClientBean getPayStep3(String sessionId,String payOrg2Code,String org2Code,String elementCatalog){
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap =qazDao.payStep3(sessionId, payOrg2Code, org2Code, elementCatalog);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(elementCatalog)) {
				PaySocinsData paySocinsData = (PaySocinsData) resultMap.get(result);
				cbody.setRows(paySocinsData.getRows());
				Custom custom = new Custom();
				custom.setSumEndowmentEe(paySocinsData.getSumEndowmentEe());
				custom.setSumEndowmentEr(paySocinsData.getSumEndowmentEr());
				//custom.setSumInjEe(paySocinsData.getSumInjEe());
				custom.setSumInjEr(paySocinsData.getSumInjEr());
				custom.setSumLarmedEe(paySocinsData.getSumLarmedEe());
				custom.setSumLarmedEr(paySocinsData.getSumLarmedEr());
				//custom.setSumMaterEe(paySocinsData.getSumMaterEe());
				custom.setSumMaterEr(paySocinsData.getSumMaterEr());
				custom.setSumMedicalEe(paySocinsData.getSumMedicalEe());
				custom.setSumMedicalEr(paySocinsData.getSumMedicalEr());
				custom.setSumUnempEe(paySocinsData.getSumUnempEe());
				custom.setSumUnempEr(paySocinsData.getSumUnempEr());
				
				custom.setPayName(paySocinsData.getPayName());
				custom.setPayTrsId(paySocinsData.getPayTrsId());
				custom.setTotalSum(paySocinsData.getTotalSum());
				
				cbody.setCustom(custom);
			}
			if ("GJJ".equalsIgnoreCase(elementCatalog)) {
				PayPhfData oaipb = (PayPhfData) resultMap.get(result);
				cbody.setRows(oaipb.getRows());
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				custom.setPayName(oaipb.getPayName());
				custom.setPayTrsId(oaipb.getPayTrsId());
				custom.setTotalSum(oaipb.getTotalSum());
				
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(elementCatalog)) {
				PaySupmedData oaisb = (PaySupmedData) resultMap.get(result);
				cbody.setRows(oaisb.getRows());
				Custom custom = new Custom();
				custom.setSumSupmedicalEe(oaisb.getSumSupmedicalEe());
				custom.setSumSupmedicalEr(oaisb.getSumSupmedicalEr());
				custom.setPayName(oaisb.getPayName());
				custom.setPayTrsId(oaisb.getPayTrsId());
				custom.setTotalSum(oaisb.getTotalSum());
				
				cbody.setCustom(custom);
			}
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_org2_pay_flow_into
	
	@RequestMapping("/getOrg2PayFlowInto")
	@ResponseBody
	public ClientBean getOrg2PayFlowInto(String payName,String payStatus,PayOrgApprCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, PayFlow> resultMap =qazDao.org2PayFlowInto(condition, payName, payStatus);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			PayFlow payFlow =resultMap.get(result);
			cbody.setRows(payFlow.getRows());
			cbody.setTotal(Integer.parseInt(payFlow.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_org2_pay_into
	@RequestMapping("/getOrg2PayInto")
	@ResponseBody
	public ClientBean getOrg2PayInto(String sessionId,String payTrsId,String elementCatalog){
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap =qazDao.org2PayInto(sessionId, payTrsId, elementCatalog);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(elementCatalog)) {
				PaySocinsData paySocinsData = (PaySocinsData) resultMap.get(result);
				cbody.setRows(paySocinsData.getRows());
				Custom custom = new Custom();
				custom.setSumEndowmentEe(paySocinsData.getSumEndowmentEe());
				custom.setSumEndowmentEr(paySocinsData.getSumEndowmentEr());
				//custom.setSumInjEe(paySocinsData.getSumInjEe());
				custom.setSumInjEr(paySocinsData.getSumInjEr());
				custom.setSumLarmedEe(paySocinsData.getSumLarmedEe());
				custom.setSumLarmedEr(paySocinsData.getSumLarmedEr());
				//custom.setSumMaterEe(paySocinsData.getSumMaterEe());
				custom.setSumMaterEr(paySocinsData.getSumMaterEr());
				custom.setSumMedicalEe(paySocinsData.getSumMedicalEe());
				custom.setSumMedicalEr(paySocinsData.getSumMedicalEr());
				custom.setSumUnempEe(paySocinsData.getSumUnempEe());
				custom.setSumUnempEr(paySocinsData.getSumUnempEr());
				custom.setPayName(paySocinsData.getPayName());
				custom.setPayTrsId(paySocinsData.getPayTrsId());
				custom.setTotalSum(paySocinsData.getTotalSum());
				
				cbody.setCustom(custom);
			}
			if ("GJJ".equalsIgnoreCase(elementCatalog)) {
				PayPhfData oaipb = (PayPhfData) resultMap.get(result);
				cbody.setRows(oaipb.getRows());
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				custom.setPayName(oaipb.getPayName());
				custom.setPayTrsId(oaipb.getPayTrsId());
				custom.setTotalSum(oaipb.getTotalSum());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(elementCatalog)) {
				PaySupmedData oaisb = (PaySupmedData) resultMap.get(result);
				cbody.setRows(oaisb.getRows());
				Custom custom = new Custom();
				custom.setSumSupmedicalEe(oaisb.getSumSupmedicalEe());
				custom.setSumSupmedicalEr(oaisb.getSumSupmedicalEr());
				custom.setPayName(oaisb.getPayName());
				custom.setPayTrsId(oaisb.getPayTrsId());
				custom.setTotalSum(oaisb.getTotalSum());
				cbody.setCustom(custom);
			}
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return cb;
	
 }
}
