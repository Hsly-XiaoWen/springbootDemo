package com.juemuren.socket.nio;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * Created by sivan on 2019/3/5.
 */
public class NIOServer {

    //通道管理器
    private Selector selector;

    private Map<SelectionKey, Map<String, String>> map = new HashMap<>();

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
     *
     * @param port 绑定的端口号
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     *
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws Exception {
        System.out.println("服务端启动成功！");
        // 轮询访问selector
        while (true) {
            //当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            Iterator ite = selectedKeys.iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key
                            .channel();
                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里可以给客户端发送信息哦
//                    channel.write(ByteBuffer.wrap(new String("Hi I'm yqq").getBytes()));
                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);
                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    read(key);//主线程处理读事件
                    key.interestOps(SelectionKey.OP_WRITE);
//                    NIOHandler.read(key);//多线程处理读事件
//                    response(key);
                }
                else if (key.isWritable()) {
                    response(key);//单线程处理些事件
//                    NIOHandler.write(key);
                }

            }

        }
    }

    private void response(SelectionKey key) {
        Map<String, String> headers = map.get(key);

        String method = headers.get("url");
        String str = "江西理工欢迎您" + method;
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

//        System.out.println("返回内容：\n" + returnStr.toString());
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer bodyBuffer = ByteBuffer.wrap(returnStr.toString().getBytes());
        try {
            channel.write(bodyBuffer);// 将消息回送给客户端
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

    /**
     * 处理读取客户端发来的信息的事件
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws Exception {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        channel.read(buffer);
//        buffer.flip();
        byte[] bytes = buffer.array();
//        buffer.get(bytes);
        String msg = new String(bytes).trim();
        this.parse(key, msg);
        System.out.println("服务端收到信息：" + msg);
        System.out.println("当前线程：" + Thread.currentThread());
    }

    private void parse(SelectionKey key, String header) throws Exception {
        if (StringUtils.isBlank(header)) {
            throw new Exception("请求头为空");
        }

        Map<String, String> keyHeader = new HashMap<>();
        String[] lines = header.split("\r\n");
        String firstLine = lines[0];
        String[] headers = firstLine.split(" ");
        keyHeader.put("method", headers[0]);
        keyHeader.put("url", headers[1]);
        keyHeader.put("version", headers[2]);

        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(":");
            keyHeader.put(values[0], values[1]);
        }
        map.put(key, keyHeader);
    }

    /**
     * 启动服务端测试
     *
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        NIOServer server = new NIOServer();
        server.initServer(8000);
        server.listen();
    }
}
