package com.db_project.db_project.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

class RsaEncryptionTest {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Cipher cipher;

    @BeforeEach
    void setUp() throws Exception {
        // RSA 키 쌍 생성 (2048 비트)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        publicKey = keyPair.getPublic();  // 공개키
        privateKey = keyPair.getPrivate(); // 개인키

        // Cipher 객체 생성
        cipher = Cipher.getInstance("RSA");
    }

    @Test
    void testEncryptionAndDecryption() throws Exception {
        String originalText = "Hello RSA";

        // 공개키로 암호화
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(originalText.getBytes());
        String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("Encrypted: " + encryptedBase64);

        // 개인키로 복호화
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
        String decryptedText = new String(decrypted);
        System.out.println("Decrypted: " + decryptedText);

        // 원본과 복호화된 값이 같은지 검증
        assertEquals(originalText, decryptedText);
    }
}
