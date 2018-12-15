package com.marsfood.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * DES加解密算法
 * @author huangxingguang
 * @date 2018/09/27
 */
public class EncryptUtils {

    private Key key = null;
    private AlgorithmParameterSpec iv = null;
    private static final Logger LOGGER = LogManager.getLogger(EncryptUtils.class);
    private static final byte[] DES_IV = new byte[]{0x12, 0x34, 0x56, 120, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef};

    private EncryptUtils(String key) {
        try {
            iv = new IvParameterSpec(DES_IV);
            this.key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 加密
     */
    public String encrypt(String data) {
        Cipher enCipher;
        try {
            enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            enCipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] pasByte = enCipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return new BASE64Encoder().encode(pasByte);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 解密
     */
    public String decrypt(String data) {
        Cipher deCipher;
        try {
            deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            deCipher.init(Cipher.DECRYPT_MODE, key, iv);
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
            return new String(pasByte, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static EncryptUtils getInstance(String key) {
        return new EncryptUtils(key);
    }

}
