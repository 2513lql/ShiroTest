package com.lql.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by LQL on 2016/8/13.
 * RolePermissionResolver用于根据角色字符串来解析得到权限集合。
 */
public class MyRolePermissionResolver implements RolePermissionResolver{


    public Collection<Permission> resolvePermissionsInRole(String s) {
        if ("role1".equals(s)){
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));
        }
        return null;
    }
}
