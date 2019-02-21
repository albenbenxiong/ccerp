package com.ccerp.bean.services;

import java.util.List;

public class EmpOrg {

/*	RowNum：序号
	OrgId：员工所在部门ID
	OrgName：员工所在部门名称
	Total：总页数*/
	
	//员工所在部门
	private String RowNum;
	
	private String  OrgId;
	
	private String  OrgName;
	
	private String  Total;
	
	
	private List<EmpOrg> Rows;


	public String getRowNum() {
		return RowNum;
	}


	public void setRowNum(String rowNum) {
		RowNum = rowNum;
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


	public String getTotal() {
		return Total;
	}


	public void setTotal(String total) {
		Total = total;
	}


	public List<EmpOrg> getRows() {
		return Rows;
	}


	public void setRows(List<EmpOrg> rows) {
		Rows = rows;
	}
	
}
