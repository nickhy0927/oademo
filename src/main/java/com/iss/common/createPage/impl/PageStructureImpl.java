package com.iss.common.createPage.impl;

import java.io.File;
import java.io.IOException;

import com.iss.common.createPage.intf.PageStructure;
import com.iss.common.createPage.utils.CreatePage;
import com.iss.common.createPage.utils.UtilPath;
import com.orm.utils.FileTools;

public abstract class PageStructureImpl implements PageStructure {

	private StringBuffer header() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\r\n");
		buffer.append("<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\"%>\r\n");
		buffer.append("<%@ taglib uri=\"http://www.common.page/core\" prefix=\"page\"%>\r\n");
		buffer.append("<c:set value=\"${pageContext.request.contextPath}\" var=\"ctx\"/>\r\n");
		return buffer;
	}

	private StringBuffer body(StringBuffer buffer) {
		StringBuffer body = new StringBuffer();
		body.append("<page:extends name=\"body\">\r\n");
		body.append("	<article class=\"page-container\">\r\n");
		body.append("		<form class=\"form form-horizontal\" id=\"formId\">\r\n");
		body.append(buffer);
		body.append("		</form>\r\n");
		body.append("	</article>\r\n");
		body.append("</page:extends>\r\n");
		return body;
	}

	private StringBuffer css(StringBuffer buffer) {
		StringBuffer css = new StringBuffer();
		css.append("<page:extends name=\"css\">\r\n");
		css.append(buffer);
		css.append("</page:extends>\r\n");
		return css;
	}

	private StringBuffer title(StringBuffer buffer) {
		StringBuffer title = new StringBuffer();
		title.append("<page:extends name=\"title\">\r\n");
		title.append(buffer);
		title.append("</page:extends>\r\n");
		return title;
	}

	private StringBuffer javascript(StringBuffer buffer) {
		StringBuffer javascript = new StringBuffer();
		javascript.append("<page:extends name=\"javascript\">\r\n");
		javascript.append(buffer);
		javascript.append("</page:extends>\r\n");
		return javascript;
	}

	private StringBuffer footer() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<jsp:include page=\"/parent/basepage.jsp\" />\r\n");
		return buffer;
	}

	protected String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public void getPage(CreatePage createPage,String fileName) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append(header());
		buffer.append(title(title(createPage)));
		buffer.append(css(css()));
		buffer.append(javascript(javascript(createPage)));
		buffer.append(body(body(createPage)));
		buffer.append(footer());

		String createFileName = toLowerCaseFirstOne(createPage.getClassName()) + fileName;
		File file = new File(UtilPath.getWorksPath() + File.separator + createPage.getPagePath());
		if (!file.exists()) {
			file.mkdirs();
		}
		String path = file.getAbsolutePath() + "/" + createFileName;
		file = new File(path);
		try {
			if (!file.exists()) {
				file.createNewFile();
				FileTools.WriteStringToFile(path, buffer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String create_div() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("			<div class=\"row cl\">\r\n");
		buffer.append("				<label class=\"form-label col-xs-3 col-sm-2\">\r\n");
		buffer.append("					<span class=\"c-red\">*</span>\r\n");
		buffer.append("					$fieldName：\r\n");
		buffer.append("				</label>\r\n");
		buffer.append("				<div class=\"formControls col-xs-9 col-sm-10\">\r\n");
		buffer.append("					<input type=\"text\" class=\"input-text\" value=\"\" placeholder=\"请输入$fieldName\" id=\"$fieldName\" name=\"$fieldName\">\r\n");
		buffer.append("				</div>\r\n");
		buffer.append("			</div>\r\n");
		return buffer.toString();
	}
	
	protected String edit_div() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("			<div class=\"row cl\">\r\n");
		buffer.append("				<label class=\"form-label col-xs-3 col-sm-2\">\r\n");
		buffer.append("					<span class=\"c-red\">*</span>\r\n");
		buffer.append("					$fieldName：\r\n");
		buffer.append("				</label>\r\n");
		buffer.append("				<div class=\"formControls col-xs-9 col-sm-10\">\r\n");
		buffer.append("					<input type=\"text\" class=\"input-text\" value=\"$fieldValueName\" placeholder=\"请输入$fieldName\" id=\"$fieldName\" name=\"$fieldName\">\r\n");
		buffer.append("				</div>\r\n");
		buffer.append("			</div>\r\n");
		return buffer.toString();
	}
	
	protected StringBuffer button() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("			<div class=\"row cl\" style=\"text-align: right;margin-right: 2px\">\r\n");
		buffer.append("				<button class=\"btn btn-success\" type=\"submit\">\r\n");
		buffer.append("					<i class=\"Hui-iconfont Hui-iconfont-save\"></i>\r\n");
		buffer.append("			 		&nbsp;保存信息\r\n");
		buffer.append("				</button>\r\n");
		buffer.append("			</div>\r\n");
		return buffer;
	}
}
