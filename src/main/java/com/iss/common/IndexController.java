package com.iss.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orm.utils.FileTools;
import com.orm.utils.MessageObject;

@Controller
public class IndexController {

	@RequestMapping(value = "/index.json", method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response) {
		String realPath = request.getServletContext().getRealPath("templates/create.jsp");
		System.out.println(realPath);
		String content = FileTools.readToString(realPath);
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		Map<String, String> map = new HashMap<String, String>();
		map.put("$1", "value1");
		map.put("$2", "value2");
		map.put("$3", "value3");
		System.out.println("结果:" + FileTools.format("$1$2$3", map));
		messageObject.setObject(content);
		messageObject.setSuccessMessage("获取页面成功");
		try {
			messageObject.returnData(response, messageObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
