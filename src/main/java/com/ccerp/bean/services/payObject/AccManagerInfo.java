package com.ccerp.bean.services.payObject;

import java.util.List;

public class AccManagerInfo {

	private String Total;
	
	private List<AccManagerInfoBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<AccManagerInfoBase> getRows() {
		return Rows;
	}

	public void setRows(List<AccManagerInfoBase> rows) {
		Rows = rows;
	}
	
	
	
}
