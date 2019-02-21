package com.ccerp.bean.services;

import java.util.List;

public class CostSkTrsServicesBean {

	private String Total;
	private List<CostSkTrsServicesBeanBase> Rows;
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public List<CostSkTrsServicesBeanBase> getRows() {
		return Rows;
	}
	public void setRows(List<CostSkTrsServicesBeanBase> rows) {
		Rows = rows;
	}
	
}
