package com.lql.chapter6.dao;

import com.lql.chapter6.entity.Permission;

/**
 * Created by LQL on 2016/8/14.
 */
public interface PermissionDao {
    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
