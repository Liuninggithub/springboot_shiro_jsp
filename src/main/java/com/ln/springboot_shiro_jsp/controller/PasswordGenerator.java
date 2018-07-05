package com.ln.springboot_shiro_jsp.controller;

import com.interlink.mng.domain.UserInfo;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordGenerator {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();


    private String algorithmName = "MD5";

    private int hashIterations = 2;

    public void encryptPassword(UserInfo user) {

        String salt = randomNumberGenerator.nextBytes().toHex();

        System.out.println("密码盐："+salt);
        user.setSalt(salt);

        String newPassword = new SimpleHash(
                algorithmName,           //加密算法
                user.getPassword(),      //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),  //salt盐   username + salt
                hashIterations   //迭代次数
        ).toHex();
        System.out.println("加密后的密码："+newPassword);
        user.setPassword(newPassword);
    }

}
