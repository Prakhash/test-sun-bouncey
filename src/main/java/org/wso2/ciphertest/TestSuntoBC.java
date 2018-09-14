package org.wso2.ciphertest;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class TestSuntoBC {

    public static void main(String [] args) throws Exception {

        String plaintext = "admin";
        byte[] ciphertext =SunEncrypter.encrypt((plaintext));
        String s= Base64.encode(ciphertext);
        System.out.println(Base64.encode(ciphertext));

        System.out.println(s.length());
        String recoveredPlaintext = BCDecrypter.decrypt(ciphertext);
        System.out.println("recoveredPlaintext   "+recoveredPlaintext);
    }
}
