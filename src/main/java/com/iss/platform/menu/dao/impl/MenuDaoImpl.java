package com.iss.platform.menu.dao.impl;

import org.springframework.stereotype.Repository;

import com.iss.platform.menu.dao.MenuDao;
import com.iss.platform.menu.entity.Menu;
import com.orm.core.dao.impl.CommonDaoImpl;

@Repository
public class MenuDaoImpl extends CommonDaoImpl<Menu, String> implements MenuDao{

}
