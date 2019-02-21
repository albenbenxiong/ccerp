package com.ccerp.bean.services;

import java.util.List;

public class Org2OrApprInfoSupmedTrsBean {

	private String Total;

	private List<Org2OrApprInfoSupmedTrsBeanBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<Org2OrApprInfoSupmedTrsBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<Org2OrApprInfoSupmedTrsBeanBase> rows) {
		Rows = rows;
	}

}
