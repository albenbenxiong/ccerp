package com.ccerp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.services.status.BaseStatusLov;
import com.ccerp.bean.services.status.CheckStatusLov;
import com.ccerp.bean.services.status.PayStatusLov;
import com.ccerp.dao.GetStatusDao;


@Controller
public class GetStatusController {
	
	@Autowired
	private GetStatusDao getStatusDao;
	
	@RequestMapping("/getCheckStatusLov")
	@ResponseBody
	public ClientBean getCheckStatusLov(String sessionId){
		Map<String,List<CheckStatusLov>> result =getStatusDao.getCheckStatus(sessionId);
		String keyResult =result.keySet().iterator().next();
		ClientBean cb = new ClientBean();
		if("SUCCESS".equalsIgnoreCase(keyResult)){
		    cb.setCode(0);
		    ClientBody  cbody = new ClientBody();
			cbody.setRows(result.get(keyResult));
		    cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(keyResult);
		}
		return  cb;
	}
	@RequestMapping("/getPayStatusLov")
	@ResponseBody
	public ClientBean getPayStatusLov(String sessionId){
		Map<String,List<PayStatusLov>> result =getStatusDao.getPayStatus(sessionId);
		String keyResult =result.keySet().iterator().next();
		ClientBean cb = new ClientBean();
		if("SUCCESS".equalsIgnoreCase(keyResult)){
		    cb.setCode(0);
		    ClientBody  cbody = new ClientBody();
			cbody.setRows(result.get(keyResult));
		    cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(keyResult);
		}
		return  cb;
	}
	@RequestMapping("/getStatusLov")
	@ResponseBody
	public ClientBean getBaseStatusLov(String sessionId){
		Map<String,List<BaseStatusLov>> result =getStatusDao.getBaseStatus(sessionId);
		String keyResult =result.keySet().iterator().next();
		ClientBean cb = new ClientBean();
		if("SUCCESS".equalsIgnoreCase(keyResult)){
		    cb.setCode(0);
		    ClientBody  cbody = new ClientBody();
			cbody.setRows(result.get(keyResult));
		    cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(keyResult);
		}
		return  cb;
	}
}
