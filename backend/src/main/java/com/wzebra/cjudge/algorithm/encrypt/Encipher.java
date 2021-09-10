package com.wzebra.cjudge.algorithm.encrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import javax.servlet.http.Cookie;

public class Encipher {
    static StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    static {
        encryptor.setPassword("sd3jk44n5v5k5jd");
    }

    public static String encrypt(EncryptInfo encryptInfo) {
        return encryptor.encrypt(encryptInfo.getUserId() + "-" + encryptInfo.getTime());
    }

    public static EncryptInfo decrypt(String str) {
        String de = encryptor.decrypt(str);
        String[] data = de.split("-");
        if (data.length != 2 || !data[0].matches("[0-9]+") || !data[1].matches("[0-9]+")) return new EncryptInfo(-1,0);
        return new EncryptInfo(Integer.parseInt(data[0]), Long.parseLong(data[1]));
    }

    public static EncryptInfo getFromCookie(Cookie[] cookies) {
        if (cookies == null) return new EncryptInfo(-1,0);
        for (Cookie c : cookies) {
            if (c.getName().equals("token")) {
                return decrypt(c.getValue());
            }
        }
        return new EncryptInfo(-1,0);
    }

}
