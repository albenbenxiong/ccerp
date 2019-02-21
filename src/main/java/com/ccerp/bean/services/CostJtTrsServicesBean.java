package com.ccerp.bean.services;

import java.util.List;

public class CostJtTrsServicesBean {

	private List<CostJtTrsServicesBeanBase> Rows;
	
	private String Total;

	public List<CostJtTrsServicesBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<CostJtTrsServicesBeanBase> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}
	
	
	
	
}
