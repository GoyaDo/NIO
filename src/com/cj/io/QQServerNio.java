package com.cj.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cj
 * @date 2019-11-03 - 16:42
 *
 * NIO普通单线程写法
 *
 */
public class QQServerNio {
    static byte[] bytes = new byte[1024];
    static List<SocketChannel> list = new ArrayList<SocketChannel>();
    //可以申请堆外内存
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) {
        try {

            //listener
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);


            while (true) {
                //阻塞-->等待连接
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null){
                    Thread.sleep(1100);
                    System.out.println("no conn");
                    //遍历list
                    for (SocketChannel client:list){
                        int k = client.read(byteBuffer);
                        if (k>0){
                            byteBuffer.flip();
                            System.out.println(byteBuffer.toString());
                        }

                    }
                }else{
                    System.out.println("---------conn---------");
                    socketChannel.configureBlocking(false);
                    list.add(socketChannel);
                    for (SocketChannel client:list){
                        int k = client.read(byteBuffer);
                        if (k>0){
                            byteBuffer.flip();
                            System.out.println(byteBuffer.toString());
                        }

                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
