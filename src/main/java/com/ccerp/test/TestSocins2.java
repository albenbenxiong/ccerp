package com.ccerp.test;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccerp.bean.condation.SocinsCondition;
import com.ccerp.dao.SocinsDao;

public class TestSocins2 {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-context-all.xml");

		SocinsDao socinsDao = context.getBean(SocinsDao.class);

		SocinsCondition scondition = new SocinsCondition();
		/*
		 * : sessionID：-1，期间：201812，org2code:436,payOrg2Code:3179 :
		 * personId:233228 : 五险新增的接口 : InjEe:1000
		 */
		scondition.setSessionId("-1");
		scondition.setPeriod("201812");
		scondition.setPayOrg2Code("3179");
		scondition.setPersonId("233228");
		scondition.setOrg2Code("436");
		scondition.setSocinsId("66");
		scondition.setInjEe("88");

		Map<String, String> result = socinsDao.socinsDataUpdate(scondition);

		System.out.println(result);
		context.close();

	}
}
