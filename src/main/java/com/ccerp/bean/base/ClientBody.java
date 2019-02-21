package com.ccerp.bean.base;

import java.util.List;

public class ClientBody {


	//private Integer pageNumber;

	//private Integer pageSize;

	private Integer total;

	private Object bean;

	private List<?> rows;

    private Custom custom;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
	}

}
