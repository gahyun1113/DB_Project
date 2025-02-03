package com.db_project.db_project.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

class AesEncryptionTest {
    private SecretKey secretKey;
    private Cipher cipher;

    @BeforeEach
    void setUp() throws Exception {
        // AES 키 생성
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        secretKey = keyGen.generateKey();

        // Cipher 객체 생성
        cipher = Cipher.getInstance("AES");
    }

    @Test
    void testEncryptionAndDecryption() throws Exception {
        String originalText = "Hello World";

        // 암호화
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(originalText.getBytes());
        String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted);

        System.out.println("원본 텍스트 : "+originalText);
        System.out.println("암호화 텍스트 : "+encryptedBase64);

        // 복호화
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
        String decryptedText = new String(decrypted);

        System.out.println("복호화 텍스트 : "+decryptedText);

        // 원본과 복호화된 값이 같은지 검증
        assertEquals(originalText, decryptedText);
    }
}

