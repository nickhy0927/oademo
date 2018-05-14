package com.orm.entity;

import java.util.Date;

import com.orm.annotation.Column;
import com.orm.constant.SysConstant;

/**
 * 基础类
 * 
 * @author yuanhuangd
 *
 */
public class IdEntity {

	/**
	 * 主键ID
	 */
	@Column(isKey = true)
	protected String id;

	/**
	 * 新增时间
	 */
	@Column(createTime = true)
	protected Date createTime;

	/**
	 * 修改时间
	 */
	protected Date updateTime;

	/**
	 * 有效状态 1 ：有效 ，0 ： 无效
	 */
	@Column(status = SysConstant.DataStatus.VALID)
	protected Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
