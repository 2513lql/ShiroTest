package com.lql.chapter2;


import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;

/**
 * Created by LQL on 2016/8/13.
 */
public class LoginLogoutTest {

    @Test
    public void testHelloWorld(){

        //1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter2/shiro.ini");

        //2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        try {
            //4����¼���������֤
            subject.login(token);
        }catch (AuthenticationException e){
            //5�������֤ʧ��
//           e.printStackTrace();
        }

        //�����û��Ѿ���¼
        Assert.assertEquals(true,subject.isAuthenticated());
        //6���˳�
        subject.logout();
    }


    @Test
    public void testCustomRealm(){

        //1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter2/shiro-realm.ini");

        //2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try {
            //4����¼���������֤
            subject.login(token);
        }catch (AuthenticationException e){
            //5�������֤ʧ��
//           e.printStackTrace();
        }

        //�����û��Ѿ���¼
        Assert.assertEquals(true,subject.isAuthenticated());
        //6���˳�
        subject.logout();
    }

    @Test
    public void testJdbcRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter2/shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang1","123");

        try {
            //4����¼���������֤
            subject.login(token);
        } catch (AuthenticationException e) {
            //5�������֤ʧ��
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //�����û��Ѿ���¼

        //6���˳�
        subject.logout();
    }
}
