package com.iss.common.createPage;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.iss.common.createPage.impl.PageStructureImpl;
import com.iss.common.createPage.utils.CreatePage;
import com.orm.utils.FileTools;

@Service
public class EditService extends PageStructureImpl {

	public void init(CreatePage createPage) throws Exception {
		this.getPage(createPage, "Edit.jsp");
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
		buffer.append("	<script type=\"text/javascript\">\r\n");
		buffer.append("		$(function () {\r\n");
		buffer.append("			$(\"#formId\").validate({\r\n");
		buffer.append("				rules: {\r\n");

		Class<?> clazz = Class.forName(createPage.getFullPath());
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			buffer.append("					" + field.getName() + ": { required: true},\r\n");
		}
		buffer.append("				},\r\n");
		buffer.append("				onkeyup: false,\r\n");
		buffer.append("				focusCleanup: true,\r\n");
		buffer.append("				success: \"valid\",\r\n");
		buffer.append("				submitHandler: function (form) {\r\n");
		buffer.append("					_saveForm();\r\n");
		buffer.append("				}\r\n");
		buffer.append("			});\r\n");
		buffer.append("		});\r\n");
		buffer.append("		var _err_callback = function (XMLHttpRequest, error, errorThrown) {\r\n");
		buffer.append("			$.closeLoading();\r\n");
		buffer.append("			$.openTip('保存失败，请稍候再试...',true, function() {\r\n");
		buffer.append("				$.closeLoading();\r\n");
		buffer.append("				return ;\r\n");
		buffer.append("			});\r\n");
		buffer.append("		};\r\n");
		buffer.append("		var _success_callback = function (response) {\r\n");
		buffer.append("			var data = eval(\"(\" + response + \")\");\r\n");
		buffer.append("			if (data.responseCode == 200) {\r\n");
		buffer.append("				$.openTip(data.responseMessage,true, function() {\r\n");
		buffer.append("					$.closeLoading();\r\n");
		buffer.append("					parent.window.location.href = ctx +'" + createPage.getPagePath() + "/"
				+ toLowerCaseFirstOne(createPage.getClassName()) + "Edit.do';\r\n");
		buffer.append("					var index = parent.layer.getFrameIndex(window.name);\r\n");
		buffer.append("				});\r\n");
		buffer.append("			} else {\r\n");
		buffer.append("				$.closeLoading();\r\n");
		buffer.append("				$.openTip(data.message,true, function() {\r\n");
		buffer.append("					$.closeLoading();\r\n");
		buffer.append("					return ;\r\n");
		buffer.append("				});\r\n");
		buffer.append("			} \r\n");
		buffer.append("		};\r\n");
		buffer.append("		var _saveForm = function () {\r\n");
		buffer.append("			$.openTip('确定保存吗？',false, function(){\r\n");
		buffer.append("				$.closeLoading();\r\n");
		buffer.append("				$.openLoading();\r\n");
		buffer.append("        		jQuery.ajax({\r\n");
		buffer.append("            		type: \"POST\",\r\n");
		buffer.append("            		url: ctx + '" + createPage.getPagePath()
				+ toLowerCaseFirstOne(createPage.getClassName()) + "Edit.json',\r\n");
		buffer.append("            		data: $(\"#formId\").serialize(),\r\n");
		buffer.append("            		error: _err_callback,\r\n");
		buffer.append("           		success: _success_callback\r\n");
		buffer.append("    	 		});\r\n");
		buffer.append("    		});\r\n");
		buffer.append("		}\r\n");
		buffer.append("	</script>\r\n");
		return buffer;
	}

	@Override
	public StringBuffer body(CreatePage createPage) {
		StringBuffer buffer = new StringBuffer();
		try {
			Class<?> clazz = Class.forName(createPage.getFullPath());
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Map<String, String> map = Maps.newConcurrentMap();
				map.put("$fieldName", field.getName());
				map.put("$fieldValueName",
						"${" + toLowerCaseFirstOne(createPage.getClassName()) + "." + field.getName() + "}");
				String format = FileTools.format(edit_div(), map);
				buffer.append(format);
			}
			buffer.append(button());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
