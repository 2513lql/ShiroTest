package com.lql.chapter6.service.imp;

import com.lql.chapter6.dao.UserDao;
import com.lql.chapter6.dao.imp.UserDaoImp;
import com.lql.chapter6.entity.User;
import com.lql.chapter6.service.UserService;
import com.lql.chapter6.util.PasswordHelper;

import java.util.Set;

/**
 * Created by LQL on 2016/8/14.
 */
public class UserServiceImp implements UserService{

    private UserDao userDao = new UserDaoImp();
    private PasswordHelper passwordHelper = new PasswordHelper();

    public User createUser(User user) {
        passwordHelper.encryptPassword(user);//∂‘√‹¬Îº”√‹
        return userDao.createUser(user);
    }

    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }

    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId,roleIds);
    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {
        userDao.uncorrelationRoles(userId,roleIds);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
