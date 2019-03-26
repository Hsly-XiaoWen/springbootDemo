package com.juemuren.socket.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sivan on 2019/3/26.
 */
public class NIOHandler {

    //构造线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void read(SelectionKey key) throws Exception {
        executorService.submit(() -> {
            // 服务器可读取消息:得到事件发生的Socket通道
            SocketChannel channel = (SocketChannel) key.channel();
            // 创建读取的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            try {
                channel.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            byte[] bytes = new byte[buffer.limit()];
            byte[] bytes = buffer.array();
            String msg = new String(bytes).trim();
            System.out.println("服务端收到信息：" + msg);
            System.out.println("当前线程：" + Thread.currentThread());
            key.interestOps(SelectionKey.OP_WRITE);
        });
    }

    public static void write(SelectionKey key) {
        String str = "江西理工欢迎您";
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("HTTP/1.1 200 ok\r\n");//增加响应消息行
        returnStr.append("Content-Type:text/html;charset=utf-8\r\n");//增加响应消息头
        returnStr.append("\r\n");//空行
        returnStr.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>返回的网页</title>\n" +
                "    <style>\n" +
                "        *{\n" +
                "            background-color: cadetblue;\n" +
                "        }\n" +
                "        #requestInfo{\n" +
                "            font-size: 35px;\n" +
                "            font-family: \"Buxton Sketch\",serif;\n" +
                "            font-weight: bold;\n" +
                "            color: #001bff;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Hello!</h1><br>\n" +
                "    <h3>I'm come from localhost!</h3>\n" +
                "    <h3>And this is you request package information：</h3><br>\n" +
                "    <p id=\"requestInfo\">\n" + str +     //返回请求头信息！
                "        \n" +
                "    </p>\n" +
                "</body>\n" +
                "</html>");//响应消息体

        System.out.println("返回内容：\n" + returnStr.toString());
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer bodyBuffer = ByteBuffer.wrap(returnStr.toString().getBytes());
        try {
            channel.write(bodyBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
