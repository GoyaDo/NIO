package com.cj.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author cj
 * @date 2019-11-03 - 16:08
 *
 * bio写法
 *
 */
public class QQServer {
    static byte[] bytes = new byte[1024];

    public static void main(String[] args) {
        try {

            //listener
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));

            while (true) {
                System.out.println("wait conn");

                //阻塞-->等待连接
                Socket socket = serverSocket.accept();

                System.out.println("conn success");
                System.out.println("wait data");
                //阻塞-->等待数据   read读了多少字节
                int read = socket.getInputStream().read(bytes);

                System.out.println("data success");
                String content = new String(bytes);

                System.out.println(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
