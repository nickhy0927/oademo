package com.iss.platform.baseData.entity;

import com.orm.annotation.Table;
import com.orm.entity.IdEntity;

/**
 * 基础数据
 * 
 * @author yuanhuangd
 *
 */
@Table(name = "sys_base_data", remark = "基础数据")
public class BaseData extends IdEntity {
	private String code;// 字典编号
	private String name;// 字典名称
	private String val;// 字典值
	private Integer enable;// 是否启用 1 启用 0 停用
	private String remark;// 备注
	private String sysParamsId; // 系统参数ID

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

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSysParamsId() {
		return sysParamsId;
	}

	public void setSysParamsId(String sysParamsId) {
		this.sysParamsId = sysParamsId;
	}
}
