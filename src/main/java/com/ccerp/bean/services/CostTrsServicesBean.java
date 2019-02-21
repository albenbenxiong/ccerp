package com.ccerp.bean.services;

import java.util.List;

public class CostTrsServicesBean {

	private String Total;
	private List<CostTrsServicesBeanBase> Rows;
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public List<CostTrsServicesBeanBase> getRows() {
		return Rows;
	}
	public void setRows(List<CostTrsServicesBeanBase> rows) {
		Rows = rows;
	}
	
}
