package com.ccerp.test;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccerp.bean.condation.SupmedCondition;
import com.ccerp.dao.SupmedDao;
import com.ccerp.utils.GsonUtils;



public class TestSupmed {
		public static void main(String[] args) {

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"spring-context-all.xml");
			
			SupmedDao supMedDao =context.getBean(SupmedDao.class);
			SupmedCondition supmedCondition = new SupmedCondition();
			
			/*p_session_id    IN VARCHAR2,
			p_period        IN VARCHAR2,
			p_pay_org2_code IN VARCHAR2,
			p_org2_code     IN VARCHAR2,
			p_person_id     IN VARCHAR2,
			p_supmedical_ee IN VARCHAR2,
			p_supmedical_er IN VARCHAR2,
			p_message       OUT VARCHAR2*/
			
			
			/*supmedCondition.setRows("4");
			supmedCondition.setPages("1");*/
			supmedCondition.setSessionId("-1");
			supmedCondition.setPeriod("201812");
			supmedCondition.setPersonId("233228");
			supmedCondition.setPayOrg2Code("3197");
			//supmedCondition.setEmpName("肖宁宁");
			
			
			//phfCondition.set
			Map<String,String>  resultMap= supMedDao.supmedDataAdd(supmedCondition);
			//System.out.println(GsonUtils.objectToJson(resultMap)); 
			
			supmedCondition.setSupmedicalEe("100000");
			supmedCondition.setSupmedId("12");
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataUpdate(supmedCondition)));
			
			supmedCondition.setRows("10");
			supmedCondition.setPages("1");
			
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataInfo(supmedCondition)));
			
			
			
			//System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataCopy("-1", "201812", "201811", "3197")));
			
			supmedCondition.setPeriodFrom("201811");
			supmedCondition.setPeriodTo("201812");
			
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedHisDataInfo(supmedCondition)));
			
			
			
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataDel("-1", "12")));
			/*System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataDel("-1", "123")));
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataUpdate(supmedCondition)));
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataInfo(supmedCondition)));
			
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedDataCopy("-1", "", "", "")));
			System.out.println(GsonUtils.objectToJson(supMedDao.supmedHisDataInfo(supmedCondition)));
			*/
			context.close();
			
		}
		
}
