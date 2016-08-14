package com.lql.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by LQL on 2016/8/13.
 */
public class MyRealm2 implements Realm {

    //����һ��ΨһRealm����
    public String getName() {
        return "myrealm2";
    }

    //�жϴ�Realm�Ƿ�֧�ִ�Token
    public boolean supports(AuthenticationToken authenticationToken) {
        //��֧��UsernamePasswordToken���͵�Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    //����Token��ȡ��֤��Ϣ
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();//�õ��û���
        String password = new String((char[])authenticationToken.getCredentials());//�õ�����
        if (!"wang".equals(username)){
            throw new UnknownAccountException();//�û�������ȷ
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();//���벻��ȷ
        }
        //��������֤��֤�ɹ�������һ��AuthenticationInfoʵ�֣�
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
