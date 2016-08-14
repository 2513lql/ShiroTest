package com.lql.chapter6.dao.imp;

import com.lql.chapter6.JdbcTemplateUtils;
import com.lql.chapter6.dao.UserDao;
import com.lql.chapter6.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by LQL on 2016/8/14.
 */
public class UserDaoImp implements UserDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    public User createUser(final User user) {

        System.out.println(user.toString());
        final String sql = "insert into sys_users(username,password,salt,locked) values(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement pst = connection.prepareStatement(sql,new String[]{"id"});
                pst.setString(1,user.getUsername());
                pst.setString(2,user.getPassword());
                pst.setString(3,user.getSalt());
                pst.setBoolean(4,user.getLocked());
                return pst;
            }
        },keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public void updateUser(User user) {
        String sql = "update sys_users set username=?,password=?,salt=?,locked=? where id=?";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked(),user.getId());
    }


    public void deleteUser(Long userId) {
        String sql = "delete from sys_users where id=?";
        jdbcTemplate.update(sql,userId);
    }


    public void correlationRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0){
            return;
        }
        String sql = "insert into sys_users_roles(user_id,role_id) values(?,?)";
        for (Long roleId : roleIds){
            if(!exists(userId,roleId)){
                jdbcTemplate.update(sql,userId,roleId);
            }
        }
    }

    private boolean exists(Long userId, Long roleId) {

        String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";

        return jdbcTemplate.queryForObject(sql,Integer.class,userId,roleId) != 0;
    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {
        String sql = "delete from sys_users_roles where user_id=? and roleId=?";
        for (Long roleId : roleIds){
            if (exists(userId,roleId)){
                jdbcTemplate.update(sql,userId,roleId);
            }
        }
    }

    public User findOne(Long userId) {

        String sql = "select id, username, password, salt, locked from sys_users where id=?";
        List<User> users = jdbcTemplate.queryForList(sql,User.class,userId);
        if (users.size() == 0){
            return null;
        }
        return users.get(0);
    }

    public User findByUsername(String username) {
        String sql = "select id, username, password, salt, locked from sys_users where username=?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class),username);
        if (users.size() == 0){
            return null;
        }
        return users.get(0);
    }

    public Set<String> findRoles(String username) {
        String sql = "select role from sys_users u , sys_roles r , sys_users_roles ur where u.username=? and ur.user_id=u.id and r.id=ur.role_id";

        return new HashSet<String>(jdbcTemplate.query(sql, new BeanPropertyRowMapper(String.class), username));
    }

    public Set<String> findPermissions(String username) {
        String sql = "select permission from sys_users u , sys_roles r ,sys_users_roles ur , sys_permissions p, sys_roles_permissions rp where u.username=? and ur.user_id=u.id and r.id=ur.role_id and rp.role_id=r.id and p.id=rp.permission_id";

        return new HashSet<String>(jdbcTemplate.query(sql, new BeanPropertyRowMapper(String.class), username));
    }

}
