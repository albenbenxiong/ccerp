package com.ccerp.bean.condation;

public class PayOrgApprCondition  extends BaseCondition{
	
	private String  elementCatalog;
	
	private String  comempFlag;
	private String  statusFlag;
	private String  payStatus;
	private String  recStatusId;
	private String  payOrg2Comments;
	public String getElementCatalog() {
		return elementCatalog;
	}
	public void setElementCatalog(String elementCatalog) {
		this.elementCatalog = elementCatalog;
	}
	public String getComempFlag() {
		return comempFlag;
	}
	public void setComempFlag(String comempFlag) {
		this.comempFlag = comempFlag;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	
	public String getRecStatusId() {
		return recStatusId;
	}
	public void setRecStatusId(String recStatusId) {
		this.recStatusId = recStatusId;
	}
	public String getPayOrg2Comments() {
		return payOrg2Comments;
	}
	public void setPayOrg2Comments(String payOrg2Comments) {
		this.payOrg2Comments = payOrg2Comments;
	}
	@Override
	public String toString() {
		return "PayOrgApprCondition [elementCatalog=" + elementCatalog
				+ ", comempFlag=" + comempFlag + ", statusFlag=" + statusFlag
				+ ", rows=" + getRows() + ", pages=" + getPages()
				+ ", sessionId=" + getSessionId() + ", period="
				+ getPeriod() + ", payOrg2Code=" + getPayOrg2Code()
				+ ", org2Code=" + getOrg2Code() + ", orgId="
				+ getOrgId() + ", empName=" + getEmpName()
				+ ", empNumber=" + getEmpNumber() + ", persion="
				+ getPersonId() + ", periodFrom=" + getPeriodFrom()
				+ ", periodTo=" + getPeriodTo() + "]";
	}
	
	
	
}
