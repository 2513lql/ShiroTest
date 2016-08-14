package com.lql.chapter5.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by LQL on 2016/8/14.
 */
public class MyRealm2 extends AuthorizingRealm{
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = "liu";//用户名及salt1
        String password = "31b60082e9182ce04c42ad218d887828";//加密后的密码
        String salt2 = "3519bf150d195a2e14654ec6a15223b2";//
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username + salt2));//盐是用户名+随机数
        return simpleAuthenticationInfo;
    }
}
