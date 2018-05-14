package com.iss.platform.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.platform.user.entity.User;
import com.orm.utils.PageSupport;

@Controller
@RequestMapping(value = "/platform")
public class UserController {

	@RequestMapping(value = "/platform/create.do", method = RequestMethod.GET)
	public String create() {
		return "platform/user/userCreate";
	}

	@RequestMapping(value = "/platform/save.json", method = RequestMethod.POST)
	public void save(HttpServletRequest request, User user) {

	}

	@RequestMapping(value = "/platform/edit.do", method = RequestMethod.GET)
	public String edit() {

		return "platform/user/userEdit";
	}

	@RequestMapping(value = "/platform/update.json", method = RequestMethod.POST)
	public void update(HttpServletRequest request, User user) {

	}

	@RequestMapping(value = "/platform/list.do", method = RequestMethod.GET)
	public String list() {

		return "platform/user/userList";
	}

	@RequestMapping(value = "/platform/list.json", method = RequestMethod.POST)
	public void list(HttpServletRequest request, PageSupport support) {

	}
}
