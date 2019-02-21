package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.base.Custom;
import com.ccerp.bean.condation.PhfCondition;
import com.ccerp.bean.services.PhfServicesBean;
import com.ccerp.dao.PhfDao;

@Controller
public class PhfController {
	@Autowired
	private PhfDao phfDao;
	
	@RequestMapping("/phfInfo")
	@ResponseBody
	public ClientBean findPhfInfo(PhfCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, PhfServicesBean>  mapResult =phfDao.phfDataInfo(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			PhfServicesBean ssb = mapResult.get(key);
			cbody.setTotal(Integer.parseInt(ssb.getTotal()));
			cbody.setRows(ssb.getRows());
			Custom custom = new Custom();
			custom.setSumPhfEe(ssb.getSumPhfEe());
			custom.setSumPhfEr(ssb.getSumPhfEr());
			cbody.setCustom(custom);
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	
	@RequestMapping("/addPhf")
	@ResponseBody
	public ClientBean addPhf(PhfCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =phfDao.phfDataAdd(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	@RequestMapping("/delPhf")
	@ResponseBody
	public ClientBean delPhf(PhfCondition condition){
		ClientBean cb = new ClientBean();
		String  phfId=condition.getPhfId();
		if(phfId==null||"".equals(phfId)){
			cb.setCode(1);
			cb.setMsg("ID属性不能为NULL");
			return cb;
		}
		String ids[] =phfId.split(",");
		for(String id:ids){
			Map<String, String>  mapResult =phfDao.phfDataDel(condition.getSessionId(),id);
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
	
	
	@RequestMapping("/updatePhf")
	@ResponseBody
	public ClientBean updatePhf(PhfCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =phfDao.phfDataUpdate(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	@RequestMapping("/copyPhf")
	@ResponseBody
	public ClientBean copyPhf(PhfCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =phfDao.phfDataCopy(condition.getSessionId(),
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
	@RequestMapping("/phfHisInfo")
	@ResponseBody
	public ClientBean phfHisInfo(PhfCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, PhfServicesBean>  mapResult =phfDao.phfHisDataInfo(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			PhfServicesBean ssb = mapResult.get(key);
			cbody.setTotal(Integer.parseInt(ssb.getTotal()));
			cbody.setRows(ssb.getRows());
			Custom custom = new Custom();
			custom.setSumPhfEe(ssb.getSumPhfEe());
			custom.setSumPhfEr(ssb.getSumPhfEr());
			cbody.setCustom(custom);
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}

}
