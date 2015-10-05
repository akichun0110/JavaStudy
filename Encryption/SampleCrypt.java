import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.spec.IvParameterSpec;

public class SampleCrypt {
    public static void main(String[] args) {
        String str = null;
        byte[] bstr = null;
        byte[] encrypted = null;// 暗号文字列（byte列）
        String authData = "smzl in kut";
        int key_bits = 256;
        Key key = null; // 暗号化鍵

        Key key2 = null;
        byte[] decrypted = null;

        // キーボード入力
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        try {
            // create secret key
            key = crypt.makeKey(key_bits, authData);
            key2 = crypt.makeKey(key_bits, authData);
            while (true) {
                PrintStr("Input (end is \\q) > ");
                str = keyboard.readLine();
                if (str.equals("\\q")) { break; }
                bstr = str.getBytes();
                PrintStr("\n");

                // encryption
                encrypted = crypt.encode(bstr, key, authData);

                // Output encrypted string
                PrintStr("Encrypted str : \n");
                PrintBin(encrypted);
                PrintStr("\n");

                // Decode
                decrypted = crypt.decode(encrypted, key2, authData);

                PrintStr("Decrypted str : \n");
                PrintStr(new String(decrypted));
                PrintStr("\n");
            }

        } catch (Exception e) { System.out.println(e); }
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
        System.out.print(str);
    }

}