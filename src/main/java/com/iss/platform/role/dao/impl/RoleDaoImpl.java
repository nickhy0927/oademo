package com.iss.platform.role.dao.impl;

import org.springframework.stereotype.Repository;

import com.iss.platform.role.dao.RoleDao;
import com.iss.platform.role.entity.Role;
import com.orm.core.dao.impl.CommonDaoImpl;

@Repository
public class RoleDaoImpl extends CommonDaoImpl<Role, String> implements RoleDao {

}
