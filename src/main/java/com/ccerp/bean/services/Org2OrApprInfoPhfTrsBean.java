package com.ccerp.bean.services;

import java.util.List;

public class Org2OrApprInfoPhfTrsBean {

	private String Total;
                  
	private List<Org2OrApprInfoPhfTrsBeanBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<Org2OrApprInfoPhfTrsBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<Org2OrApprInfoPhfTrsBeanBase> rows) {
		Rows = rows;
	}

}
