package com.iss.common.createPage.intf;

import com.iss.common.createPage.utils.CreatePage;

public interface PageStructure {
	
	StringBuffer title(CreatePage createPage);

	StringBuffer css();

	StringBuffer javascript(CreatePage createPage) throws Exception;
	
	StringBuffer body(CreatePage createPage);

}
