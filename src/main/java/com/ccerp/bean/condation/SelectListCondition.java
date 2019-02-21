package com.ccerp.bean.condation;

public class SelectListCondition {
 
	private String  p_rows;
	private String  p_pages;
	private String  p_session_id;
	private String  p_pay_org2_name;
	private String  p_org2_name;
	private String  p_period;
	private String  p_org_name;
	
	private String  p_emp_name;
	private String  p_emp_number;
	public String getP_rows() {
		return p_rows;
	}
	public void setP_rows(String p_rows) {
		this.p_rows = p_rows;
	}
	public String getP_pages() {
		return p_pages;
	}
	public void setP_pages(String p_pages) {
		this.p_pages = p_pages;
	}
	public String getP_session_id() {
		return p_session_id;
	}
	public void setP_session_id(String p_session_id) {
		this.p_session_id = p_session_id;
	}
	public String getP_pay_org2_name() {
		return p_pay_org2_name;
	}
	public void setP_pay_org2_name(String p_pay_org2_name) {
		this.p_pay_org2_name = p_pay_org2_name;
	}
	public String getP_period() {
		return p_period;
	}
	public void setP_period(String p_period) {
		this.p_period = p_period;
	}
	public String getP_org_name() {
		return p_org_name;
	}
	public void setP_org_name(String p_org_name) {
		this.p_org_name = p_org_name;
	}
	public String getP_emp_name() {
		return p_emp_name;
	}
	public void setP_emp_name(String p_emp_name) {
		this.p_emp_name = p_emp_name;
	}
	public String getP_emp_number() {
		return p_emp_number;
	}
	public void setP_emp_number(String p_emp_number) {
		this.p_emp_number = p_emp_number;
	}
	public String getP_org2_name() {
		return p_org2_name;
	}
	public void setP_org2_name(String p_org2_name) {
		this.p_org2_name = p_org2_name;
	}
	@Override
	public String toString() {
		return "SelectListCondition [p_rows=" + p_rows + ", p_pages=" + p_pages
				+ ", p_session_id=" + p_session_id + ", p_pay_org2_name="
				+ p_pay_org2_name + ", p_org2_name=" + p_org2_name
				+ ", p_period=" + p_period + ", p_org_name=" + p_org_name
				+ ", p_emp_name=" + p_emp_name + ", p_emp_number="
				+ p_emp_number + "]";
	}
	
	
	
}
