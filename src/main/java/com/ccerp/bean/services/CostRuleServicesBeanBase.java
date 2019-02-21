package com.ccerp.bean.services;

public class CostRuleServicesBeanBase {

	/*
	 * RowNum：序号 RuleId：主键PK PayOrg2Name：代垫单位名称 CostTypeName：成本类型名称
	 * ComempFlag：是否本单位员工 CostTypeName：费用类型 CostFlag：是否成本计算 ElementName：社保项目
	 * DSegment：借方科目组合 CSegment：贷方科目组合  PeopleGroupName：费用类型
	 */ 

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getRuleId() {
		return RuleId;
	}

	public void setRuleId(String ruleId) {
		RuleId = ruleId;
	}

	public String getPayOrg2Name() {
		return PayOrg2Name;
	}

	public void setPayOrg2Name(String payOrg2Name) {
		PayOrg2Name = payOrg2Name;
	}

	public String getCostTypeName() {
		return CostTypeName;
	}

	public void setCostTypeName(String costTypeName) {
		CostTypeName = costTypeName;
	}

	public String getComempFlag() {
		return ComempFlag;
	}

	public void setComempFlag(String comempFlag) {
		ComempFlag = comempFlag;
	}

	public String getCostFlag() {
		return CostFlag;
	}

	public void setCostFlag(String costFlag) {
		CostFlag = costFlag;
	}

	public String getElementName() {
		return ElementName;
	}

	public void setElementName(String elementName) {
		ElementName = elementName;
	}

	public String getDSegment() {
		return DSegment;
	}

	public void setDSegment(String dSegment) {
		DSegment = dSegment;
	}

	public String getCSegment() {
		return CSegment;
	}

	public void setCSegment(String cSegment) {
		CSegment = cSegment;
	}

	private String RowNum;
	private String RuleId;
	private String PayOrg2Name;
	private String CostTypeName;
	private String ComempFlag;
	private String CostFlag;
	private String ElementName;
	private String DSegment;
	private String CSegment;
	
	private String PeopleGroupName;

	public String getPeopleGroupName() {
		return PeopleGroupName;
	}

	public void setPeopleGroupName(String peopleGroupName) {
		PeopleGroupName = peopleGroupName;
	}
	
	

}
