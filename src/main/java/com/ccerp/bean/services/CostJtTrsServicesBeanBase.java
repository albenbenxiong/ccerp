package com.ccerp.bean.services;

public class CostJtTrsServicesBeanBase {

/*	RowNum：序号
	CostTrsId：主键PK
	PayPeriod：财务期间
	CostTypeName：成本类型名称
	PayOrg2Name：代垫单位
	ElementCatalogName：缴费类型
	BatchName：凭证批名
	RunStatus：状态
	Total：总数据行数*/
	
	private String RowNum;
	
	private String CostTrsId;
	private String PayPeriod;
	private String CostTypeName;
	private String PayOrg2Name;
	private String ElementCatalogName;
	private String BatchName;
	private String RunStatus;
	public String getRowNum() {
		return RowNum;
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getCostTrsId() {
		return CostTrsId;
	}
	public void setCostTrsId(String costTrsId) {
		CostTrsId = costTrsId;
	}
	public String getPayPeriod() {
		return PayPeriod;
	}
	public void setPayPeriod(String payPeriod) {
		PayPeriod = payPeriod;
	}
	public String getCostTypeName() {
		return CostTypeName;
	}
	public void setCostTypeName(String costTypeName) {
		CostTypeName = costTypeName;
	}
	public String getPayOrg2Name() {
		return PayOrg2Name;
	}
	public void setPayOrg2Name(String payOrg2Name) {
		PayOrg2Name = payOrg2Name;
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
	public String getRunStatus() {
		return RunStatus;
	}
	public void setRunStatus(String runStatus) {
		RunStatus = runStatus;
	}
	@Override
	public String toString() {
		return "CostJtTrsServicesBeanBase [BatchName=" + BatchName
				+ ", CostTrsId=" + CostTrsId + ", CostTypeName=" + CostTypeName
				+ ", ElementCatalogName=" + ElementCatalogName
				+ ", PayOrg2Name=" + PayOrg2Name + ", PayPeriod=" + PayPeriod
				+ ", RowNum=" + RowNum + ", RunStatus=" + RunStatus + "]";
	}
	
	
	
	
}
