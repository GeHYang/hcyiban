package com.htgl.utils.crypto;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DESUtil {

    private static final String CHARSET = "utf-8";

    /**
     * 生成key
     *
     * @param password
     * @param DES
     * @return
     */
    private static Key generateKey(String password, final String DES) {
        try {
            DESKeySpec dks = new DESKeySpec(password.getBytes(CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            return keyFactory.generateSecret(dks);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES加密
     * @param password 加密密钥
     * @param data 待加密字符串
     * @param DES 加密类型
     * @param CIPHER_ALGORITHM 填充模式
     * @param IV_PARAMETER 偏移量
     * @return
     */
    protected static String encrypt(final String password, final String data,
                                 final String DES, final String CIPHER_ALGORITHM,
                                 final String IV_PARAMETER) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (password == null || password.length() < 8) {
            return null;
        }
        if (data == null) return null;

        Key secretKey = generateKey(password, DES);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));
        return Base64Util.encodeBASE64(bytes);

    }

    /**
     * DES解密
     * @param password
     * @param data
     * @param DES
     * @param CIPHER_ALGORITHM
     * @param IV_PARAMETER
     * @return
     */
    protected static String decrypt(final String password, final String data,
                                 final String DES, final String CIPHER_ALGORITHM,
                                 final String IV_PARAMETER) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (password == null || password.length() < 8) {
            return null;
        }
        if (data == null) return null;
        Key secretKey = generateKey(password, DES);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] bytes = Base64Util.decryBASE64(data.getBytes(CHARSET));
        return new String(cipher.doFinal(bytes), CHARSET);


    }

}
