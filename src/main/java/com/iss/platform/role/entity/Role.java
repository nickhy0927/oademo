package com.iss.platform.role.entity;

import com.orm.annotation.Table;
import com.orm.entity.IdEntity;

/**
 * 系统角色
 * 
 * @author yuanhuangd
 *
 */
@Table(name = "sys_role", remark = "系统角色")
public class Role extends IdEntity {

	private String code;// 编号
	private String name; // 名称
	private String remark;// 备注
	private Integer frozen;// 是否冻结 1：已冻结 0： 未冻结

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getFrozen() {
		return frozen;
	}

	public void setFrozen(Integer frozen) {
		this.frozen = frozen;
	}
}
