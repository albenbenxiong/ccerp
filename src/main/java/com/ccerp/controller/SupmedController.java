package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.base.Custom;
import com.ccerp.bean.condation.SupmedCondition;
import com.ccerp.bean.services.SupmedServicesBean;
import com.ccerp.dao.SupmedDao;

@Controller
public class SupmedController {

	@Autowired
	private SupmedDao supmedDao;
	
	@RequestMapping("/supmedInfo")
	@ResponseBody
	public ClientBean findSupmedInfo(SupmedCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, SupmedServicesBean>  mapResult =supmedDao.supmedDataInfo(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			SupmedServicesBean ssb = mapResult.get(key);
			cbody.setTotal(Integer.parseInt(ssb.getTotal()));
			cbody.setRows(ssb.getRows());
			Custom custom = new Custom();
			custom.setSumSupmedicalEe(ssb.getSumSupmedicalEe());
			custom.setSumSupmedicalEr(ssb.getSumSupmedicalEr());
			cbody.setCustom(custom);
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	
	@RequestMapping("/addSupmed")
	@ResponseBody
	public ClientBean addSupmed(SupmedCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =supmedDao.supmedDataAdd(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	@RequestMapping("/delSupmed")
	@ResponseBody
	public ClientBean delSupmed(SupmedCondition condition){
		ClientBean cb = new ClientBean();
		String  supmedId=condition.getSupmedId();
		if(supmedId==null||"".equals(supmedId)){
			cb.setCode(1);
			cb.setMsg("ID属性不能为NULL");
			return cb;
		}
		String ids[] =supmedId.split(",");
		for(String id:ids){
			Map<String, String>  mapResult =supmedDao.supmedDataDel(condition.getSessionId(),id);
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
	
	
	@RequestMapping("/updateSupmed")
	@ResponseBody
	public ClientBean updateSupmed(SupmedCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =supmedDao.supmedDataUpdate(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	@RequestMapping("/copySupmed")
	@ResponseBody
	public ClientBean copySupmed(SupmedCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, String>  mapResult =supmedDao.supmedDataCopy(condition.getSessionId(),
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
	@RequestMapping("/supmedHisInfo")
	@ResponseBody
	public ClientBean supmedHisInfo(SupmedCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, SupmedServicesBean>  mapResult =supmedDao.supmedHisDataInfo(condition);
		String key =mapResult.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			SupmedServicesBean ssb = mapResult.get(key);
			cbody.setTotal(Integer.parseInt(ssb.getTotal()));
			cbody.setRows(ssb.getRows());
			Custom custom = new Custom();
			custom.setSumSupmedicalEe(ssb.getSumSupmedicalEe());
			custom.setSumSupmedicalEr(ssb.getSumSupmedicalEr());
			cbody.setCustom(custom);
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}

}
