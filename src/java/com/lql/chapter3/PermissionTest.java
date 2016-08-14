package com.lql.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by LQL on 2016/8/13.
 */
public class PermissionTest {

    private void login(String configFile,String username,String password){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);
    }

    @Test
    public void testPermit(){
        login("classpath:chapter3/shiro-permission.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isPermitted("user:create"));
        System.out.println(subject.isPermitted("user:view"));
    }


    @Test
    public void testPermit2(){
        login("classpath:chapter3/shiro-authorizer.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isPermitted("user1:update"));
        System.out.println(subject.isPermitted("user:update"));
    }


    @Test
    public void testJdbcPermit3(){
        login("classpath:chapter3/shiro-authorizer.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isPermitted("user1:update"));
    }
}
