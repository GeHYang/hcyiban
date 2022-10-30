package com.hgl.utils.crypto;

import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 加密解密工具类
 */
public class CryptoUtils {

    //加密名称
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";
    public static final String HmacMD5 = "HmacMD5";
    public static final String HmacSHA1 = "HmacSHA1";
    public static final String DES = "DES";
    public static final String AES = "AES";

    private final static String IV_PARAMETER = "12345678";//偏移量
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";//填充方式

    /**
     * BASE64加密
     * @param key
     * @return
     */
    public static String encryptBASE64(String key){
        return Base64Util.encodeBASE64(key.getBytes());
    }

    /**
     * BASE64解密
     * @param key
     * @return
     */
    public static String decryptBASE64(String key){
        return new String(Base64Util.decryBASE64(key.getBytes()));
    }

    /**
     * md5加密
     * @param data
     * @return
     */
    public static String encryptMD5(String data) throws NoSuchAlgorithmException {
        return MD5Util.encryMD5(data, MD5);
    }

    /**
     * DES加密
     * @param str 加密字符串
     * @param password 加密秘密
     * @return
     */
    public static String encryptDES(String str, String password) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return DESUtil.encrypt(password,str,DES,CIPHER_ALGORITHM,IV_PARAMETER);
    }

    public static String decryptDES(String str, String password) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return DESUtil.decrypt(password,str,DES,CIPHER_ALGORITHM,IV_PARAMETER);
    }

    public static String encryptDES(String str, String password, String iv) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return DESUtil.encrypt(password,str,DES,CIPHER_ALGORITHM,iv);
    }

    public static String decryptDES(String str, String password, String iv) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return DESUtil.decrypt(password,str,DES,CIPHER_ALGORITHM,iv);
    }

    /**
     * AES加密解密
     * @param str
     * @param password
     * @return
     */
    public static String encryptAES(String str, String password) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        return AESUtil.encrypt(str, password,IV_PARAMETER);
    }

    public static String decryptAES(String str, String password) throws DecoderException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return AESUtil.decrypt(str, password,IV_PARAMETER);
    }

    public static String encryptAES(String str, String password, String iv) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        return AESUtil.encrypt(str,password,iv);
    }

    public static String decryptAES(String str, String password, String iv) throws DecoderException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return AESUtil.decrypt(str, password,iv);
    }

    /**
     * 生成密钥对
     * 0公钥1私钥
     * @return
     */
    public static java.util.Map<Integer, String> createRSAKey(){
        return RSAUtil.getKeyMap();
    }

    /**
     * RSA加密
     * @param str   需要加密的字符串
     * @param publicKey RSA公钥
     * @return
     */
    public static String encryptRSA(String str, String publicKey) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return RSAUtil.encrypt(str, publicKey);
    }

    /***
     * RSA解密
     * @param str   需要解密的字符串
     * @param privateKey    私钥
     * @return
     */
    public static String decryptRSA(String str, String privateKey){
        return RSAUtil.decrypt(str, privateKey);
    }
}
