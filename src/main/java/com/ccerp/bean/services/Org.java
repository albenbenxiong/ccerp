package com.ccerp.bean.services;
public class Org {

	
	//支付单位
	private String Org2Code;

	private String Org2Name;
/*
	private List<Org> Rows;
	private String RowNum;

	private String Total;*/
	/*
	RowNum：序号
	Org2Code：支付单位代码
	Org2Name：支付单位名称
	Total：总页数*/
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

	/*public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public List<Org> getRows() {
		return Rows;
	}

	public void setRows(List<Org> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}
	*/
	
}
