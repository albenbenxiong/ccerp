package com.ccerp.bean.condation;

public class SupmedCondition extends BaseCondition {

	private String supmedicalEe;
	private String supmedicalEr;
	private String supmedId;

	
	public String getSupmedicalEe() {
		return supmedicalEe;
	}


	public void setSupmedicalEe(String supmedicalEe) {
		this.supmedicalEe = supmedicalEe;
	}


	public String getSupmedicalEr() {
		return supmedicalEr;
	}


	public void setSupmedicalEr(String supmedicalEr) {
		this.supmedicalEr = supmedicalEr;
	}


	public String getSupmedId() {
		return supmedId;
	}


	public void setSupmedId(String supmedId) {
		this.supmedId = supmedId;
	}


	@Override
	public String toString() {
		return "SupmedCondition [SupmedicalEe=" + supmedicalEe
				+ ", SupmedicalEr=" + supmedicalEr + ", SupmedId=" + supmedId
				+ ", rows=" + getRows() + ", pages=" + getPages()
				+ ", sessionId=" + getSessionId() + ", getPeriod()="
				+ getPeriod() + ", getPayOrg2Code()=" + getPayOrg2Code()
				+ ", getOrg2Code()=" + getOrg2Code() + ", getOrgId()="
				+ getOrgId() + ", getEmpName()=" + getEmpName()
				+ ", getEmpNumber()=" + getEmpNumber() + ", getPersonId()="
				+ getPersonId() + ", getPeriodFrom()=" + getPeriodFrom()
				+ ", getPeriodTo()=" + getPeriodTo() + "]";
	}

	

}
