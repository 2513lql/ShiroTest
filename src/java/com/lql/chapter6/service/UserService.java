package com.lql.chapter6.service;

import com.lql.chapter6.entity.User;

import java.util.Set;

/**
 * Created by LQL on 2016/8/14.
 */
public interface UserService {

    User createUser(User user);

    //修改密码
    void changePassword(Long userId , String newPassword);

    /**
     * 添加用户角色关系
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * 移除用户角色关系
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
}
