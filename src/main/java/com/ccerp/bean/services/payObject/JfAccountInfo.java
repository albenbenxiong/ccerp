package com.ccerp.bean.services.payObject;

import java.util.List;

public class JfAccountInfo {

	
	private String Total;
	
	private List<JfAccountInfoBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<JfAccountInfoBase> getRows() {
		return Rows;
	}

	public void setRows(List<JfAccountInfoBase> rows) {
		Rows = rows;
	}
	
	
	
}
