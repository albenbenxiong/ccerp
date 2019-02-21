package com.ccerp.bean.services;

public class CostTrsServicesBeanBase {
	
	/*RowNum：序号
	CostTrsId：主键PK
	CostAcc：科目
	CostAccName：描述
	Dval：借方金额
	Cval：贷方金额
	Remark：摘要
	Total：总数据行数*/
	
	
	private String RowNum;
	private String CostTrsId;
	private String CostAcc;
	private String Dval;
	private String Cval;
	private String Remark;
	private String CostAccName;
	public String getRowNum() {
		return RowNum;
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getCostTrsId() {
		return CostTrsId;
	}
	public void setCostTrsId(String costTrsId) {
		CostTrsId = costTrsId;
	}
	public String getCostAcc() {
		return CostAcc;
	}
	public void setCostAcc(String costAcc) {
		CostAcc = costAcc;
	}
	public String getDval() {
		return Dval;
	}
	public void setDval(String dval) {
		Dval = dval;
	}
	public String getCval() {
		return Cval;
	}
	public void setCval(String cval) {
		Cval = cval;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getCostAccName() {
		return CostAccName;
	}
	public void setCostAccName(String costAccName) {
		CostAccName = costAccName;
	}
	
	

}
