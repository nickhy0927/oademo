package com.iss.common.createPage.utils;

public class CreatePage {

	private String packageName;
	private String className;
	private String fullPath;
	private String selectName;
	private String pagePath;
	
	public String getPagePath() {
		return pagePath;
	}
	
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getSelectName() {
		return selectName;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFullPath() {
		return fullPath;
	}
	
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	@Override
	public String toString() {
		return "CreatePage [packageName=" + packageName + ", className=" + className + ", fullPage=" + fullPath
				+ ", selectName=" + selectName + "]";
	}

}
