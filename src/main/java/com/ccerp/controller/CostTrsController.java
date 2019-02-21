package com.ccerp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.services.CostTrsServicesBean;
import com.ccerp.dao.CostTrsDao;

@Controller
public class CostTrsController {

	@Autowired
	private CostTrsDao costTrsDao;
	
	//成本计算结果查询(刷新)
	@RequestMapping("/getCostTrsInfo")
	@ResponseBody
	public ClientBean getCostTrsInfo(String rows,String pages,String sessionId,String costTrsId){
		ClientBean cb = new ClientBean();
		Map<String,CostTrsServicesBean>  resultMap=costTrsDao.getCostTrsInfo(rows, pages, sessionId, costTrsId);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			CostTrsServicesBean ctb= resultMap.get(message);
			ClientBody body = new ClientBody();
			body.setTotal(Integer.parseInt(ctb.getTotal()));
			body.setRows(ctb.getRows());
			cb.setCode(0);
			cb.setClientBody(body);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	
	//cux_hr_phfsi_rec_api_pkg.get_cost_run
	//成本计算结果查询(重新计算)
	@RequestMapping("/getCostRun")
	@ResponseBody
	public ClientBean getCostRun(String sessionId,String costTrsId){
		ClientBean cb = new ClientBean();
		Map<String,String>  resultMap=costTrsDao.getCostRun(sessionId, costTrsId);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_init_cost_trs_rb
	//成本计算结果查询(取消凭证)
	@RequestMapping("/getInitCostTrsRb")
	@ResponseBody
	public ClientBean getInitCostTrsRb(String sessionId,String costTrsId){
		ClientBean cb = new ClientBean();
		Map<String,String>  resultMap=costTrsDao.getInitCostTrsRb(sessionId, costTrsId);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_to_gl
	//成本计算结果查询(传动到总账)
	@RequestMapping("/getToGl")
	@ResponseBody
	public ClientBean getToGl(String sessionId,String costTrsId){
		ClientBean cb = new ClientBean();
		Map<String,String>  resultMap=costTrsDao.getToG1(sessionId, costTrsId);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_to_gl_vr
	//成本计算结果查询(虚拟传送)
	@RequestMapping("/getToGlVr")
	@ResponseBody
	public ClientBean getToGlVr(String sessionId,String costTrsId){
		ClientBean cb = new ClientBean();
		Map<String,String>  resultMap=costTrsDao.getToG1Vr(sessionId, costTrsId);
		String message =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(message)){
			cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(message);
		}
		return cb;
	}
}
