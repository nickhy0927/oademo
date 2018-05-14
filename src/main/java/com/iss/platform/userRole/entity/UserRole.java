package com.iss.platform.userRole.entity;

import com.orm.annotation.Table;
import com.orm.entity.IdEntity;

/**
 * 用户角色(用户对应的角色信息)
 * 
 * @author yuanhuangd
 *
 */
@Table(name = "sys_user_role", remark = "用户角色(用户对应的角色信息)")
public class UserRole extends IdEntity {

	private String userId; // 用户ID
	private String roleId; // 角色ID

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
