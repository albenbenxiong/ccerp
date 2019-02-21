package com.ccerp.bean.services;

import java.util.List;

public class CostConServicesBean {

	private List<CostConServicesBeanBase> Rows;


	private String Total;

	public List<CostConServicesBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<CostConServicesBeanBase> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

}
