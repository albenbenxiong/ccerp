package com.ccerp.bean.services;

import java.util.List;

public class ElementLov {
	
	/*RowNum：序号
	ElementCode：社保项目代码
	ElementName：社保项目名称
	Total：总数据行数*/
	
	private List<ElementLov> Rows;
	
	private String Total;
	
	private String ElementCode;
	
	private String ElementName;
	
	private String RowNum;

	public List<ElementLov> getRows() {
		return Rows;
	}

	public void setRows(List<ElementLov> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public String getElementCode() {
		return ElementCode;
	}

	public void setElementCode(String elementCode) {
		ElementCode = elementCode;
	}

	public String getElementName() {
		return ElementName;
	}

	public void setElementName(String elementName) {
		ElementName = elementName;
	}

	public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	
	
	

}
