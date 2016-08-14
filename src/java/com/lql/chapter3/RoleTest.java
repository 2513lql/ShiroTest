package com.lql.chapter3;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by LQL on 2016/8/13.
 */
public class RoleTest{

    @Test
    public void testHasRole(){
        login("classpath:chapter3/shiro-role.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.hasRole("role1"));
//        System.out.println(subject.hasRole("role3"));

        System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2")));
        subject.checkRoles("role1","role3");
    }


    private void login(String configFile,String username,String password){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);
    }

}
