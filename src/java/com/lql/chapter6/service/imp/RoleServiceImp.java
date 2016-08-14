package com.lql.chapter6.service.imp;

import com.lql.chapter6.dao.RoleDao;
import com.lql.chapter6.dao.imp.RoleDaoImp;
import com.lql.chapter6.entity.Role;
import com.lql.chapter6.service.RoleService;
import com.lql.chapter6.util.PasswordHelper;

/**
 * Created by LQL on 2016/8/14.
 */
public class RoleServiceImp implements RoleService{

    private RoleDao roleDao = new RoleDaoImp();

    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId,permissionIds);
    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId,permissionIds);
    }
}
