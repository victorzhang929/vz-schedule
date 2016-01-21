package com.victorzhang.schedule.pojo;

/**
 * 部门模型
 * @author 40808
 *
 */
public class Depart {

	private String departid;//学院id
	private String dname;//学院名称
	private String dphone;//学院电话
	private String address;//学院地址
	private String connperson;//联系人
	private String connphone;//联系人电话
	
	public String getDepartid() {
		return departid;
	}
	public void setDepartid(String departid) {
		this.departid = departid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDphone() {
		return dphone;
	}
	public void setDphone(String dphone) {
		this.dphone = dphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConnperson() {
		return connperson;
	}
	public void setConnperson(String connperson) {
		this.connperson = connperson;
	}
	public String getConnphone() {
		return connphone;
	}
	public void setConnphone(String connphone) {
		this.connphone = connphone;
	}
	
}
