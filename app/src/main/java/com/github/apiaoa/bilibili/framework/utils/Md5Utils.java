package com.github.apiaoa.bilibili.framework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by a176 on 2017/2/28.
 */

public class Md5Utils {

        //静态方法，便于作为工具类
        public static String getMd5(String plainText) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(plainText.getBytes());
                byte b[] = md.digest();

                int i;

                StringBuilder buf = new StringBuilder("");
                for (byte aB : b) {
                    i = aB;
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                //32位加密
                return buf.toString();
                // 16位的加密
                //return buf.toString().substring(8, 24);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }

        }

}
