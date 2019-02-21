package com.ccerp.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccerp.bean.condation.PayOrgApprCondition;
import com.ccerp.dao.PayOrgApprDao;
import com.ccerp.utils.GsonUtils;
public class TestPayOrgAppr {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-context-all.xml");
		PayOrgApprDao  poad=context.getBean(PayOrgApprDao.class);
		PayOrgApprCondition c = new  PayOrgApprCondition();
		c.setRows("10");
		c.setPages("1");
		c.setElementCatalog("WX");
		c.setSessionId("-1");
		c.setPeriod("201812");
		//c.setComempFlag("");
		//c.setPayOrg2Code("");
		
		
		/*payOrgCondation.setP_session_id("-1");
		payOrgCondation.setP_rows("10");
		payOrgCondation.setP_pages("1");*/
		System.out.println(GsonUtils.objectToJson(poad.getPayOrg2Info(c)));
		//System.out.println(poad.getPayOrg2DataView(payOrgCondation));
		//System.out.println(poad.getPayOrg2Cf(payOrgCondation));
		//System.out.println(poad.getPayOrg2Cancel(payOrgCondation));
		System.out.println("=============================================");
		
		/*System.out.println(poad.getOrg2Info(payOrgCondation));
		System.out.println(poad.getOrg2DataView(payOrgCondation));
		System.out.println(poad.getOrg2Cf(payOrgCondation));
		System.out.println(poad.getOrg2Cancel(payOrgCondation));
		
		System.out.println("=============================================");
		
		System.out.println(poad.getApprInfo(payOrgCondation));
		System.out.println(poad.getApprDataView(payOrgCondation));
		System.out.println(poad.getApprCf(payOrgCondation));
		System.out.println(poad.getApprCancel(payOrgCondation));*/
		
		
		context.close();
		
	}
}
