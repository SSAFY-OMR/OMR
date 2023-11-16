package com.ssafy.omr.modules.auth.util;

import com.ssafy.omr.modules.global.exception.ExternalLibraryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class Encryptor {

    public static String alg = "AES/CBC/PKCS5Padding";
    private final String iv;

    public Encryptor(@Value("security.encrypt.key") String key) {
        this.iv = key.substring(0, 16);
    }

    public String encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new ExternalLibraryException(alg);
        }
    }

    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new ExternalLibraryException(alg);
        }
    }
}
