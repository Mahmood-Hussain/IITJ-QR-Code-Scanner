package com.kyadapaan.iitjqrcodescanner;
import android.util.Base64;
import android.util.Log;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

// Custom Classes
class GetSHA256 {
    public byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public  String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}


class RSAEncrypt{

    public void TestEncryptData(String dataToEncrypt) {
        // generate a new public/private key pair to test with (note. you should only do this once and keep them!)
        KeyPair kp = getKeyPair();

        PublicKey publicKey = kp.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        String publicKeyBytesBase64 = new String(Base64.encode(publicKeyBytes, Base64.DEFAULT));

//        Log.i("public key ", publicKeyBytesBase64);

        PrivateKey privateKey = kp.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String privateKeyBytesBase64 = new String(Base64.encode(privateKeyBytes, Base64.DEFAULT));

//        Log.i("private key ", privateKeyBytesBase64);

        // test encryption
        String encrypted = Encrypt(dataToEncrypt, publicKeyBytesBase64);

        // test decryption
        String decrypted = Decrypt(encrypted, privateKeyBytesBase64);
    }

    public static KeyPair getKeyPair() {
        KeyPair kp = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            kp = kpg.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kp;
    }

    public String Encrypt(String clearText, String publicKey) {
        String encryptedBase64 = "";
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey.trim().getBytes(), Base64.DEFAULT));
            Key key = keyFac.generatePublic(keySpec);

            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes("UTF-8"));
            encryptedBase64 = new String(Base64.encode(encryptedBytes, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedBase64.replaceAll("(\\r|\\n)", "");
    }

    public String Decrypt(String encryptedBase64, String privateKey) {

        String decryptedString = "";
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey.trim().getBytes(), Base64.DEFAULT));
            Key key = keyFac.generatePrivate(keySpec);

            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            // encrypt the plain text using the public key
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] encryptedBytes = Base64.decode(encryptedBase64, Base64.DEFAULT);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptedString;
    }
}


/*
public key
 */

/*

MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzLZFdAxlWBazbhdDck5c27OOnOXfCDZcBP+vbtf8IgcZwWMi/LIHF4xinZYkmbx7j90F2zSffPMUSgVIsQoJFRzwplwxYW4ObWhYQitqc10L7cRuwNkH34mMV4c9Huk4hSar5EPbKVUSrKD2fZmVPMDVN2pgyis/v7RDgc10r4A/MXRDxDuVSIULkcJY8gYeJSpR0qfNTBf2F9xLfP65MQlnXfkPI1WFT5lz0xza6dIlqcFRR4RndUoQ7mvVLcjHB0zmlR0GSGKayezyxzLgGgtqduhohKcJqnFo4i+8yVr1ByFVILlJICqYA9Nrs97isURwWHKwqjN7wAxegexoRQIDAQAB

 */

/*
Private Key
 */
/*


MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMtkV0DGVYFrNuF0NyTlzbs46c
5d8INlwE/69u1/wiBxnBYyL8sgcXjGKdliSZvHuP3QXbNJ988xRKBUixCgkVHPCmXDFhbg5taFhC
K2pzXQvtxG7A2QffiYxXhz0e6TiFJqvkQ9spVRKsoPZ9mZU8wNU3amDKKz+/tEOBzXSvgD8xdEPE
O5VIhQuRwljyBh4lKlHSp81MF/YX3Et8/rkxCWdd+Q8jVYVPmXPTHNrp0iWpwVFHhGd1ShDua9Ut
yMcHTOaVHQZIYprJ7PLHMuAaC2p26GiEpwmqcWjiL7zJWvUHIVUguUkgKpgD02uz3uKxRHBYcrCq
M3vADF6B7GhFAgMBAAECggEAHcebOWtPN94PiqGgOmnraLuJcMog5JsxzjlazjtlzpZtWPxgu9Vz
IioWM3oCx4mLiQ1cW3vfgqgC6NS1cym0Ugm0eWL6fQOrXh+ajT+vG+qqgFN90BiSZGKoU5hVHCDN
y9LpGQsFABGD5rCAwxs7QkwECGx1eZuKicsBsgmPIrN44UpzdBs0Qk5DZxqJ1DUtLHJN5ZpHh/VQ
H0fozSB7g6O4s+kakSRWhus0yZ9nYJdj8/A1+vE4cZi7N7KTcBr9xtylvuAhnbpPAikt2kkaejur
8P28TL04DwjF5oTk/9xrb7cmOz1LR8UqZDjHWoOMWpvz95hvNucsRTM7lmQfmQKBgQDzzLsYzPmx
FMKXuTG4VX7Zs3kejL9+R5AVer/YjveHIFZIBA0boOcN6H7yGQQrIilnd331YRinQ3rn2yQka5F/
OmC5GUL+QTD2y4IxqhXPDgUOPwf+SVQY2pDIsKUuFOG9g0biIGm8fM+xzg8lXNsKX4VS37yUnXof
Cx1/G2ggeQKBgQDW9MufO702dMthxXz68sNrQV2uchFH+5mXQGkaN3Q0PmHKxU0b5R10yNTtgH5q
U9G7NrIt5E7A8dKu3EeBbVvgS/mG5L7ZxcxGV3dvUR0SrQgwLTmdulAscz4ejspL/ACrnNY2+gNl
ijdj0Crthp6W+j9zu3Dv8We79EG1waWLLQKBgQCVkFqTW2Qu44qBY8aBzElWqsi6GiR8BtD4rGY3
J3Xo+siTw5B7C5nTqXb/dKNfBsUZyW+HcnqaWkZzKLgic689uwWNaFb1/e5Y6P5FE4/vhy1I0RhJ
QpkJ6kP6KowbGgpmpbt9i97yGCuea8hU5oMl+PWkosdg8L7zfnEpAho+GQKBgEW82arpaLOYJLut
pKSNyhSr+ka0PyMJX4UQooic4Rhp/LQaWq/oHyxaU6pR+7FnTC3eyuPUpDA4xE9yq8gzwJD+VRAM
elaQs3tGuTrmJ/Z9KKDoqz6eyZACO28SiFMqiNUkZuMjLaLtupapXKP+/sigm6FieHt/6FW0IvVi
w+BpAoGBAMztSayveMTEvOalEQfwcue6srOPPWPizRAXMNfR8ehYC7tKYhNt9ZtpVw4kJ2v22cER
X0EUdh0ssf4oXXufcEB+hK1cW1Cem6EjSEy1792Zm07HhyhVHTZ4yti1PiX70oFGYYPBCqA3M6wT
MaCkSBC2PMNlMEWSl4YlGgxRzp6C

 */



