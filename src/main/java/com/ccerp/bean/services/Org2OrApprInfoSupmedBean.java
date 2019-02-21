package com.ccerp.bean.services;

import java.util.List;

public class Org2OrApprInfoSupmedBean {

	private String Total;

	private String SumSupmedicalEe;
	private String SumSupmedicalEr;
	private List<Org2OrApprInfoSupmedBeanBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<Org2OrApprInfoSupmedBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<Org2OrApprInfoSupmedBeanBase> rows) {
		Rows = rows;
	}

	public String getSumSupmedicalEe() {
		return SumSupmedicalEe;
	}

	public void setSumSupmedicalEe(String sumSupmedicalEe) {
		SumSupmedicalEe = sumSupmedicalEe;
	}

	public String getSumSupmedicalEr() {
		return SumSupmedicalEr;
	}

	public void setSumSupmedicalEr(String sumSupmedicalEr) {
		SumSupmedicalEr = sumSupmedicalEr;
	}

	
	
	
	
}
