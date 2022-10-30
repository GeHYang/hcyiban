package com.hgl.utils.crypto;

import java.util.Base64;

public class Base64Util {
    private static final String CHARSET = "utf-8";

    /**
     * BASE64加密
     * @param bytes
     * @return
     */
    protected static String encodeBASE64(byte[] bytes){
        return new String(Base64.getEncoder().encode(bytes));
    }

    /**
     * BASE64解密
     * @param bytes
     * @return
     */
    protected static byte[] decryBASE64(byte[] bytes){
        return Base64.getDecoder().decode(bytes);
    }
}
