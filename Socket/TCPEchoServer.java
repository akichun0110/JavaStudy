// test3

import java.net.*;
import java.io.*;

public class TCPEchoServer {

    private static final int BUFSIZE = 32;      // 受信バッファサイズ

    public static void main(String[] args) throws IOException {

        if (args.length != 1) // 引数が正しいかどうか確かめる
            throw new IllegalArgumentException("Parameter(s) <Port>");

            int servPort = Integer.parseInt(args[0]);

            // クライアントの接続要求を受け付けるサーバソケットを作成する
            ServerSocket servSock = new ServerSocket(servPort);

            int recvMsgSize;        // 受信したメッセージサイズ
            byte[] byteBuffer = new byte[BUFSIZE];        // 受信バッファ

            for ( ; ; ) {   // 繰り返し実行され，接続を受けて処理を行う
                Socket clntSock = servSock.accept();    //クライアント接続を取得する

                System.out.println("Handling client at" + clntSock.getInetAddress().getHostAddress() + "on port" + clntSock.getPort());

                InputStream in = clntSock.getInputStream();
                OutputStream out = clntSock.getOutputStream();

                // クライアント接続をクローズし，−1が返されるまで繰り返す 
                while ((recvMsgSize = in.read(byteBuffer)) != -1 )
                    out.write(byteBuffer, 0, recvMsgSize);

                clntSock.close();

            }
            /* この部分には到達しない */ 
    }
}