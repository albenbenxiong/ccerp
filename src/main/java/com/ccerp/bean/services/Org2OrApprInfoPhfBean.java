package com.ccerp.bean.services;

import java.util.List;

public class Org2OrApprInfoPhfBean {

	private String Total;
	private String  SumPhfEe;
	private String SumPhfEr ;

	private List<Org2OrApprInfoPhfBeanBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<Org2OrApprInfoPhfBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<Org2OrApprInfoPhfBeanBase> rows) {
		Rows = rows;
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
