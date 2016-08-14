package com.lql.chapter6.util;

import com.lql.chapter6.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by LQL on 2016/8/14.
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator =
            new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations = 2;


    public void encryptPassword(User user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String encryptPwd = new SimpleHash(algorithmName,user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),hashIterations).toHex();
        user.setPassword(encryptPwd);
    }
}
