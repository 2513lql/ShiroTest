package com.lql.chapter6.entity;

import java.io.Serializable;

/**
 * Created by LQL on 2016/8/14.
 */
public class RolePermission implements Serializable{

    private Long roleId;
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermission that = (RolePermission) o;

        if (!roleId.equals(that.roleId)) return false;
        return permissionId.equals(that.permissionId);

    }

    @Override
    public int hashCode() {
        int result = roleId.hashCode();
        result = 31 * result + permissionId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
