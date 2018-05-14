package com.iss.platform.roleMenu.entity;

import com.orm.annotation.Table;
import com.orm.entity.IdEntity;

/**
 * 角色菜单(角色对应的菜单)
 * 
 * @author yuanhuangd
 *
 */
@Table(name = "sys_role_menu", remark = "角色菜单(角色对应的菜单)")
public class RoleMenu extends IdEntity {

	private String menuId;// 菜单ID
	private String roleId;// 角色ID

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
