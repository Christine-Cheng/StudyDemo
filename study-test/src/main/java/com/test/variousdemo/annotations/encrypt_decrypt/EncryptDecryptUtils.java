package com.test.variousdemo.annotations.encrypt_decrypt;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-15 15:56:18
 **/
@Component
public class EncryptDecryptUtils {
    public static void main(String[] args) throws Exception {
        rsaEncryptionExample();
    }
    
    public static void rsaEncryptionExample() throws Exception {
        // 生成RSA密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("打印密钥对");
        System.out.println("publicKey" + publicKey);
        System.out.println("privateKey" + privateKey);
        System.out.println("结束");
        
        //publicKeySun RSA public key, 512 bits
        //   params: null
        //   modulus: 9153760695147077850685442125448355050230707596003510211122674600796299393007363906338858999759699202079027678908981419480144990129657408092909935014808503
        //   public exponent: 65537
        
        
        //privateKeySunRsaSign RSA private CRT key, 512 bits
        //   params: null
        //   modulus: 9153760695147077850685442125448355050230707596003510211122674600796299393007363906338858999759699202079027678908981419480144990129657408092909935014808503
        //   private exponent: 52773180279583607955566823062777313830032434909898117726411806358762253190175673796022870729184848792057777845730439548894104664411663450662277947875081
        
        
        // 生成要加密的数据
        byte[] data = "Hello, RSA!".getBytes();
        
        // 加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data);
        
        System.out.println("Encrypted: " + new String(encryptedBytes));
        
        // 解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        
        System.out.println("Decrypted: " + new String(decryptedBytes));
    }
}
