package com.ccerp.test;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccerp.bean.condation.PayOrgApprCondition;
import com.ccerp.dao.PayOrgApprDao;



public class Test {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-context-all.xml");
		PayOrgApprDao poa =context.getBean(PayOrgApprDao.class);
		
		
		//p_session_id=-1，p_period=201812，p_pay_org2_code=3197，p_org2_code=436，p_element_catalog=WX
		
		PayOrgApprCondition poac =new PayOrgApprCondition();
		
		poac.setSessionId("-1");
		poac.setPeriod("201812");
		poac.setPayOrg2Code("3197");
		poac.setOrg2Code("436");
		poac.setElementCatalog("WX");
		
		//Map<String, String> resultMap =poa.getApprCancel(poac);
		
		//System.out.println(resultMap);
		
		//p_session_id=-1，p_period=201812，p_pay_org2_code=3197，p_org2_code=436，p_element_catalog=WX
		context.close();

	}

}
