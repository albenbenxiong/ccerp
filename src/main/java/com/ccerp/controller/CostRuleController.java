package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.condation.CostRuleCondition;
import com.ccerp.bean.services.CostRuleServicesBean;
import com.ccerp.bean.services.CostRuleUpdateBean;
import com.ccerp.dao.CostRuleDao;



@Controller
public class CostRuleController {
	
	
	
	@Autowired
	private CostRuleDao costRuleDao;
	
	@ResponseBody
	@RequestMapping("/costRuleAdd")
	public ClientBean costRuleAdd(CostRuleCondition costRuleCondition){
		ClientBean cb = new ClientBean();
		Map<String,String> resultMap= costRuleDao.costRuleSetAdd(costRuleCondition);
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
	@RequestMapping("/costRuleUpdate")
	public ClientBean costRuleUpdate(CostRuleCondition costRuleCondition){
		ClientBean cb = new ClientBean();
		Map<String,String> resultMap= costRuleDao.costRuleSetUpdate(costRuleCondition);
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
	@RequestMapping("/costRuleDel")
	public ClientBean costRuleDel(CostRuleCondition costRuleCondition){
		ClientBean cb = new ClientBean();
		String  releIds=costRuleCondition.getRuleId();
		if(releIds==null||"".equals(releIds)){
			cb.setCode(1);
			cb.setMsg("ID属性不能为NULL");
			return cb;
		}
		String ids[] =releIds.split(",");
		for(String id:ids){
			Map<String,String> resultMap= costRuleDao.costRuleSetDel(costRuleCondition.getSessionId(),id);
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
	@RequestMapping("/costRuleInfo")
	public ClientBean costRuleInfo(CostRuleCondition costRuleCondition){
		ClientBean cb = new ClientBean();
		Map<String,CostRuleServicesBean> resultMap= costRuleDao.costRuleSetInfo(costRuleCondition);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			CostRuleServicesBean crs =resultMap.get(result);
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
	
	@ResponseBody
	@RequestMapping("/getCostRuleSetUpdInfo")
	public ClientBean costRuleSetUpdInfo(String sessionId,String ruleId){
		ClientBean cb = new ClientBean();
		Map<String,CostRuleUpdateBean> resultMap= costRuleDao.getCostRuleSetUpdInfo(sessionId, ruleId);
		String result =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(result)){
			CostRuleUpdateBean crs =resultMap.get(result);
			cb.setCode(0);
			ClientBody body = new ClientBody();
			body.setBean(crs);
			cb.setClientBody(body);
		}else{
			cb.setCode(1);
			cb.setMsg(result);
		}
		return  cb;
	}
}
