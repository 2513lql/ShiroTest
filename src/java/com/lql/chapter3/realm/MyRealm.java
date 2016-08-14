package com.lql.chapter3.realm;

import com.lql.chapter3.permission.BitPermission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by LQL on 2016/8/13.
 */
public class MyRealm extends AuthorizingRealm{


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();//�õ��û���
        String password = new String((char[])authenticationToken.getCredentials());//�õ�����
        if (!"zhang".equals(username)){
            throw new UnknownAccountException();//�û�������ȷ
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();//���벻��ȷ
        }
        //��������֤��֤�ɹ�������һ��AuthenticationInfoʵ�֣�
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
