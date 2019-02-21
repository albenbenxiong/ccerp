package com.ccerp.test;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccerp.bean.condation.CostRuleCondition;
import com.ccerp.bean.condation.SocinsCondition;
import com.ccerp.dao.CostRuleDao;
import com.ccerp.dao.CostTrsDao;
import com.ccerp.dao.SocinsDao;

public class Testget_socins_data_add {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-context-all.xml");
		
		
		//SocinsDao socinDao=context.getBean(SocinsDao.class);
		CostRuleDao costRuleDao=context.getBean(CostRuleDao.class);
		CostTrsDao costTrsDao=context.getBean(CostTrsDao.class);
		
			//成本规则设置新增修改 ,   然后计提、缴费凭证列表  生成凭证那部分 
		CostRuleCondition crc =new  CostRuleCondition();
		
	/*	p_session_id:-1
		p_cost_type:JT
		p_pay_org2_code3197
		p_comemp_flag:Y
		p_people_group_id:20062
		p_costed_flag:Y
		p_element_code:ENDOWMENT_EE
		p_d_segment1 :0001*/
		
		crc.setSessionId("-1");
		crc.setCostType("JT");
		crc.setPayOrg2Code("3197");
		crc.setComempFlag("Y");
		crc.setPeopleGroupId("20062");
		crc.setCostedFlag("Y");
		crc.setElementCode("ENDOWMENT_EE");
		crc.setDsegment1("001");
	/*	Map<String,String> resultMap=  costRuleDao.costRuleSetAdd(crc);
		System.out.println(resultMap);
		*/
		crc.setDsegment1("0001");
		crc.setDsegment2("1000");
		crc.setRuleId("9");
		Map<String,String> resultMap1=  costRuleDao.costRuleSetUpdate(crc);
		System.out.println(resultMap1);
		
		context.close();
		
	}
}
