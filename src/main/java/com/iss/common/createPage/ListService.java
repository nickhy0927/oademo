package com.iss.common.createPage;

import java.lang.reflect.Field;

import org.springframework.stereotype.Service;

import com.iss.common.createPage.impl.PageStructureImpl;
import com.iss.common.createPage.utils.CreatePage;

@Service
public class ListService extends PageStructureImpl {

	public void init(CreatePage createPage) throws Exception {
		this.getPage(createPage, "List.jsp");
	}
	
	@Override
	public StringBuffer title(CreatePage createPage) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("	" + createPage.getClassName() + "\r\n");
		return buffer;
	}

	@Override
	public StringBuffer css() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("	<style type=\"text/css\"></style>\r\n");
		return buffer;
	}

	@Override
	public StringBuffer javascript(CreatePage createPage) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Field[] fields = clazz.getDeclaredFields();
		String[] strings = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			strings[i] =  "#" + fields[i].getName();
		}
		String url = createPage.getPagePath() + "/" + toLowerCaseFirstOne(createPage.getClassName()) + "List.json";
		buffer.append("		var _init_dataGrid = function() {\r\n");
		buffer.append("			$(\"#dataGridList\").dataGrid({\r\n");
		buffer.append("				title:'" + createPage.getClassName() +"列表',\r\n");
		buffer.append("				url : ctx + '" + url + "',\r\n");
		buffer.append("				checkbox : true,\r\n");
		buffer.append("				pageSize: 7,\r\n");
		buffer.append("				orderField : 'createTime',\r\n"); 
		buffer.append("        		sort: 'desc',\r\n");
		buffer.append("				searchButtonId: '#searchButton',\r\n");
		buffer.append("				queryParamsId: [],\r\n");
		buffer.append("				tableId: '#dataGridList',\r\n");
		buffer.append("				columns:[\r\n");
		buffer.append("					 { field : 'id', className:'text-c'},");
		for (int i = 0; i < fields.length; i++) {
			buffer.append("					 { field : '" + fields[i].getName() + "',className:'text-l',description : '" + fields[i].getName() + "', sort: true},\r\n");
		}
		buffer.append("					{ field : 'operate',className:'text-c',description : '操作', paramFormatter : function(row) {\r\n");
		buffer.append("						return '<a href=\"#\" title=\"修改\" onclick=\"data_edit(\\'' + row.id + '\\')\">'\r\n");
		buffer.append("								 + '<i class=\"Hui-iconfont\">&#xe60c;</i>'\r\n");
		buffer.append("							 + '</a>&nbsp;&nbsp;'\r\n");
		buffer.append("							 + '<a href=\"#\" title=\"删除\" onclick=\"datadel(\\'' + row.id + '\\',true)\">'\r\n");
		buffer.append("								+ '<i class=\"Hui-iconfont\">&#xe609;</i>'\r\n");
		buffer.append("							 + '</a>'\r\n");
		buffer.append("						}");
		buffer.append("					}\r\n");
		buffer.append("				]\r\n");
		buffer.append("			});\r\n");
		buffer.append("		};\r\n");
		buffer.append("		$(function() {\r\n");
		buffer.append("			_init_dataGrid();\r\n");
		buffer.append("		});\r\n");
		return buffer;
	}

	private StringBuffer create(CreatePage createPage) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("		function create(){\r\n");
		String url = createPage.getPagePath() + "/" + toLowerCaseFirstOne(createPage.getClassName()) + "Create.do";
		buffer.append("			var url = ctx + '"+ url + "';\r\n");
		buffer.append("			layer.open({\r\n");
		buffer.append("				type: 2,\r\n");
		buffer.append("				title: '新增" + toLowerCaseFirstOne(createPage.getClassName()) +"信息',\r\n");
		buffer.append("				move: false,\r\n");
		buffer.append("				shadeClose: true,\r\n");
		buffer.append("				shade: 0.6,\r\n");
		buffer.append("				area: ['800px','460px'],\r\n");
		buffer.append("				content: url\r\n");
		buffer.append("			});\r\n");
		buffer.append("		}\r\n");
		return buffer;
	}
	
	private StringBuffer edit(CreatePage createPage) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("		function edit(id){\r\n");
		String url = createPage.getPagePath() + "/" + toLowerCaseFirstOne(createPage.getClassName()) + "Edit.do,";
		buffer.append("			var url = ctx + '"+ url + "?id=' + id;\r\n");
		buffer.append("			layer.open({\r\n");
		buffer.append("				type: 2,\r\n");
		buffer.append("				title: '修改" + toLowerCaseFirstOne(createPage.getClassName()) +"信息',\r\n");
		buffer.append("				move: false,\r\n");
		buffer.append("				shadeClose: true,\r\n");
		buffer.append("				shade: 0.6,\r\n");
		buffer.append("				area: ['800px','460px'],\r\n");
		buffer.append("				content: url\r\n");
		buffer.append("			});\r\n");
		buffer.append("		}\r\n");
		return buffer;
	}

	private StringBuffer delete(CreatePage createPage) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("		function datadel(id, single) {\r\n");
		buffer.append("			$.closeLoading();\r\n");
		String url = createPage.getPagePath() + "/" + toLowerCaseFirstOne(createPage.getClassName()) + "Delete.json";
		buffer.append("			$.datadel({\r\n");
		buffer.append("				url: ctx + '" + url + "',\r\n");
		buffer.append("				method:'POST',\r\n");
		buffer.append("				dataType:'JSON',\r\n");
		buffer.append("				data: {id: id},\r\n");
		buffer.append("				success: function(data) {\r\n");
		buffer.append("					$.closeLoading();\r\n");
		buffer.append("					if(data.responseCode == 200) {\r\n");
		buffer.append("						$.openTip(data.responseMessage, true, function(d) {\r\n");
		buffer.append("							$.closeLoading();\r\n");
		buffer.append("							_init_dataGrid();\r\n");
		buffer.append("						})\r\n");
		buffer.append("					} else {\r\n");
		buffer.append("						$.openTip(data.message, true,function() {\r\n");
		buffer.append("							$.closeLoading();\r\n");
		buffer.append("						})\r\n");
		buffer.append("					}\r\n");
		buffer.append("				},\r\n");
		buffer.append("				error: function(err) {\r\n");
		buffer.append("					$.openTip('删除数据异常，请稍后再试...',true,function() {\r\n");
		buffer.append("						$.closeLoading();\r\n");
		buffer.append("					})\r\n");
		buffer.append("				}\r\n");
		buffer.append("			},single);\r\n");
		buffer.append("		}\r\n");
		return buffer;
	}

	private StringBuffer list(CreatePage createPage) throws ClassNotFoundException {
		StringBuffer buffer = new StringBuffer();
		Class<?> clazz = Class.forName(createPage.getFullPath());
		Field[] fields = clazz.getDeclaredFields();
		String[] strings = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			strings[i] =  "#" + fields[i].getName();
		}
		String url = createPage.getPagePath() + "/" + toLowerCaseFirstOne(createPage.getClassName()) + "List.json";
		buffer.append("		var _init_dataGrid = function() {\r\n");
		buffer.append("			$(\"#dataGridList\").dataGrid({\r\n");
		buffer.append("				title:'" + createPage.getClassName() +"列表',\r\n");
		buffer.append("				url : ctx + '" + url + "',\r\n");
		buffer.append("				checkbox : true,\r\n");
		buffer.append("				pageSize: 7,\r\n");
		buffer.append("				orderField : 'createTime',\r\n"); 
		buffer.append("        		sort: 'desc',\r\n");
		buffer.append("				searchButtonId: '#searchButton',\r\n");
		buffer.append("				queryParamsId: [],\r\n");
		buffer.append("				tableId: '#dataGridList',\r\n");
		buffer.append("				columns:[\r\n");
		buffer.append("					 { field : 'id', className:'text-c'},");
		for (int i = 0; i < fields.length; i++) {
			buffer.append("					 { field : '" + fields[i].getName() + "',className:'text-l',description : '" + fields[i].getName() + "', sort: true},\r\n");
		}
		buffer.append("					{ field : 'operate',className:'text-c',description : '操作', paramFormatter : function(row) {\r\n");
		buffer.append("						return '<a href=\"#\" title=\"修改\" onclick=\"data_edit(\\'' + row.id + '\\')\">'\r\n");
		buffer.append("								 + '<i class=\"Hui-iconfont\">&#xe60c;</i>'\r\n");
		buffer.append("							 + '</a>&nbsp;&nbsp;'\r\n");
		buffer.append("							 + '<a href=\"#\" title=\"删除\" onclick=\"datadel(\\'' + row.id + '\\',true)\">'\r\n");
		buffer.append("								+ '<i class=\"Hui-iconfont\">&#xe609;</i>'\r\n");
		buffer.append("							 + '</a>'\r\n");
		buffer.append("						}");
		buffer.append("					}\r\n");
		buffer.append("				]\r\n");
		buffer.append("			});\r\n");
		buffer.append("		};\r\n");
		buffer.append("		$(function() {\r\n");
		buffer.append("			_init_dataGrid();\r\n");
		buffer.append("		});\r\n");
		return buffer;
	}

	@Override
	public StringBuffer body(CreatePage createPage) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("	<script type=\"text/javascript\">\r\n");
		buffer.append(create(createPage));
		buffer.append(edit(createPage));
		buffer.append(delete(createPage));
		try {
			buffer.append(list(createPage));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		buffer.append("	</script>\r\n");
		return buffer;
	}
}
