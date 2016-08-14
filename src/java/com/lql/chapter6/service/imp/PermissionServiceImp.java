package com.lql.chapter6.service.imp;

import com.lql.chapter6.dao.PermissionDao;
import com.lql.chapter6.dao.imp.PermissionDaoImp;
import com.lql.chapter6.entity.Permission;
import com.lql.chapter6.service.PermissionService;

/**
 * Created by LQL on 2016/8/14.
 */
public class PermissionServiceImp implements PermissionService{

    private PermissionDao permissionDao = new PermissionDaoImp();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
