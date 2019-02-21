package com.ccerp.bean.services.payObject;

import java.util.List;

public class PayFlow {

	private String Total;
	
	private List<PayFlowBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<PayFlowBase> getRows() {
		return Rows;
	}

	public void setRows(List<PayFlowBase> rows) {
		Rows = rows;
	}
}
