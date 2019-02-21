package com.ccerp.bean.services;

import java.util.List;

public class CustomMenu {

	
	private String Total;
	
	private List<CustomMenuBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<CustomMenuBase> getRows() {
		return Rows;
	}

	public void setRows(List<CustomMenuBase> rows) {
		Rows = rows;
	}
}
