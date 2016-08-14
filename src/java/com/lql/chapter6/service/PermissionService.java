package com.lql.chapter6.service;

import com.lql.chapter6.entity.Permission;

/**
 * Created by LQL on 2016/8/14.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
