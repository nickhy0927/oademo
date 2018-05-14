package com.iss.platform.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.iss.platform.user.dao.UserDao;
import com.iss.platform.user.entity.User;
import com.orm.core.dao.impl.CommonDaoImpl;

@Repository
public class UserDaoImpl extends CommonDaoImpl<User, String> implements UserDao {


}
