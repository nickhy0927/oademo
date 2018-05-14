package com.iss.platform.menu.entity;

import com.orm.annotation.Table;
import com.orm.entity.IdEntity;

/**
 * 系统菜单
 * 
 * @author yuanhuangd
 *
 */
@Table(name = "sys_menu", remark = "系统菜单")
public class Menu extends IdEntity {

	private String code;// 菜单编号
	private String name;// 菜单名称
	private String url;// 菜单地址
	private String alias;// 菜单别名
	private String remark;// 备注
	private String menuId;// 上级菜单
	private Integer enable;// 是否停用1 启用 0 停用
	private Integer shows; // 是否显示 1显示 0 隐藏

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getShows() {
		return shows;
	}

	public void setShows(Integer shows) {
		this.shows = shows;
	}

}
