package com.lql.chapter6.dao;

import com.lql.chapter6.entity.User;

import java.util.Set;

/**
 * Created by LQL on 2016/8/14.
 */
public interface UserDao {

    User createUser(User user);
    void updateUser(User user);
    void deleteUser(Long userId);

    //����û�-��ɫ��ϵ
    void correlationRoles(Long userId, Long... roleIds);
    // �Ƴ��û�-��ɫ��ϵ
    void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
