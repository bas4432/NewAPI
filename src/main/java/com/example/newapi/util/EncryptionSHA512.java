package com.example.newapi.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import static java.util.Objects.requireNonNull;

public class EncryptionSHA512 {
    private final static String ENCRYPTION_KEY = "2fd81f28b4c3435e408cf145825ef7c2";
    private final static String DIGEST_TYPE = "MD5";
    private static final String HMAC_SHA512 = "HmacSHA512";
    private static final String HMAC_SHA512_INFO = "authentication";
    private static final int HMAC_SHA512_LENGTH = 64;

    public static byte[] ivBytes = new byte[16];

    public static String aes_encrypt(String str, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] textBytes = str.getBytes(StandardCharsets.UTF_8);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

        Base64.Encoder encoder = Base64.getEncoder();
        String encode = new String(encoder.encode(cipher.doFinal(textBytes)));
        return encode;
        //return Base64.encodeBase64String(cipher.doFinal(textBytes));
    }

    public static String aes_decrypt(String str, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        //byte[] textBytes = Base64.decodeBase64(str);

        // Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] textBytes = decoder.decode(str.getBytes ());

        //byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return new String(cipher.doFinal(textBytes), StandardCharsets.UTF_8);
    }

    public static String oneWayPass(String password) {
        String digestValue = getMessageDigest(DIGEST_TYPE, password);

        SecretKey encValue = extract(digestValue.getBytes());

        byte[] hash = expand(encValue, HMAC_SHA512_INFO.getBytes(), HMAC_SHA512_LENGTH);

        return DatatypeConverter.printHexBinary(hash).toLowerCase();
    }

    public static SecretKey extract(byte[] ikm) {
        requireNonNull(ikm, "ikm must not be null");

        Mac mac = initMac(new SecretKeySpec(ENCRYPTION_KEY.getBytes(), HMAC_SHA512));

        byte[] keyBytes = mac.doFinal(ikm);

        return new SecretKeySpec(keyBytes, HMAC_SHA512);
    }

    public static byte[] expand(SecretKey key, byte[] info, int outputLength) {
        requireNonNull(key, "key must not be null");

        if(outputLength < 1) {
            throw new IllegalArgumentException("outputLength must be positive");
        }

        int hashLen = HMAC_SHA512_LENGTH;

        if(outputLength > 255 * hashLen) {
            throw new IllegalArgumentException("outputLength must be less than or equal to 255*HashLen");
        }

        if(info == null) {
            info = new byte[0];
        }

        int n = (outputLength % hashLen == 0) ? outputLength / hashLen : (outputLength / hashLen) + 1;

        byte[] hashRound = new byte[0];

        ByteBuffer generatedBytes = ByteBuffer.allocate(Math.multiplyExact(n, hashLen));

        Mac mac = initMac(key);

        for(int roundNum = 1; roundNum <= n; roundNum++) {
            mac.reset();

            ByteBuffer t = ByteBuffer.allocate(hashRound.length + info.length + 1);
            t.put(hashRound);
            t.put(info);
            t.put((byte)roundNum);

            hashRound = mac.doFinal(t.array());
            generatedBytes.put(hashRound);
        }

        byte[] result = new byte[outputLength];
        generatedBytes.rewind();
        generatedBytes.get(result, 0, outputLength);

        return result;
    }

    private static Mac initMac(SecretKey key) {
        Mac mac;

        try {
            mac = Mac.getInstance(HMAC_SHA512);
            mac.init(key);

            return mac;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static String getMessageDigest(String type, String value) {
        String result = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type);
            messageDigest.reset();
            messageDigest.update(value.getBytes());

            byte byteData[] = messageDigest.digest();

            StringBuffer sb = new StringBuffer();

            for(int i = 0 ; i < byteData.length ; i++) {
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }

            result = sb.toString();
        } catch (Exception e) {
            result = "";
        }

        return result;
    }
}
