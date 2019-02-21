package com.ccerp.bean.services;

import java.util.List;

public class ValueSetComLov {

	
/*	RowNum：序号
	FlexValue：代码
	Description：说明
	Total：总数据行数*/

	
	private String RowNum;
	
	private String FlexValue;
	
	private String Description;
	
	private String Total;
	
	private List<ValueSetComLov> Rows;

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public String getFlexValue() {
		return FlexValue;
	}

	public void setFlexValue(String flexValue) {
		FlexValue = flexValue;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<ValueSetComLov> getRows() {
		return Rows;
	}

	public void setRows(List<ValueSetComLov> rows) {
		Rows = rows;
	}
	
	
	
}
