package com.ccerp.bean.services;

import java.util.List;

public class PayOrg2DataViewSupmedBean {

    private String Total;
    
    private String SumSupmedicalEe;
    private String SumSupmedicalEr ;
    private List<PayOrg2DataViewSupmedBeanBase> Rows;

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<PayOrg2DataViewSupmedBeanBase> getRows() {
		return Rows;
	}

	public void setRows(List<PayOrg2DataViewSupmedBeanBase> rows) {
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
