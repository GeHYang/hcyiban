package com.hgl.utils.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    /**
     * md5加密
     * @param data
     * @return
     */
    protected static String encryMD5(String data, String MD5) throws NoSuchAlgorithmException {
        byte[] md5Bytes = null;
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        md5Bytes = md5.digest(data.getBytes());
        StringBuffer buffer = new StringBuffer();
        for(byte b : md5Bytes){
            buffer.append(String.format("%02x", b & 0xff));
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String sid = "2020120034";
        String md5 = encryMD5(sid, "MD5");
        System.out.println(md5);
    }

}
