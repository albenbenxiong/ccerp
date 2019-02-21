package com.ccerp.bean.services;

public class CostSkTrsServicesBeanBase {
	
	/*缴费类型为：五险
	RowNum：序号
	RecStatusId：主键PK
	PeriodName：期间
	PayOrg2Name：代垫单位
	Org2Name：支付单位
	ElementCatalogName ：缴费类型
	BatchName：缴费凭证批名
	Status：缴费状态
	Total：总数据行数*/
	
	
	private String RecStatusId;
	private String PeriodName;
	private String PayOrg2Code;
	private String Org2Code;
	private String PayOrg2Name;
	private String Org2Name;
	private String ElementCatalogName;
	private String BatchName;
	private String Status;
	public String getRecStatusId() {
		return RecStatusId;
	}
	public void setRecStatusId(String recStatusId) {
		RecStatusId = recStatusId;
	}
	public String getPeriodName() {
		return PeriodName;
	}
	public void setPeriodName(String periodName) {
		PeriodName = periodName;
	}
	public String getPayOrg2Name() {
		return PayOrg2Name;
	}
	public void setPayOrg2Name(String payOrg2Name) {
		PayOrg2Name = payOrg2Name;
	}
	public String getOrg2Name() {
		return Org2Name;
	}
	public void setOrg2Name(String org2Name) {
		Org2Name = org2Name;
	}
	public String getElementCatalogName() {
		return ElementCatalogName;
	}
	public void setElementCatalogName(String elementCatalogName) {
		ElementCatalogName = elementCatalogName;
	}
	public String getBatchName() {
		return BatchName;
	}
	public void setBatchName(String batchName) {
		BatchName = batchName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPayOrg2Code() {
		return PayOrg2Code;
	}
	public void setPayOrg2Code(String payOrg2Code) {
		PayOrg2Code = payOrg2Code;
	}
	public String getOrg2Code() {
		return Org2Code;
	}
	public void setOrg2Code(String org2Code) {
		Org2Code = org2Code;
	}

	
	
}
