package com.lql.chapter6.service;

import com.lql.chapter6.entity.User;

import java.util.Set;

/**
 * Created by LQL on 2016/8/14.
 */
public interface UserService {

    User createUser(User user);

    //�޸�����
    void changePassword(Long userId , String newPassword);

    /**
     * ����û���ɫ��ϵ
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * �Ƴ��û���ɫ��ϵ
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Long userId, Long... roleIds);

    /**
     * �����û��������û�
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * �����û����������ɫ
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * �����û���������Ȩ��
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
}
