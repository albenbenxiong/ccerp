package com.ccerp.test;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccerp.bean.condation.PhfCondition;
import com.ccerp.dao.PhfDao;
import com.ccerp.utils.GsonUtils;



public class TestPhf {

	
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-context-all.xml");
		
		PhfDao phfDao =context.getBean(PhfDao.class);
		
		PhfCondition phfCondition = new PhfCondition();
	//	sessionID：-1，期间：201812，p_pay_org2_code：436，personID：233228，p_phf_ee：500
		
		phfCondition.setSessionId("-1");
		phfCondition.setPeriod("201812");
		phfCondition.setPayOrg2Code("436");
		phfCondition.setOrg2Code("436");
		phfCondition.setPersonId("233228");
		phfCondition.setPhfEe("12345");
		phfCondition.setPhfId("19");
		
		Map<String,String>  resultMap= phfDao.phfDataAdd(phfCondition);
		//System.out.println(GsonUtils.objectToJson(resultMap));
		//System.out.println(GsonUtils.objectToJson(phfDao.phfDataUpdate(phfCondition)));
		
		phfCondition.setRows("10");
		phfCondition.setPages("1");
		//System.out.println(GsonUtils.objectToJson(phfDao.phfDataInfo(phfCondition)));
		
		//System.out.println(GsonUtils.objectToJson(phfDao.phfDataCopy("-1", "201812", "201811", "436")));
		phfCondition.setPeriodFrom("201811");
		phfCondition.setPeriodTo("201812");
		System.out.println(GsonUtils.objectToJson(phfDao.phfHisDataInfo(phfCondition)));
		
		
		//System.out.println(GsonUtils.objectToJson(phfDao.phfDataDel("-1", "27")));
		
		/*System.out.println(GsonUtils.objectToJson(phfDao.phfDataDel("-1", "123")));
		System.out.println(GsonUtils.objectToJson(phfDao.phfDataUpdate(phfCondition)));
		System.out.println(GsonUtils.objectToJson(phfDao.phfDataInfo(phfCondition)));
		
		System.out.println(GsonUtils.objectToJson(phfDao.phfDataUpdate(phfCondition)));
		
		System.out.println(GsonUtils.objectToJson(phfDao.phfHisDataInfo(phfCondition)));*/
		
		context.close();
		
	}
}
