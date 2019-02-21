package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.condation.CostConCondition;
import com.ccerp.bean.services.CostConServicesBean;
import com.ccerp.dao.CostConDao;

@Controller
public class CostConController {
	
	
	
	@Autowired
	private CostConDao costConDao;
	
	@ResponseBody
	@RequestMapping("/costConAdd")
	public ClientBean costConAdd(CostConCondition condition){
		ClientBean cb = new ClientBean();
		Map<String,String> resultMap= costConDao.costConAdd(condition);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return  cb;
	}
	
	@ResponseBody
	@RequestMapping("/costConDel")
	public ClientBean costConDel(CostConCondition condition){
		ClientBean cb = new ClientBean();
		String  costConsId=condition.getCostConId();
		if(costConsId==null||"".equals(costConsId)){
			cb.setCode(1);
			cb.setMsg("ID属性不能为NULL");
			return cb;
		}
		String ids[] =costConsId.split(",");
		for(String id:ids){
			Map<String,String> resultMap= costConDao.costConDel(condition.getSessionId(),id);
			String result =resultMap.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(result)){
				cb.setCode(0);
			}else{
				cb.setCode(1);
				cb.setMsg(result);
			}
		}
		return  cb;
	}
	@ResponseBody
	@RequestMapping("/costConInfo")
	public ClientBean costConInfo(CostConCondition condition){
		ClientBean cb = new ClientBean();
		Map<String,CostConServicesBean> resultMap= costConDao.costConInfo(condition);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			CostConServicesBean crs =resultMap.get(result);
			cb.setCode(0);
			ClientBody body = new ClientBody();
			body.setRows(crs.getRows());
			body.setTotal(Integer.parseInt(crs.getTotal()));
			cb.setClientBody(body);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return  cb;
	}
}
