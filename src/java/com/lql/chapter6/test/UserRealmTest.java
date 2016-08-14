package com.lql.chapter6.test;

import com.lql.chapter6.dao.UserDao;
import com.lql.chapter6.dao.imp.UserDaoImp;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by LQL on 2016/8/14.
 */
public class UserRealmTest extends BaseTest{

    @Test
    public void testLoginSuccess(){
        login("classpath:chapter6/shiro.ini",u1.getUsername(),password);
    }

    @Test
    public void testLoginFailWithUnknownUsername() {
        login("classpath:chapter6/shiro.ini", u1.getUsername() + "1", password);
    }

    @Test
    public void testLoginFailWithErrorPassowrd() {
        login("classpath:chapter6/shiro.ini", u1.getUsername(), password + "1");
    }

    @Test
    public void testLoginFailWithLocked() {
        login("classpath:chapter6/shiro.ini", u4.getUsername(), password + "1");
    }

    @Test
    public void testLoginFailWithLimitRetryCount() {
        for(int i = 1; i <= 5; i++) {
            try {
                login("classpath:chapter6/shiro.ini", u3.getUsername(), password + "1");
            } catch (Exception e) {/*ignore*/}
        }
        login("classpath:chapter6/shiro.ini", u3.getUsername(), password + "1");

        //需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
    }

    @Test
    public void testHasRole() {
        login("classpath:chapter6/shiro.ini", u1.getUsername(), password );
        System.out.println(subject().hasRole("admin"));
    }

    @Test
    public void testDao(){
        UserDao userDao = new UserDaoImp();
        Set<String> set = userDao.findRoles("zhang");
        System.out.println(set.size());
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            System.out.println("----------" + s + "********");
        }
    }
}
