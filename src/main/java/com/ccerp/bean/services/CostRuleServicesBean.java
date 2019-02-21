package com.ccerp.bean.services;

import java.util.List;

public class CostRuleServicesBean {
	private List<CostRuleServicesBeanBase> Rows;
	
	private String Total;

	public List<CostRuleServicesBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<CostRuleServicesBeanBase> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	
	
}
