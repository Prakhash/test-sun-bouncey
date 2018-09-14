package org.wso2.ciphertest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.security.jgss.SunProvider;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;

public class SunEncrypter {

    //Sunprovider is used as the crypto provider
    public static byte [] encrypt(String plaintext) throws Exception {
        Security.insertProviderAt(new SunProvider(), 1);
        KeyStore keyStore = getKeyStore();
        Certificate[] certs = keyStore.getCertificateChain("wso2carbon");
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPwithSHA1andMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, certs[0].getPublicKey());
        return cipher.doFinal(plaintext.getBytes());
    }

    public static KeyStore getKeyStore() throws Exception {
        String file ="wso2carbon.jks";
        KeyStore keyStore = KeyStore
                .getInstance("JKS");
        String password = "wso2carbon1";
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            keyStore.load(in, password.toCharArray());
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return keyStore;
    }

}
