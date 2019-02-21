package com.ccerp.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccerp.bean.base.ClientBean;
import com.ccerp.bean.base.ClientBody;
import com.ccerp.bean.condation.SelectListCondition;
import com.ccerp.bean.services.CostStatusServicesBean;
import com.ccerp.bean.services.CostTypeLov;
import com.ccerp.bean.services.ElementCatalog;
import com.ccerp.bean.services.ElementLov;
import com.ccerp.bean.services.EmpOrg;
import com.ccerp.bean.services.EmpPop;
import com.ccerp.bean.services.EpeopleGroupLov;
import com.ccerp.bean.services.Org;
import com.ccerp.bean.services.PayOrg;
import com.ccerp.bean.services.ValueSetComLov;
import com.ccerp.dao.SelectListDao;

@Controller
public class SelectListController {
	@Autowired
	private SelectListDao selectListDao;
	
	@RequestMapping("/getPayOrg2Lov")
	@ResponseBody
	public ClientBean getPayOrg2Lov(String sessionId){
		ClientBean cb = new ClientBean();
		Map<String, List<PayOrg>> result =selectListDao.getPayOrg2Lov(sessionId);
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(result.get(key));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
		
	}
	@RequestMapping("/getOrg2Lov")
	@ResponseBody
	public ClientBean getOrg2Lov(String sessionId){
		ClientBean cb = new ClientBean();
		Map<String, List<Org>> result =selectListDao.getOrg2Lov(sessionId);
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(result.get(key));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	
	@RequestMapping("/getElementCataLogLov")
	@ResponseBody
	public ClientBean getElementCataLogLov(String p_session_id){
		ClientBean cb = new ClientBean();
		Map<String, List<ElementCatalog>> result =selectListDao.getElementCataLog(p_session_id);
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(result.get(key));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	
	
	@RequestMapping("/getEmpPopup")
	@ResponseBody
	public ClientBean getEmpPopup(SelectListCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, EmpPop> result =selectListDao.getEmpPopup(condition);
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			EmpPop  empop =result.get(key);
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(empop.getRows());
			cbody.setTotal(Integer.parseInt(empop.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}

	
	@RequestMapping("/getEmpOrg")
	@ResponseBody
	public ClientBean getEmpOrg(SelectListCondition condition){
		ClientBean cb = new ClientBean();
		Map<String, EmpOrg> result =selectListDao.getEmpOrg(condition.getP_rows(), condition.getP_pages(), condition.getP_session_id(),condition.getP_org_name());
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			EmpOrg  empOrg =result.get(key);
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(empOrg.getRows());
			cbody.setTotal(Integer.parseInt(empOrg.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	
	//=====================================================================
	
	//cux_hr_phfsi_rec_api_pkg.get_element_lov
	@RequestMapping("/getElementLov")
	@ResponseBody
	public ClientBean getElementLov(String rows,String pages,String sessionId,String elementName){
		ClientBean cb = new ClientBean();
		Map<String, ElementLov> result =selectListDao.getElementLov(rows, pages, sessionId, elementName);
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			ElementLov  elementLov =result.get(key);
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(elementLov.getRows());
			cbody.setTotal(Integer.parseInt(elementLov.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	
	
	//cux_hr_phfsi_rec_api_pkg.get_people_group_lov
	@RequestMapping("/getPeopleGroupLov")
	@ResponseBody
	public ClientBean getPeopleGroupLov(String rows,String pages,String sessionId,String peopleGroupName){
		ClientBean cb = new ClientBean();
		Map<String, EpeopleGroupLov> result =selectListDao.getPeopleGroupLov(rows, pages, sessionId, peopleGroupName);
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			EpeopleGroupLov   elementLov =result.get(key);
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(elementLov.getRows());
			cbody.setTotal(Integer.parseInt(elementLov.getTotal()));
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_people_group_lov
	@RequestMapping("/getCostTypeLov")
	@ResponseBody
	public ClientBean getCostTypeLov(String sessionId,String cosTypeName){
		ClientBean cb = new ClientBean();
		Map<String, List<CostTypeLov>> result =selectListDao.getCostTypeLov(sessionId, cosTypeName);
		String key =result.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			List<CostTypeLov>   elementLov =result.get(key);
			cb.setCode(0);
			ClientBody cbody= new ClientBody();
			cbody.setRows(elementLov);
			cb.setClientBody(cbody);
		}else{
			cb.setCode(1);
			cb.setMsg(key);
		}
		return cb;
	}
	//cux_hr_phfsi_rec_api_pkg.get_value_set_com_lov
		@RequestMapping("/getValueSetComLov")
		@ResponseBody
		public ClientBean getValueSetComLov(String rows, String pages, String sessionId, String valueSetName,String valueMeaning){
			ClientBean cb = new ClientBean();
			Map<String, ValueSetComLov> result =selectListDao.getValueSetComLov(rows, pages, sessionId, valueSetName, valueMeaning);
			String key =result.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(key)){
				ValueSetComLov   elementLov =result.get(key);
				cb.setCode(0);
				ClientBody cbody= new ClientBody();
				cbody.setRows(elementLov.getRows());
				cbody.setTotal(Integer.parseInt(elementLov.getTotal()));
				cb.setClientBody(cbody);
			}else{
				cb.setCode(1);
				cb.setMsg(key);
			}
			return cb;
		}
	
	
		@RequestMapping("/getCostStatusLov")
		@ResponseBody
		public ClientBean getCostStatusLov(String sessionId,String costStatusName){
			ClientBean cb = new ClientBean();
			Map<String, List<CostStatusServicesBean>> result =selectListDao.getCostStatusLov(sessionId,costStatusName);
			String key =result.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(key)){
				cb.setCode(0);
				ClientBody cbody= new ClientBody();
				cbody.setRows(result.get(key));
				cb.setClientBody(cbody);
			}else{
				cb.setCode(1);
				cb.setMsg(key);
			}
			return cb;
		}
		
	
}
