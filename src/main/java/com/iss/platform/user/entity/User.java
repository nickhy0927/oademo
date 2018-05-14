package com.iss.platform.user.entity;

import java.util.Date;

import com.orm.annotation.Table;
import com.orm.entity.IdEntity;

/**
 * 系统用户
 * 
 * @author yuanhuangd
 *
 */
@Table(name = "sys_user", remark = "系统用户表")
public class User extends IdEntity {

	private String realName;// 真实姓名
	private String loginName;// 登录账号
	private String password;// 密码
	private Integer locked;// 是否锁定
	private Integer enable;// 是否启用
	private String email;// 有效
	private String mobile;// 电话号码
	private String remark;// 备注
	private String position;// 岗位
	private Date lastLoginTime;// 最后一次登录事件

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		return "User [realName=" + realName + ", loginName=" + loginName + ", password=" + password + ", locked="
				+ locked + ", enable=" + enable + ", email=" + email + ", mobile=" + mobile + ", remark=" + remark
				+ ", position=" + position + ", lastLoginTime=" + lastLoginTime + ", id=" + id + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", status=" + status + "]";
	}

}
