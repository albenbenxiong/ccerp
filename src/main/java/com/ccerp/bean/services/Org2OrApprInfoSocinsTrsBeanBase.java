package com.ccerp.bean.services;


public class Org2OrApprInfoSocinsTrsBeanBase  extends Org2OrApprInfoSocinsBeanBase{

	/*JTBatchName：计提凭证批名
	JTStatus：计提状态
	JFBatchName：缴费凭证批名
	JFStatus：缴费状态*/
	
	
	private String JTBatchName;
	private String JTStatus;
	private String JFBatchName;
	private String JFStatus;
	private String RecStatusId;
	public String getJTBatchName() {
		return JTBatchName;
	}
	public void setJTBatchName(String jTBatchName) {
		JTBatchName = jTBatchName;
	}
	public String getJTStatus() {
		return JTStatus;
	}
	public void setJTStatus(String jTStatus) {
		JTStatus = jTStatus;
	}
	public String getJFBatchName() {
		return JFBatchName;
	}
	public void setJFBatchName(String jFBatchName) {
		JFBatchName = jFBatchName;
	}
	public String getJFStatus() {
		return JFStatus;
	}
	public void setJFStatus(String jFStatus) {
		JFStatus = jFStatus;
	}
	public String getRecStatusId() {
		return RecStatusId;
	}
	public void setRecStatusId(String recStatusId) {
		RecStatusId = recStatusId;
	}
	
	
	
	
}
