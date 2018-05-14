package com.iss.common.createPage.utils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CreatePageUtils {

	public static final Map<String, CreatePage> createPageMaps = Maps.newHashMap();

	@SuppressWarnings("unchecked")
	public static List<CreatePage> getCreatePage() {
		List<CreatePage> pages = Lists.newArrayList();
		InputStream stream = CreatePageUtils.class.getClassLoader().getResourceAsStream("create/createPage.xml");
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(stream);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for (Element element : elements) {
				CreatePage page = new CreatePage();
				page.setSelectName(element.attributeValue("selectName"));
				page.setClassName(element.element("className").getTextTrim());
				page.setFullPath(element.element("fullPath").getStringValue());
				page.setPackageName(element.element("package").getTextTrim());
				page.setPagePath(element.element("pagePath").getTextTrim());
				pages.add(page);
				createPageMaps.put(element.element("className").getTextTrim(), page);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return pages;
	}
}
