package com.lql.chapter5;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Created by LQL on 2016/8/13.
 */
public class EncryptTest {

    @Test
    public void md5Test(){
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str,salt).toString();
        System.out.println(md5);

    }

}
