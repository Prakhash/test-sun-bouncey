package org.wso2.ciphertest;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class BCDecrypter {

    //BC is used as the crypto provider
    public static String decrypt(byte [] ciphertext) throws  Exception {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);

        KeyStore keyStore = null;
        try {
            keyStore = getKeyStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrivateKey privateKey = null;
        try {
            privateKey = (PrivateKey) keyStore.getKey("wso2carbon",
                    "wso2carbon1".toCharArray());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPwithSHA1andMGF1Padding","BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] cipherbyte=cipher.doFinal(ciphertext);
        return new String(cipherbyte);
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
