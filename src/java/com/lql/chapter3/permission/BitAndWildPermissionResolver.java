package com.lql.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by LQL on 2016/8/13.
 */
public class BitAndWildPermissionResolver implements PermissionResolver{



    public Permission resolvePermission(String s) {
        if (s.startsWith("+")){
            return new BitPermission(s);
        }
        return new WildcardPermission(s);
    }
}
