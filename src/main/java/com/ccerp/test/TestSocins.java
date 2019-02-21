package com.ccerp.test;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccerp.bean.condation.SelectListCondition;
import com.ccerp.bean.condation.SocinsCondition;
import com.ccerp.bean.services.EmpPop;
import com.ccerp.dao.SelectListDao;
import com.ccerp.dao.SocinsDao;
import com.ccerp.utils.GsonUtils;

public class TestSocins {

	
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-context-all.xml");
		
		SocinsDao socinsDao =context.getBean(SocinsDao.class);
		
		SelectListDao selectListDao =context.getBean(SelectListDao.class);
		
		
		
		System.out.println("===================增加工列表======================");
		SelectListCondition condation = new SelectListCondition();
		condation.setP_pages("1");
		condation.setP_rows("10");
		
		condation.setP_period("201812");
		condation.setP_session_id("-1");
		
		//responsibilityDao.getEmpPopup(condation);
		Map<String,EmpPop> resultMap =selectListDao.getEmpPopup(condation);
		
		
		String keys =resultMap.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(keys)){
			EmpPop  empop =resultMap.get(keys);
			String resultq=GsonUtils.objectToJson(empop);
			
			System.out.println(resultq+"===========");
		}else{
			System.out.println("---------->"+keys);;
		}
		
		
	
		
		
		
		
		SocinsCondition scondition = new SocinsCondition();
		
		SocinsCondition sc = new SocinsCondition();
		sc.setRows("4");
		sc.setPages("1");
		sc.setSessionId("-1");
		sc.setPeriod("201812");
		sc.setPayOrg2Code("3197");
		sc.setEmpName("肖宁宁");
		socinsDao.socinsDataInfo(sc);
		
		
		scondition.setSessionId("-1");
		scondition.setPeriod("201812");
		scondition.setPayOrg2Code("123");
		scondition.setPeriod("233228");
		scondition.setOrg2Code("123");
		scondition.setEmpName("test");
		
	/*	Map<String,Object>  resultMap= socinsDao.socinsDataAdd(scondition);
		System.out.println(resultMap);
		System.out.println("============================");
		scondition.setP_rows("4");
		scondition.setP_pages("1");
		//sc.setP_session_id("16");
		Map<String, SocinsServicesBean>  resultMap1= socinsDao.socinsDataInfo(scondition);
		String resultS =resultMap1.keySet().iterator().next();
		if("SUCCESS".equalsIgnoreCase(resultS)){
			SocinsServicesBean  ssb =resultMap1.get(resultS);
			System.out.println(GsonUtils.objectToJson(ssb));
		}*/
		context.close();
		
	}
}
