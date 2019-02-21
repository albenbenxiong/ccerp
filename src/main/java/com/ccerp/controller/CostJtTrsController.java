package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.condation.PayOrgApprCondition;
import com.ccerp.bean.services.CostJtTrsServicesBean;
import com.ccerp.bean.services.CostSkTrsServicesBean;
import com.ccerp.bean.services.Org2OrApprInfoPhfTrsBean;
import com.ccerp.bean.services.Org2OrApprInfoSocinsTrsBean;
import com.ccerp.bean.services.Org2OrApprInfoSupmedTrsBean;
import com.ccerp.bean.services.PayOrg2DataViewPhfBean;
import com.ccerp.bean.services.PayOrg2DataViewSocinsBean;
import com.ccerp.bean.services.PayOrg2DataViewSupmedBean;
import com.ccerp.dao.CostTrsDao;


@Controller
public class CostJtTrsController {
	
	@Autowired
	private CostTrsDao costTrsDao;
	
	//计提/缴费/收款凭证查询
	@RequestMapping("/getJtTrsInfo")
	@ResponseBody
	//cux_hr_phfsi_rec_api_pkg.get_jt_trs_info
	public ClientBean getJtTrsInfo(String rows,String pages,String sessionId,String payPeriod,String batchName,String runStats,String elementCatalog,
			                       String payOrg2Codes,String costType){
		ClientBean cb = new ClientBean();
		Map<String ,CostJtTrsServicesBean> resultMap=
			costTrsDao.getJtTrsInfo(rows, pages, sessionId, payPeriod, batchName, runStats, elementCatalog, costType, payOrg2Codes);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			CostJtTrsServicesBean jts= resultMap.get(message);
			ClientBody body = new ClientBody();
			body.setTotal(Integer.parseInt(jts.getTotal()));
			body.setRows(jts.getRows());
			cb.setCode(0);
			cb.setClientBody(body);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}

	
	

	//生成计提凭证/生成缴费凭证第一步
	@RequestMapping("/getJtjfStep1")
	@ResponseBody
	//cux_hr_phfsi_rec_api_pkg.get_jtjf_step1
	public ClientBean getJtjfStep1(String recStatusId,String sessionId,String costType){
		ClientBean cb = new ClientBean();
		Map<String ,String> resultMap=
			costTrsDao.getJtjfStep1(sessionId, recStatusId, costType);
		
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}

	//生成计提凭证/生成缴费凭证第2步
	@RequestMapping("/getJtjfStep2")
	@ResponseBody
	//cux_hr_phfsi_rec_api_pkg.get_jtjf_step2
	public ClientBean getJtjfStep2(String recStatusId,String sessionId,String costType,String payPeriod){
		ClientBean cb = new ClientBean();
		Map<String ,String> resultMap=
			costTrsDao.getJtjfStep2(sessionId, payPeriod,recStatusId, costType);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	
	
	
	
	//生成缴费凭证第一步
	@RequestMapping("/getSkStep1")
	@ResponseBody
	//cux_hr_phfsi_rec_api_pkg.get_sk_step1_lp
	public ClientBean getSkStep1(String recStatusId,String sessionId){
		ClientBean cb = new ClientBean();
		Map<String ,String> resultMap=
			costTrsDao.getSkStep1Lp(sessionId, recStatusId);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	
	//生成缴费凭证第2步
	@RequestMapping("/getSkStep2")
	@ResponseBody
	public ClientBean getSkStep2(String recStatusId,String sessionId){
		ClientBean cb = new ClientBean();
		Map<String ,String> resultMap=
			costTrsDao.getSkStep2Lp(sessionId, recStatusId);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	//生成缴费凭证第3步
	@RequestMapping("/getSkStep3")
	@ResponseBody
	public ClientBean getSkStep3(String sessionId,String payPeriod,String payOrg2Code,String org2Code,String elementCatalog){
		ClientBean cb = new ClientBean();
		Map<String ,String> resultMap=
			costTrsDao.getSkStep3(sessionId, payPeriod, payOrg2Code, org2Code, elementCatalog);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	//
	@RequestMapping("/getJtJfCostTrsView")
	@ResponseBody
	//.get_jtjf_cost_trs_view
	public ClientBean getJtJfCostTrsView(PayOrgApprCondition poac){
		ClientBean cb = new ClientBean();
		Map<String ,Object> resultMap=
			costTrsDao.getJtjfCostTrsView(poac);
		String keyMessage =resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewSocinsBean oaisb = (PayOrg2DataViewSocinsBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
			}
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewPhfBean oaipb = (PayOrg2DataViewPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewSupmedBean oaisb = (PayOrg2DataViewSupmedBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
			}
			cb.setClientBody(cbody);
		} else {
			cb.setCode(1);
			cb.setMsg(keyMessage);
		}
		return cb;
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_jtjf_cost_trs_info
	
	@RequestMapping("/getJtjfCostTrsInfo")
	@ResponseBody
	public ClientBean getJtjfCostTrsInfo(PayOrgApprCondition poac ,String jtStatus,String jfStatus) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = costTrsDao.getJtjfCostTrsInfo(poac, jtStatus, jfStatus);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSocinsTrsBean oaisb = (Org2OrApprInfoSocinsTrsBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
			}
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoPhfTrsBean oaipb = (Org2OrApprInfoPhfTrsBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSupmedTrsBean oaisb = (Org2OrApprInfoSupmedTrsBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
			}
			cb.setClientBody(cbody);
		} else {
			cb.setCode(1);
			cb.setMsg(keyMessage);
		}
		return cb;
	}
	
	//生成凭证(生成收款)查询
	@RequestMapping("/getSkTrsInfo")
	@ResponseBody
	//cux_hr_phfsi_rec_api_pkg.get_sk_cost_trs_info
	public ClientBean getSkTrsInfo(String rows,String pages,String sessionId,String period, String elementCatalog,String payOrg2Code,String org2Code,
			                      String runStatus){
		ClientBean cb = new ClientBean();
		Map<String ,CostSkTrsServicesBean> resultMap=
			costTrsDao.getSkTrsInfo(rows, pages, sessionId, period, elementCatalog,payOrg2Code, org2Code, runStatus);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			CostSkTrsServicesBean jts= resultMap.get(message);
			ClientBody body = new ClientBody();
			body.setTotal(Integer.parseInt(jts.getTotal()));
			body.setRows(jts.getRows());
			cb.setCode(0);
			cb.setClientBody(body);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}

	
	//
	@RequestMapping("/getSkCostTrsView")
	@ResponseBody
	//cux_hr_phfsi_rec_api_pkg.get_sk_cost_trs_view
	public ClientBean getSkCostTrsView(PayOrgApprCondition poac){
		ClientBean cb = new ClientBean();
		Map<String ,Object> resultMap=
			costTrsDao.getSkCostTrsView(poac);
		String keyMessage =resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewSocinsBean oaisb = (PayOrg2DataViewSocinsBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
			}
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewPhfBean oaipb = (PayOrg2DataViewPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewSupmedBean oaisb = (PayOrg2DataViewSupmedBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaisb.getRows());
				cbody.setTotal(Integer.parseInt(oaisb.getTotal()));
			}
			cb.setClientBody(cbody);
		} else {
			cb.setCode(1);
			cb.setMsg(keyMessage);
		}
		return cb;
	}
	
}
