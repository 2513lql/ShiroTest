package com.lql.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by LQL on 2016/8/13.
 */
public class MyRealm2 implements Realm {

    //返回一个唯一Realm名字
    public String getName() {
        return "myrealm2";
    }

    //判断此Realm是否支持此Token
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    //根据Token获取认证信息
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();//得到用户名
        String password = new String((char[])authenticationToken.getCredentials());//得到密码
        if (!"wang".equals(username)){
            throw new UnknownAccountException();//用户名不正确
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();//密码不正确
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
