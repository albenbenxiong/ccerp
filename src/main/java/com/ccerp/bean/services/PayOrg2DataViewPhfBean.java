package com.ccerp.bean.services;

import java.util.List;

public class PayOrg2DataViewPhfBean {

    private String Total;
    
    private String  SumPhfEe;
    
    private String SumPhfEr;
    
    private List<PayOrg2DataViewPhfBeanBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<PayOrg2DataViewPhfBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<PayOrg2DataViewPhfBeanBase> rows) {
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
