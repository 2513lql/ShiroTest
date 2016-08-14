package com.lql.chapter6.realm;

import com.lql.chapter6.entity.User;
import com.lql.chapter6.service.UserService;
import com.lql.chapter6.service.imp.UserServiceImp;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by LQL on 2016/8/14.
 */
public class UserRealm extends AuthorizingRealm{

    private UserService userService = new UserServiceImp();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username =(String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.findRoles(username));
        simpleAuthorizationInfo.setStringPermissions(userService.findPermissions(username));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username =(String) authenticationToken.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null){
            throw new UnknownAccountException();//�޴��˺��쳣
        }
        if(Boolean.TRUE == user.getLocked()){
            throw new LockedAccountException();//�˺������쳣
        }
        //����AuthenticatingRealmʹ��CredentialsMatcher��������ƥ�䣬
        // ��������˼ҵĲ��ÿ����ڴ��жϻ��Զ���ʵ��
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),getName());
        return simpleAuthenticationInfo;
    }
}
