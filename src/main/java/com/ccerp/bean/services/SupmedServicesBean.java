package com.ccerp.bean.services;

import java.util.List;

public class SupmedServicesBean {

	
	private List<SupmedServicesBeanBase> Rows;
	
	private String Total;
	
	private String SumSupmedicalEe;
	private String SumSupmedicalEr;

	public List<SupmedServicesBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<SupmedServicesBeanBase> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
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
