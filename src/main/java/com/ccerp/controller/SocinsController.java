package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.base.Custom;
import com.ccerp.bean.condation.SocinsCondition;
import com.ccerp.bean.services.SocinsServicesBean;
import com.ccerp.dao.SocinsDao;

@Controller
public class SocinsController {
	
	@Autowired
	private SocinsDao socinsDao;
	
	@RequestMapping("/socinsInfo")
	@ResponseBody
	public ClientBean findSocinsInfo(SocinsCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, SocinsServicesBean>  mapResult =socinsDao.socinsDataInfo(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			SocinsServicesBean ssb = mapResult.get(key);
			cbody.setTotal(Integer.parseInt(ssb.getTotal()));
			cbody.setRows(ssb.getRows());
			
			Custom custom =new Custom();
			custom.setSumEndowmentEe(ssb.getSumEndowmentEe());
			custom.setSumEndowmentEr(ssb.getSumEndowmentEr());
			custom.setSumInjEe(ssb.getSumInjEe());
			custom.setSumInjEr(ssb.getSumInjEr());
			custom.setSumLarmedEe(ssb.getSumLarmedEe());
			custom.setSumLarmedEr(ssb.getSumLarmedEr());
			custom.setSumMaterEe(ssb.getSumMaterEe());
			custom.setSumMaterEr(ssb.getSumMaterEr());
			custom.setSumMedicalEe(ssb.getSumMedicalEe());
			custom.setSumMedicalEr(ssb.getSumMedicalEr());
			custom.setSumUnempEe(ssb.getSumUnempEe());
			custom.setSumUnempEr(ssb.getSumUnempEr());
			
			
			cbody.setCustom(custom);
			
			cb.setClientBody(cbody);
			
			
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	
	@RequestMapping("/addSocins")
	@ResponseBody
	public ClientBean addSocins(SocinsCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =socinsDao.socinsDataAdd(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	@RequestMapping("/delSocins")
	@ResponseBody
	public ClientBean delSocinsInfo(SocinsCondition condition){
		ClientBean cb = new ClientBean();
		String  socinsId=condition.getSocinsId();
		if(socinsId==null||"".equals(socinsId)){
			cb.setCode(1);
			cb.setMsg("ID属性不能为NULL");
			return cb;
		}
		String ids[] =socinsId.split(",");
		for(String id:ids){
			Map<String, String>  mapResult =socinsDao.socinsDataDel(condition.getSessionId(),id);
			String key =mapResult.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(key)){
				cb.setCode(0);
			}else{
				cb.setCode(1);
				cb.setMsg(key);
				break;
			}
		}
		return cb;
	}
	
	@RequestMapping("/updateSocins")
	@ResponseBody
	public ClientBean updateSocins(SocinsCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =socinsDao.socinsDataUpdate(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	@RequestMapping("/copySocins")
	@ResponseBody
	public ClientBean copySocins(SocinsCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =socinsDao.socinsDataCopy(condition.getSessionId(),
				condition.getPeriodFrom(), condition.getPeriodTo(), condition.getPayOrg2Code());
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	@RequestMapping("/socinsHisInfo")
	@ResponseBody
	public ClientBean socinsHisInfo(SocinsCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, SocinsServicesBean>  mapResult =socinsDao.socinsHisDataInfo(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			SocinsServicesBean ssb = mapResult.get(key);
			cbody.setTotal(Integer.parseInt(ssb.getTotal()));
			cbody.setRows(ssb.getRows());
			
			Custom custom =new Custom();
			custom.setSumEndowmentEe(ssb.getSumEndowmentEe());
			custom.setSumEndowmentEr(ssb.getSumEndowmentEr());
			custom.setSumInjEe(ssb.getSumInjEe());
			custom.setSumInjEr(ssb.getSumInjEr());
			custom.setSumLarmedEe(ssb.getSumLarmedEe());
			custom.setSumLarmedEr(ssb.getSumLarmedEr());
			custom.setSumMaterEe(ssb.getSumMaterEe());
			custom.setSumMaterEr(ssb.getSumMaterEr());
			custom.setSumMedicalEe(ssb.getSumMedicalEe());
			custom.setSumMedicalEr(ssb.getSumMedicalEr());
			custom.setSumUnempEe(ssb.getSumUnempEe());
			custom.setSumUnempEr(ssb.getSumUnempEr());
			cbody.setCustom(custom);
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}

}
