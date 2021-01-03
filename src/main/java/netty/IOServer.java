package netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {

    public static void main(String[] args) throws IOException {

        System.out.println("创建ServerSocket前");
        final ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("创建ServerSocket后");

        // 监听线程
        new Thread(() -> {
            System.out.println("服务端创建监听线程");
            while (true) {
                try {
                    // 阻塞方法获取新连接
                    System.out.println("accept前");
                    // 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    System.out.println("accept后");
                    // 新连接创建一个线程
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {

                        }
                    }).start();
                } catch (IOException e) {

                }
            }
        }).start();

    }
}
