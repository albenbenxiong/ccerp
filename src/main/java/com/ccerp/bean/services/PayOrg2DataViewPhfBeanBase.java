package com.ccerp.bean.services;


public class PayOrg2DataViewPhfBeanBase {

       
/*    缴费类型为：公积金
    RowNum：序号
    EmpName：员工姓名
    EmpNum：员工编号
    OrgName：员工所在部门
    PhfEe：公积金个人
    PhfEr：公积金单位
    Total：总数据行数*/
	
	private String PhfId;
	private String CheckStatus;
	private String ErrorMessage;
	private String RowNum;
	private String EmpName;
	private String EmpNum;
	private String OrgName;
	private String PhfEe;
	private String PhfEr;
	public String getRowNum() {
		return RowNum;
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getEmpNum() {
		return EmpNum;
	}
	public void setEmpNum(String empNum) {
		EmpNum = empNum;
	}
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	public String getPhfEe() {
		return PhfEe;
	}
	public void setPhfEe(String phfEe) {
		PhfEe = phfEe;
	}
	public String getPhfEr() {
		return PhfEr;
	}
	public void setPhfEr(String phfEr) {
		PhfEr = phfEr;
	}
	public String getPhfId() {
		return PhfId;
	}
	public void setPhfId(String phfId) {
		PhfId = phfId;
	}
	public String getCheckStatus() {
		return CheckStatus;
	}
	public void setCheckStatus(String checkStatus) {
		CheckStatus = checkStatus;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	
}
