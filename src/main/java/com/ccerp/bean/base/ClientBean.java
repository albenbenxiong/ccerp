package com.ccerp.bean.base;


public class ClientBean {

	private Integer code;

	private String msg;

	private String notes;

	private ClientBody body;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ClientBody getClientBody() {
		return body;
	}

	public void setClientBody(ClientBody body) {
		this.body = body;
	}


}
