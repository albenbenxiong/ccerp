package com.ccerp.bean.services;
public class PayOrg {

	
	
	//{"Rows":[{"RowNum":"1","PayOrg2Code":"3197","PayOrg2Name":"集团总部"}],"Total":"1"}
	
	//代垫单位
	private String PayOrg2Code;
	private String PayOrg2Name;

/*	private String RowNum;
	private List<PayOrg> Rows;
	
	private String Total;*/

	public String getPayOrg2Code() {
		return PayOrg2Code;
	}

	public void setPayOrg2Code(String payOrg2Code) {
		PayOrg2Code = payOrg2Code;
	}

	public String getPayOrg2Name() {
		return PayOrg2Name;
	}

	public void setPayOrg2Name(String payOrg2Name) {
		PayOrg2Name = payOrg2Name;
	}

	/*public String getRowNum() {
		return RowNum;
	}

	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}

	public List<PayOrg> getRows() {
		return Rows;
	}

	public void setRows(List<PayOrg> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}*/

}
