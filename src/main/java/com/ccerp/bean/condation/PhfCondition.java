package com.ccerp.bean.condation;

public class PhfCondition  extends BaseCondition{


	private String phfEe;
	private String phfEr;
	private String phfId;


	
	public String getPhfEe() {
		return phfEe;
	}



	public void setPhfEe(String phfEe) {
		this.phfEe = phfEe;
	}



	public String getPhfEr() {
		return phfEr;
	}



	public void setPhfEr(String phfEr) {
		this.phfEr = phfEr;
	}



	public String getPhfId() {
		return phfId;
	}



	public void setPhfId(String phfId) {
		this.phfId = phfId;
	}



	@Override
	public String toString() {
		return "PhfCondition [PhfEe=" + phfEe + ", PhfEr=" + phfEr + ", PhfId="
				+phfId + ", rows=" + getRows() + ",pages="
				+ getPages() + ", sessionId=" + getSessionId()
				+ ", period=" + getPeriod() + ", payOrg2Code="
				+ getPayOrg2Code() + ", org2COde=" + getOrg2Code()
				+ ", orgId=" + getOrgId() + ",empName="
				+ getEmpName() + ", empNumber=" + getEmpNumber()
				+ ", personId=" + getPersonId() + ", periodFrom="
				+ getPeriodFrom() + ", periodTo=" + getPeriodTo() + "]";
	}
	
	
	
	
}
