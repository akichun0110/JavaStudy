//test2

import java.net.*;
import java.io.*;

public class TCPEchoClient {

    public static void main(String[] args) throws IOException {

        if ((args.length < 2 ) || (args.length > 3)) // 引数が正しいかどうかを確かめる
        throw new IllegalArgumentException("Parameter(s) : <Server> <Word> [<Port>]");

        String server = args[0]; // サーバ名またはIPアドレス
        
        // デフォルトの文字コードエンコード方式を使って入力Stringをバイト変換する
        byte[] byteBuffer = args[1].getBytes();
        int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
        
        // サーバの指定されたポートに接続するソケットを作成する
        Socket socket = new Socket(server, servPort);
        System.out.println("Connected to server ...sending echo string");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(byteBuffer); //エンコードされた文字列をサーバに送信する


            // サーバから同じ文字列を受信する
            int totalBytesRcvd = 0; //これまでに受信したバイト数
            int bytesRcvd;              // 前回読み込みで受信したバイト数
            while (totalBytesRcvd < byteBuffer.length) {
                if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1) 
                    throw new SocketException("Connetion closed prematurely");
                totalBytesRcvd += bytesRcvd;
            }

            System.out.println("Received : " + new String(byteBuffer));

            socket.close();     // ソケットとストリームをクローズする
    }
}