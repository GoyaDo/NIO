package com.cj.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author cj
 * @date 2019-11-03 - 16:12
 *
 * 客户端
 *
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String txt = scanner.next();
                socket.getOutputStream().write(txt.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
