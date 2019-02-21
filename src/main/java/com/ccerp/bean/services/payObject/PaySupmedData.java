package com.ccerp.bean.services.payObject;

import java.util.List;

public class PaySupmedData {

	private String Total;
	
	private List<PaySupmedDataBase> Rows;
	
	private String SumSupmedicalEe;
	
	private String SumSupmedicalEr;
	
	private String PayName;
	private String TotalSum;
	private String PayTrsId;


	
	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public List<PaySupmedDataBase> getRows() {
		return Rows;
	}

	public void setRows(List<PaySupmedDataBase> rows) {
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
