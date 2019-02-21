package com.ccerp.bean.services.payObject;

import java.util.List;

public class SkAccountInfo {

	
	private String Total;
	
	private List<SkAccountInfoBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<SkAccountInfoBase> getRows() {
		return Rows;
	}

	public void setRows(List<SkAccountInfoBase> rows) {
		Rows = rows;
	}
	
	
	
}
