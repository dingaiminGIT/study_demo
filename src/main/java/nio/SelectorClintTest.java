package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SelectorClintTest {

    public static void main(String[] args) {
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open();
            channel.connect(new InetSocketAddress("127.0.0.1", 8000));

            ByteBuffer writeBuf = ByteBuffer.allocate(1024);
            ByteBuffer readBuf = ByteBuffer.allocate(1024);

            writeBuf.put("Hello Server".getBytes());
            writeBuf.flip();

            while (true) {
                writeBuf.rewind();
                channel.write(writeBuf);
                readBuf.clear();
                channel.read(readBuf);
                System.out.println("Client receive:" + new String(readBuf.array()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
