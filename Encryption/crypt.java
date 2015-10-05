import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class crypt {
       /**
     * 秘密鍵をバイト列から生成する
     * @param key_bits 鍵の長さ（ビット単位）
     */
    public static Key makeKey(int key_bits, String authData) {

        byte[] key = new byte[key_bits / 8];
        try {
            // バイト列
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(authData.getBytes("UTF-8"));
            key = md.digest();
            //System.out.println("SHA256 is OK : "+ key.length*8);
            System.out.println("Secret Key is : ");
            PrintBin(key);
            // バイト列の内容（秘密鍵の値）はプログラマーが決める
            /*
            for (int i = 0; i < key.length; i++) {
                key[i] = (byte) (i + 1);
            }
            */
        } catch (Exception e) { System.out.println("makeKey Err : "+e); }
        System.out.println("Make Key is OK\n");
        return new SecretKeySpec(key, "AES");
    }

    /**
     * 暗号化
     */
    public static byte[] encode(byte[] src, Key skey, String authData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("1234567812345678".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);
            //System.out.println("Enc src Length :  "+src.length);
            System.out.println("Encrypted!");
            return cipher.doFinal(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 復号化
     */
    public static byte[] decode(byte[] src, Key skey, String authData) {
        try {
            IvParameterSpec iv = new IvParameterSpec("1234567812345678".getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(skey.getEncoded(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, skey, iv);
            //System.out.println("Dec src Length :  "+src.length);
            return cipher.doFinal(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


                   // 表示用メソッド
    public static void PrintBin(byte[] b){
        for(int i=0; i<b.length; i++){
            String h = Integer.toHexString(b[i]&0xFF);
            if((i+1)%8 == 0) {
                System.out.println(h+" ");
            }else{
                System.out.print(h+" ");    
           }
        }
        System.out.print("\n");
    }

    public static void PrintStr(String str) {
        System.out.println(str);
    }
}