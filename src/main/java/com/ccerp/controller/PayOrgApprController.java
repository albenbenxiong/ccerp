package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.base.Custom;
import com.ccerp.bean.condation.PayOrgApprCondition;
import com.ccerp.bean.services.Org2OrApprInfoPhfBean;
import com.ccerp.bean.services.Org2OrApprInfoSocinsBean;
import com.ccerp.bean.services.Org2OrApprInfoSupmedBean;
import com.ccerp.bean.services.Org2PayInfo;
import com.ccerp.bean.services.PayOrg2DataViewPhfBean;
import com.ccerp.bean.services.PayOrg2DataViewSocinsBean;
import com.ccerp.bean.services.PayOrg2DataViewSupmedBean;
import com.ccerp.dao.PayOrgApprDao;

@Controller
public class PayOrgApprController {

	@Autowired
	private PayOrgApprDao payOrgApprDao;
	
	
	@RequestMapping("/getPayOrg2Info")
	@ResponseBody
	public ClientBean getPayOrg2Info(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getPayOrg2Info(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSocinsBean oaisb = (Org2OrApprInfoSocinsBean) resultMap
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
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoPhfBean oaipb = (Org2OrApprInfoPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSupmedBean oaisb = (Org2OrApprInfoSupmedBean) resultMap
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

	@RequestMapping("/getPayOrg2DataView")
	@ResponseBody
	public ClientBean getPayOrg2DataView(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getPayOrg2DataView(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
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
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewPhfBean oaipb = (PayOrg2DataViewPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
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


	
	
	
	@RequestMapping("/getPayOrg2Cf")
	@ResponseBody
	public ClientBean getPayOrg2Cf(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		String periods =poac.getPeriod();
		String payOrg2Codes =poac.getPayOrg2Code();
		String org2Codes =poac.getOrg2Code();
		if(periods==null||payOrg2Codes==null||org2Codes==null){
			cb.setCode(1);
			cb.setMsg("period或者 payOrg2Codes是null");
		}else{
			String arrayPeriod[] =periods.split(",");
			String arrayPays[]=payOrg2Codes.split(",");
			String arrayCodes[]=org2Codes.split(",");
			for(int i=0;i<arrayPeriod.length;i++){
				Map<String, String> resultMap = payOrgApprDao.getPayOrg2Cf(poac.getSessionId(),arrayPeriod[i],arrayPays[i],poac.getElementCatalog(),arrayCodes[i]);
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
			}
		}
		return cb;
	}

	@RequestMapping("/getPayOrg2Cancel")
	@ResponseBody
	public ClientBean getPayOrg2Cancel(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		String periods =poac.getPeriod();
		String payOrg2Codes =poac.getPayOrg2Code();
		String org2Codes =poac.getOrg2Code();
		if(periods==null||payOrg2Codes==null||org2Codes==null){
			cb.setCode(1);
			cb.setMsg("period或者 payOrg2Codes是null");
		}else{
			String arrayPeriod[] =periods.split(",");
			String arrayPays[]=payOrg2Codes.split(",");
			String arrayPayCodes[]=payOrg2Codes.split(",");
			for(int i=0;i<arrayPeriod.length;i++){
				Map<String, String> resultMap = payOrgApprDao.getPayOrg2Cancel(poac.getSessionId(),arrayPeriod[i],arrayPays[i],poac.getElementCatalog(),arrayPayCodes[i]);
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
			}
		}
		return cb;
		
	}

	@RequestMapping("/getOrg2Info")
	@ResponseBody
	public ClientBean getOrg2Info(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getOrg2Info(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSocinsBean oaisb = (Org2OrApprInfoSocinsBean) resultMap
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
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoPhfBean oaipb = (Org2OrApprInfoPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSupmedBean oaisb = (Org2OrApprInfoSupmedBean) resultMap
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

	@RequestMapping("/getOrg2DataView")
	@ResponseBody
	public ClientBean getOrg2DataView(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getOrg2DataView(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
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
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewPhfBean oaipb = (PayOrg2DataViewPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
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

	@RequestMapping("/getOrg2DataSave")
	@ResponseBody
	public ClientBean getOrg2DataSave(String sessionId,String phfsiId,String elementCatalog,String checkStatus,String errMessage) {
		ClientBean cb = new ClientBean();
		Map<String, String> resultMap = payOrgApprDao.getOrg2DataSave(sessionId, phfsiId, elementCatalog, checkStatus, errMessage);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			cb.setCode(0);
			cb.setNotes(keyMessage);
		} else {
			cb.setCode(1);
			cb.setMsg(keyMessage);
		}
		return cb;
	}
	// cux_hr_phfsi_rec_api_pkg.get_org2_cf

	@RequestMapping("/getOrg2Cf")
	@ResponseBody
	public ClientBean getOrg2Cf(String sessionId,String recStatusId,String org2Comments) {
		ClientBean cb = new ClientBean();
		if(recStatusId==null){
			cb.setCode(1);
			cb.setMsg("recStatusId是null");
		}else{
			String arrayPeriod[] =recStatusId.split(",");
			for(int i=0;i<arrayPeriod.length;i++){
				Map<String, String> resultMap = payOrgApprDao.getOrg2Cf(sessionId,arrayPeriod[i],org2Comments);
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
			}
		}
		return cb;
	}
	@RequestMapping("/getOrg2Cancel")
	@ResponseBody
	public ClientBean getOrg2Cancel(String sessionId,String recStatusId,String org2Comments) {
		ClientBean cb = new ClientBean();
		if(recStatusId==null){
			cb.setCode(1);
			cb.setMsg("recStatusId是null");
		}else{
			String arrayPeriod[] =recStatusId.split(",");
			for(int i=0;i<arrayPeriod.length;i++){
				Map<String, String> resultMap = payOrgApprDao.getOrg2Cancel(sessionId,arrayPeriod[i],org2Comments);
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
			}
		}
		return cb;
		
	}

	
	
	// cux_hr_phfsi_rec_api_pkg.get_appr_det_info
	@RequestMapping("/getApprDataInfo")
	@ResponseBody
	public ClientBean getApprDataInfo(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getApprInfo(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSocinsBean oaisb = (Org2OrApprInfoSocinsBean) resultMap
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
			}
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoPhfBean oaipb = (Org2OrApprInfoPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSupmedBean oaisb = (Org2OrApprInfoSupmedBean) resultMap
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

	// cux_hr_phfsi_rec_api_pkg.get_appr_data_view
	@RequestMapping("/getApprDataView")
	@ResponseBody
	public ClientBean getApprDataView(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getApprDataView(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
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
			}
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewPhfBean oaipb = (PayOrg2DataViewPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
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

	
	@RequestMapping("/getApprDataSave")
	@ResponseBody
	public ClientBean getApprDataSave(String sessionId,String phfsiId,String elementCatalog,String checkStatus,String errMessage) {
		ClientBean cb = new ClientBean();
		Map<String, String> resultMap = payOrgApprDao.getApprDataSave(sessionId, phfsiId, elementCatalog, checkStatus, errMessage);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			cb.setCode(0);
			cb.setNotes(keyMessage);
		} else {
			cb.setCode(1);
			cb.setMsg(keyMessage);
		}
		return cb;
	}
	// cux_hr_phfsi_rec_api_pkg.get_appr_cf

	@RequestMapping("/getApprCf")
	@ResponseBody
	public ClientBean getApprCf(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		String periods =poac.getPeriod();
		String payOrg2Codes =poac.getPayOrg2Code();
		String org2Codes =poac.getOrg2Code();
		String recStatusId =poac.getRecStatusId();
		if(periods==null||payOrg2Codes==null||org2Codes==null||recStatusId==null){
			cb.setCode(1);
			cb.setMsg("period或者 payOrg2Codes是null");
		}else{
			String arrayPeriod[] =periods.split(",");
			String arrayPays[]=payOrg2Codes.split(",");
			String arrayorgCodes[]=org2Codes.split(",");
			String arrayrecStatusId[]=recStatusId.split(",");
			for(int i=0;i<arrayPeriod.length;i++){
				Map<String, String> resultMap = payOrgApprDao.getApprCf(poac.getSessionId(),arrayrecStatusId[i],arrayPeriod[i],arrayPays[i],arrayorgCodes[i],poac.getElementCatalog(),poac.getPayOrg2Comments());
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
			}
		}
		return cb;
	}

	@RequestMapping("/getApprCancel")
	@ResponseBody
	public ClientBean getApprCancel(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		String periods =poac.getPeriod();
		String payOrg2Codes =poac.getPayOrg2Code();
		String org2Codes =poac.getOrg2Code();
		String recStatusId =poac.getRecStatusId();
		if(periods==null||payOrg2Codes==null||org2Codes==null||recStatusId==null){
			cb.setCode(1);
			cb.setMsg("period或者 payOrg2Codes是null");
		}else{
			String arrayPeriod[] =periods.split(",");
			String arrayPays[]=payOrg2Codes.split(",");
			String arrayorgCodes[]=org2Codes.split(",");
			String arrayrecStatusId[]=recStatusId.split(",");
			for(int i=0;i<arrayPeriod.length;i++){
				Map<String, String> resultMap = payOrgApprDao.getApprCancel(poac.getSessionId(),arrayrecStatusId[i],arrayPeriod[i],arrayPays[i],arrayorgCodes[i],poac.getElementCatalog(),poac.getPayOrg2Comments());
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
			}
		}
		return cb;
	}

	@RequestMapping("/getOrg2PayInfo")
	@ResponseBody
	public ClientBean getOrg2PayInfo(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Org2PayInfo> resultMap = payOrgApprDao.getOrg2PayInfo(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
				Org2PayInfo org2PayInfo = resultMap.get(keyMessage);
				cbody.setRows(org2PayInfo.getRows());
				cbody.setTotal(Integer.parseInt(org2PayInfo.getTotal()));
			cb.setClientBody(cbody);
		} else {
			cb.setCode(1);
			cb.setMsg(keyMessage);
		}
		return cb;
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_org2_pay_data_view
	@RequestMapping("/getOrg2PayDataView")
	@ResponseBody
	public ClientBean getOrg2PayDataView(PayOrgApprCondition poac) {
		
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getOrg2PayDataView(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
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
			}
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				PayOrg2DataViewPhfBean oaipb = (PayOrg2DataViewPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
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
	
	//cux_hr_phfsi_rec_api_pkg.get_org2_to_pay
	@RequestMapping("/getOrg2ToPay")
	@ResponseBody
	public ClientBean getOrg2ToPay(PayOrgApprCondition poac) {
		ClientBean cb = new ClientBean();
		Map<String, Object> resultMap = payOrgApprDao.getOrg2ToPay(poac);
		String keyMessage = resultMap.keySet().iterator().next();
		if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
			ClientBody cbody = new ClientBody();
			cb.setCode(0);
			if ("WX".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSocinsBean oaisb = (Org2OrApprInfoSocinsBean) resultMap
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
			}
			if ("GJJ".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoPhfBean oaipb = (Org2OrApprInfoPhfBean) resultMap
						.get(keyMessage);
				cbody.setRows(oaipb.getRows());
				cbody.setTotal(Integer.parseInt(oaipb.getTotal()));
				Custom custom = new Custom();
				custom.setSumPhfEe(oaipb.getSumPhfEe());
				custom.setSumPhfEr(oaipb.getSumPhfEr());
				cbody.setCustom(custom);
			}
			if ("BCYL".equalsIgnoreCase(poac.getElementCatalog())) {
				Org2OrApprInfoSupmedBean oaisb = (Org2OrApprInfoSupmedBean) resultMap
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
	
	@RequestMapping("/getOrg2ToPayCf")
	@ResponseBody
	public ClientBean getOrg2ToPayCf(String sessionId,String payTrsId,String payName,String comments ) {
		ClientBean cb = new ClientBean();
				Map<String, String> resultMap = payOrgApprDao.getOrg2ToPayCf(sessionId,payTrsId,payName,comments);
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
		return cb;
	}
	
	
	@RequestMapping("/getOrg2ToPayCancel")
	@ResponseBody
	public ClientBean getOrg2ToPayCancel(String sessionId,String payTrsId) {
		ClientBean cb = new ClientBean();
				Map<String, String> resultMap = payOrgApprDao.getOrg2ToPayCancel(sessionId,payTrsId);
				String keyMessage = resultMap.keySet().iterator().next();
				if ("SUCCESS".equalsIgnoreCase(keyMessage)) {
					cb.setCode(0);
					cb.setNotes(keyMessage);
				} else {
					cb.setCode(1);
					cb.setMsg(keyMessage);
				}
		return cb;
		
	}
	
	
}
