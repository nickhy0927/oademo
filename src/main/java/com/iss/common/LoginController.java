package com.iss.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.orm.utils.MessageObject;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response) {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		messageObject.ok("登录成功");
		messageObject.setResult("index");
		try {
			messageObject.returnData(response, messageObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
