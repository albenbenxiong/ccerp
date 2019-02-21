package com.ccerp.bean.services;

import java.util.List;

public class PhfServicesBean {

	
	private List<PhfServicesBeanBase> Rows;
	
	private String Total;
	private String  SumPhfEe;
	private String  SumPhfEr;

	public List<PhfServicesBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<PhfServicesBeanBase> rows) {
		Rows = rows;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public String getSumPhfEe() {
		return SumPhfEe;
	}

	public void setSumPhfEe(String sumPhfEe) {
		SumPhfEe = sumPhfEe;
	}

	public String getSumPhfEr() {
		return SumPhfEr;
	}

	public void setSumPhfEr(String sumPhfEr) {
		SumPhfEr = sumPhfEr;
	}
	
	
	
}
