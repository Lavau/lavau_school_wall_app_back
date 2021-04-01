package top.leeti.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author Leet
 */
public class PasswordUtil {

    private static final String HASH_ALGORITHM_NAME = "MD5";
    private static final int HASH_ITERATIONS = 1024;

    /**
     * 加密登录密码
     * （采用 MD5 哈希加密，理论上，不能反向解密）
     * @param username 用户名
     * @param password 密码
     * @return 返回加密后的密码
     */
    public static String encrypt(String username, String password){
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        SimpleHash hash = new SimpleHash(HASH_ALGORITHM_NAME, password, credentialsSalt, HASH_ITERATIONS);
        return hash.toString();
    }

    /**
     * 加密登录密码
     * （采用 MD5 哈希加密，理论上，不能反向解密）
     * @param password 密码
     * @return 返回加密后的密码
     */
    public static String encrypt(String password){
        final String SALT = "leet niu bi!!";
        ByteSource credentialsSalt = ByteSource.Util.bytes(SALT);
        SimpleHash hash = new SimpleHash(HASH_ALGORITHM_NAME, password, credentialsSalt, HASH_ITERATIONS);
        return hash.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("leet niu bi!!", "12345678"));
    }
}
