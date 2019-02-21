package com.ccerp.bean.services;

import java.util.List;

public class EpeopleGroupLov {

	private List<EpeopleGroupLov> Rows;
	private String Total;
	/*RowNum：序号
	PeopleGroupId：费用类别组ID
	PeopleGroupName：费用类别组名称
	Total：总数据行数*/
	
	private String  RowNum;
	private String  PeopleGroupId;
	private String  PeopleGroupName;
	public List<EpeopleGroupLov> getRows() {
		return Rows;
	}
	public void setRows(List<EpeopleGroupLov> rows) {
		Rows = rows;
	}
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public String getRowNum() {
		return RowNum;
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getPeopleGroupId() {
		return PeopleGroupId;
	}
	public void setPeopleGroupId(String peopleGroupId) {
		PeopleGroupId = peopleGroupId;
	}
	public String getPeopleGroupName() {
		return PeopleGroupName;
	}
	public void setPeopleGroupName(String peopleGroupName) {
		PeopleGroupName = peopleGroupName;
	}
	
	
	
	
}
