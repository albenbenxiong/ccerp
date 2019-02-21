package com.ccerp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.services.CustomMenu;
import com.ccerp.bean.services.Responsibility;
import com.ccerp.dao.LoginDao;
import com.ccerp.dao.ResponsibilityDao;

@Controller
public class loginController {
	
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private ResponsibilityDao responsibilityDao;
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public ClientBean doLogin(String p_user_name,String p_password){
		String loginResult =loginDao.doLogin(p_user_name, p_password);
		ClientBean cb = new ClientBean();
		if("SUCCESS".equalsIgnoreCase(loginResult)){
				cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(loginResult);
		}
		return  cb;
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public ClientBean changePassword(String userName,String oldPwd,String newPwdInput1,String newPwdInput2){
		String loginResult =loginDao.changePassword(userName, oldPwd, newPwdInput1, newPwdInput2);
		ClientBean cb = new ClientBean();
		if("SUCCESS".equalsIgnoreCase(loginResult)){
				cb.setCode(0);
		}else{
			cb.setCode(1);
			cb.setMsg(loginResult);
		}
		return  cb;
	}
	
	@RequestMapping("/getRespList")
	@ResponseBody
	public ClientBean getRespList(String p_user_name){
		Map<String,List<Responsibility>> respListResult =responsibilityDao.findRespList(p_user_name);
		String keyResult =respListResult.keySet().iterator().next();
		ClientBean cb = new ClientBean();
		if("SUCCESS".equalsIgnoreCase(keyResult)){
			cb.setCode(0);
			ClientBody  cbody = new ClientBody();
			cbody.setRows(respListResult.get(keyResult));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(keyResult);
		}
		return cb;
	}
	@RequestMapping("/getSessionId")
	@ResponseBody
	public ClientBean getSessionId(String p_user_name,String p_resp_code){
		Map<String,String>  resultMap=responsibilityDao.changeResponsibility(p_user_name, p_resp_code);
		ClientBean cb = new ClientBean();
		String mapKey =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(mapKey)){
			cb.setCode(0);
			cb.setNotes(resultMap.get(mapKey));
		}else{
			cb.setCode(1);
			cb.setMsg(mapKey);
		}
		return cb ;
	}
	
	
	
	@RequestMapping("/getMenuList")
	@ResponseBody
	public ClientBean getMenuList(String respId,String appId){
		Map<String,String>  resultMap=responsibilityDao.getMenulList(respId, appId);
		ClientBean cb = new ClientBean();
		String mapKey =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(mapKey)){
			cb.setCode(0);
			cb.setNotes(resultMap.get(mapKey));
		}else{
			cb.setCode(1);
			cb.setMsg(mapKey);
		}
		return cb ;
	}
	
	@RequestMapping("/getCurPeriod")
	@ResponseBody
	public ClientBean getCurPeriod(){
		Map<String,String>  resultMap=responsibilityDao.getCurPeriod();
		ClientBean cb = new ClientBean();
		String mapKey =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(mapKey)){
			cb.setCode(0);
			cb.setNotes(resultMap.get(mapKey));
		}else{
			cb.setCode(1);
			cb.setMsg(mapKey);
		}
		return cb ;
	}
	
	@RequestMapping("/getCustomMenu")
	@ResponseBody
	public ClientBean getCustomMenu(String rows,String pages,String sessionId){
		Map<String, CustomMenu>  resultMap=responsibilityDao.getCustomMenu(rows, pages, sessionId);
		ClientBean cb = new ClientBean();
		String mapKey =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(mapKey)){
			CustomMenu  cm =resultMap.get(mapKey);
			cb.setCode(0);
			ClientBody body =new ClientBody();
			body.setTotal(Integer.parseInt(cm.getTotal()));
			body.setRows(cm.getRows());
			cb.setClientBody(body);
			
		}else{
			cb.setCode(1);
			cb.setMsg(mapKey);
		}
		return cb ;
	}
}
