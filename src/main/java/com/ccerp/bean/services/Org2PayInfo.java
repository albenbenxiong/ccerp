package com.ccerp.bean.services;

import java.util.List;

public class Org2PayInfo {

	private String Total;
	
	private List<Org2PayInfoBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<Org2PayInfoBase> getRows() {
		return Rows;
	}

	public void setRows(List<Org2PayInfoBase> rows) {
		Rows = rows;
	}
	
	
	
}
