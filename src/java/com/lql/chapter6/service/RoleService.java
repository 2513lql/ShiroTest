package com.lql.chapter6.service;

import com.lql.chapter6.entity.Role;

/**
 * Created by LQL on 2016/8/14.
 */
public interface RoleService {

    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /**
     * ��ӽ�ɫ-Ȩ��֮���ϵ
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * �Ƴ���ɫ-Ȩ��֮���ϵ
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
