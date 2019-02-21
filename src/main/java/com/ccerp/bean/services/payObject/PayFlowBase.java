package com.ccerp.bean.services.payObject;

public class PayFlowBase {

	private String RowNum;
	private String PayTrsId;
	private String PayOrg2Name;
	private String PayTotalVal;
	private String PayName;
	private String PayStatus;
	private String UpdFlag;
	private String ElementCatalogName;
	private String PayComments;
	private String Org2Name;
	public String getRowNum() {
		return RowNum;
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getPayTrsId() {
		return PayTrsId;
	}
	public void setPayTrsId(String payTrsId) {
		PayTrsId = payTrsId;
	}
	public String getPayOrg2Name() {
		return PayOrg2Name;
	}
	public void setPayOrg2Name(String payOrg2Name) {
		PayOrg2Name = payOrg2Name;
	}
	public String getPayTotalVal() {
		return PayTotalVal;
	}
	public void setPayTotalVal(String payTotalVal) {
		PayTotalVal = payTotalVal;
	}
	public String getPayName() {
		return PayName;
	}
	public void setPayName(String payName) {
		PayName = payName;
	}
	
	public String getElementCatalogName() {
		return ElementCatalogName;
	}
	public void setElementCatalogName(String elementCatalogName) {
		ElementCatalogName = elementCatalogName;
	}
	public String getPayStatus() {
		return PayStatus;
	}
	public void setPayStatus(String payStatus) {
		PayStatus = payStatus;
	}
	public String getPayComments() {
		return PayComments;
	}
	public void setPayComments(String payComments) {
		PayComments = payComments;
	}
	public String getOrg2Name() {
		return Org2Name;
	}
	public void setOrg2Name(String org2Name) {
		Org2Name = org2Name;
	}
	public String getUpdFlag() {
		return UpdFlag;
	}
	public void setUpdFlag(String updFlag) {
		UpdFlag = updFlag;
	}
	
}
