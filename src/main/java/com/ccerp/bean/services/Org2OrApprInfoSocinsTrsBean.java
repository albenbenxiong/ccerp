package com.ccerp.bean.services;

import java.util.List;

public class Org2OrApprInfoSocinsTrsBean {

	private String Total;

	private List<Org2OrApprInfoSocinsTrsBeanBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<Org2OrApprInfoSocinsTrsBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<Org2OrApprInfoSocinsTrsBeanBase> rows) {
		Rows = rows;
	}

}
