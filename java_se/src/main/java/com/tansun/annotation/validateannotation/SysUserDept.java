package com.tansun.annotation.validateannotation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class SysUserDept extends BaseEntity{
	private static final long serialVersionUID = 3776304095517793129L;
	@ParamsCheck
	private Integer id;
	@ParamsCheck
	private String username;
	@ParamsCheck
	private String password;//md5
	@ParamsCheck
	private String salt;
	@ParamsCheck
	private String email;
	@ParamsCheck
	private String mobile;
	@ParamsCheck
	private Integer valid=1;
	/**基于此对象存储部门信息*/
	@ParamsCheck
	private List<String> sysDept;
	@ParamsCheck
	private Date createdTime;
	@ParamsCheck
	private Date modifiedTime;
	@ParamsCheck
	private String createdUser;
	@ParamsCheck
	private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public List<String> getSysDept() {
		return sysDept;
	}
	public void setSysDept(List<String> sysDept) {
		this.sysDept = sysDept;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "SysUserDept [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", email=" + email + ", mobile=" + mobile + ", valid=" + valid + ", sysDept=" + sysDept
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	
	
}
