package com.iss.platform.sysParams.entity;

import com.orm.annotation.Table;
import com.orm.entity.IdEntity;

/**
 * 系统参数
 * 
 * @author yuanhuangd
 *
 */
@Table(name = "sys_base_params", remark = "系统参数")
public class SysParams extends IdEntity {

	private String code;// 参数编号
	private String name;// 参数名称
	private String val;// 参数类型
	private String enable;// 是否启用 1 启用 0 停用
	private String remark;// 备注

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
