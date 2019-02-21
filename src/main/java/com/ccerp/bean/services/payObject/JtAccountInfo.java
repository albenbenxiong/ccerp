package com.ccerp.bean.services.payObject;

import java.util.List;

public class JtAccountInfo {

	
	private String Total;
	
	private List<JtAccountInfoBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<JtAccountInfoBase> getRows() {
		return Rows;
	}

	public void setRows(List<JtAccountInfoBase> rows) {
		Rows = rows;
	}
	
	
	
}
