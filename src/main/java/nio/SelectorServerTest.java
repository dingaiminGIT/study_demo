package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServerTest {

    public static void main(String[] args) {
        Selector selector = null;
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
            // 设置非阻塞模式
            ssc.configureBlocking(false);

            selector = Selector.open();
            // 注册 channel ，同时指定感兴趣的事件是 Accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuf = ByteBuffer.allocate(1024);
            ByteBuffer writeBuf = ByteBuffer.allocate(1024);
            writeBuf.put("Hello Client".getBytes());
            writeBuf.flip();

            while (true) {
                // 阻塞等待
                int readyNum = selector.select();
                if (readyNum == 0) {
                    continue;
                }
                // 获取就绪的 Keys
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                // 遍历就绪的 Channel
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        // 创建新的连接，并把新的连接注册到 selector 上，且对读操作感兴趣
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuf.clear();
                        socketChannel.read(readBuf);
                        readBuf.flip();
                        System.out.println("Server receive:" +new String(readBuf.array()));
                        // 读完数据后，只对写感兴趣，因为要给 client 发送数据
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        writeBuf.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuf);
                        // 发送完只对读事件感兴趣
                        key.interestOps(SelectionKey.OP_READ);
                    }

                    // 处理完事件后，要从就绪的 Keys 中删除
                    it.remove();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
