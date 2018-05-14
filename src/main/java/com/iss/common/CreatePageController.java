package com.iss.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.common.createPage.CreateService;
import com.iss.common.createPage.EditService;
import com.iss.common.createPage.ListService;
import com.iss.common.createPage.utils.CreatePage;
import com.iss.common.createPage.utils.CreatePageUtils;
import com.iss.common.createPage.xml.XmlTemplate;
import com.orm.utils.MessageObject;

@Controller
public class CreatePageController {

	@Autowired
	private CreateService createService;

	@Autowired
	private EditService editService;
	
	@Autowired
	private ListService listService;

	@Autowired
	private XmlTemplate xmlTemplate;

	@RequestMapping(value = "createPage.do", method = RequestMethod.GET)
	public String createPage(Model model) {
		List<CreatePage> list = CreatePageUtils.getCreatePage();
		model.addAttribute("list", list);
		return "templates/createPage";
	}

	@RequestMapping(value = "create/page.json", method = RequestMethod.POST)
	public void createPage(HttpServletRequest request, HttpServletResponse response) {
		String className = request.getParameter("className");
		CreatePage createPage = CreatePageUtils.createPageMaps.get(className);
		MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
		messageObject.setSuccessMessage("获取信息成功");
		try {
			createService.init(createPage);
			editService.init(createPage);
			listService.init(createPage);
			xmlTemplate.getXml(createPage);
			messageObject.returnData(response, messageObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
