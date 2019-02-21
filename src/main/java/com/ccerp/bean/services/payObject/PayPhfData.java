package com.ccerp.bean.services.payObject;

import java.util.List;

public class PayPhfData {

	private String Total;
	
	private List<PayPhfDataBase> Rows;
	
	private String SumPhfEe;
	private String SumPhfEr;
	
	private String PayName;
	private String TotalSum;
	private String PayTrsId;
	

	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public List<PayPhfDataBase> getRows() {
		return Rows;
	}
	public void setRows(List<PayPhfDataBase> rows) {
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
	public String getPayName() {
		return PayName;
	}
	public void setPayName(String payName) {
		PayName = payName;
	}
	public String getTotalSum() {
		return TotalSum;
	}
	public void setTotalSum(String totalSum) {
		TotalSum = totalSum;
	}
	public String getPayTrsId() {
		return PayTrsId;
	}
	public void setPayTrsId(String payTrsId) {
		PayTrsId = payTrsId;
	}
	
	
	
}
