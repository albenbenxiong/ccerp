package com.ccerp.bean.services;

import java.util.List;

public class EmpPop {

	/*
	RowNum：序号
	EmpNum：员工编号
	PersonId：Person_id
	EmpName：员工姓名
	OrgId：员工所在部门ID
	OrgName：员工所在单位名称
	Org2Code：支付单位代码
	Org2Name：支付单位名称
	Total：总页数
	 */

	//新增人员
	private String RowNum;
	private String EmpNum;
	private String EmpName;
	private String PersonId;
	private String OrgId;
	private String OrgName;
	private String Org2Code;
	private String Org2Name;
	private String Total;
	
	private List<EmpPop> Rows;

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getEmpNum() {
		return EmpNum;
	}

	public void setEmpNum(String empNum) {
		EmpNum = empNum;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public String getPersonId() {
		return PersonId;
	}

	public void setPersonId(String personId) {
		PersonId = personId;
	}

	public String getOrgId() {
		return OrgId;
	}

	public void setOrgId(String orgId) {
		OrgId = orgId;
	}

	public String getOrgName() {
		return OrgName;
	}

	public void setOrgName(String orgName) {
		OrgName = orgName;
	}

	public String getOrg2Code() {
		return Org2Code;
	}

	public void setOrg2Code(String org2Code) {
		Org2Code = org2Code;
	}

	public String getOrg2Name() {
		return Org2Name;
	}

	public void setOrg2Name(String org2Name) {
		Org2Name = org2Name;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<EmpPop> getRows() {
		return Rows;
	}

	public void setRows(List<EmpPop> rows) {
		Rows = rows;
	}
	
	
	
	

}
