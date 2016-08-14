package com.lql.chapter6.dao;

import com.lql.chapter6.entity.Role;

/**
 * Created by LQL on 2016/8/14.
 */
public interface RoleDao {

    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
