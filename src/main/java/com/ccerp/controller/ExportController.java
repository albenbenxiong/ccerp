package com.ccerp.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ccerp.bean.condation.BaseCondition;
import com.ccerp.bean.condation.PhfCondition;
import com.ccerp.bean.condation.SocinsCondition;
import com.ccerp.bean.condation.SupmedCondition;
import com.ccerp.bean.services.PhfServicesBean;
import com.ccerp.bean.services.SocinsServicesBean;
import com.ccerp.bean.services.SupmedServicesBean;
import com.ccerp.dao.PhfDao;
import com.ccerp.dao.SocinsDao;
import com.ccerp.dao.SupmedDao;
import com.ccerp.export.view.PhfView;
import com.ccerp.export.view.SocinseView;
import com.ccerp.export.view.SupmedView;


@Controller
public class ExportController {
	
	@Autowired
	private SocinsDao socinsDao;
	
	@Autowired
	private PhfDao phfDao;
	
	@Autowired
	private SupmedDao supmedDao;
	
	@RequestMapping("/exportSocins")
	public ModelAndView exportSocins(SocinsCondition condition,ModelMap model){
		condition.setRows("1000000");
		condition.setPages("1");
		Map<String,SocinsServicesBean> resultMap =socinsDao.socinsDataInfo(condition);
		String key =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			model.put("socinseList", resultMap.get("SUCCESS").getRows());
		}else{
			model.put("socinseList",null);
		}
		SocinseView socinseView = new SocinseView();
		
		return new ModelAndView(socinseView,model);
	}
	@RequestMapping("/exportPhf")
	public ModelAndView exportPhf(PhfCondition condition,ModelMap model){
		condition.setRows("1000000");
		condition.setPages("1");
		Map<String,PhfServicesBean> resultMap =phfDao.phfDataInfo(condition);
		String key =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			model.put("phfList", resultMap.get("SUCCESS").getRows());
		}else{
			model.put("phfList",null);
		}
		PhfView phfView = new PhfView();
		return new ModelAndView(phfView,model);
	}
	
	@RequestMapping("/exportSupmed")
	public ModelAndView exportSupmed(SupmedCondition condition,ModelMap model){
		condition.setRows("1000000");
		condition.setPages("1");
		Map<String,SupmedServicesBean> resultMap =supmedDao.supmedDataInfo(condition);
		String key =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(key)){
			model.put("supmedList", resultMap.get("SUCCESS").getRows());
		}else{
			model.put("supmedList",null);
		}
		
		SupmedView suomedView = new SupmedView();
		return new ModelAndView(suomedView,model);
	}
	
	@RequestMapping("/exportHis/{exportType}")
	public ModelAndView exportSupmed(BaseCondition condition,ModelMap model,@PathVariable("exportType") String exportType){
		condition.setRows("1000000");
		condition.setPages("1");
		if("WX".equalsIgnoreCase(exportType)){
			Map<String,SocinsServicesBean> resultMap =socinsDao.socinsHisDataInfo(condition);
			String key =resultMap.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(key)){
				model.put("socinseList", resultMap.get("SUCCESS").getRows());
			}else{
				model.put("socinseList",null);
			}
			SocinseView socinseView = new SocinseView();
			return new ModelAndView(socinseView,model);
		}else if("GJJ".equalsIgnoreCase(exportType)){
			Map<String,PhfServicesBean> resultMap =phfDao.phfHisDataInfo(condition);
			
			String key =resultMap.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(key)){
				model.put("phfList", resultMap.get("SUCCESS").getRows());
		    }else{
				model.put("phfList",null);
			}
			PhfView phfView = new PhfView();
			return new ModelAndView(phfView,model);
		}else{
			Map<String,SupmedServicesBean> resultMap =supmedDao.supmedHisDataInfo(condition);
			String key =resultMap.keySet().iterator().next();
			if("SUCCESS".equalsIgnoreCase(key)){
				model.put("supmedList", resultMap.get("SUCCESS").getRows());
			}else{
				model.put("supmedList",null);
			}
			SupmedView suomedView = new SupmedView();
			return new ModelAndView(suomedView,model);
		}
		// ("BCYL".equalsIgnoreCase(exportType))
	}
	
}
