package com.victorzhang.schedule.pojo;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userid;//用户id
	private String roleid;//角色id
	private String departid;//部门id
	private String username;//用户名
	private String password;//密码
	private String realname;//真实姓名
	private String useridcard;//身份证号
	private String usermobile;//手机号
	private String usermail;//邮箱地址
	private String randomcode;//随机码（生成重设密码链接时候使用）
	
	public User() {
		super();
	}

	public User(String userid, String roleid, String departid, String realname) {
		super();
		this.userid = userid;
		this.roleid = roleid;
		this.departid = departid;
		this.realname = realname;
	}
	
	

	public User(String userid, String roleid, String departid, String username,
			String password, String realname, String useridcard,
			String usermobile, String usermail) {
		super();
		this.userid = userid;
		this.roleid = roleid;
		this.departid = departid;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.useridcard = useridcard;
		this.usermobile = usermobile;
		this.usermail = usermail;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getDepartid() {
		return departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUseridcard() {
		return useridcard;
	}

	public void setUseridcard(String useridcard) {
		this.useridcard = useridcard;
	}

	public String getUsermobile() {
		return usermobile;
	}

	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public String getUsermail() {
		return usermail;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}
	public String getRandomcode() {
		return randomcode;
	}
	public void setRandomcode(String randomcode) {
		this.randomcode = randomcode;
	}

}
