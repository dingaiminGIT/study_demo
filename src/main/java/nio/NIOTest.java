package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

    public static void main(String[] args) {
        writeFile();
        readFile();
    }

    public static void writeFile() {
        RandomAccessFile raf = null;
        FileChannel inChannel = null;
        try {
            raf = new RandomAccessFile("input.txt", "rw");
            // 获取 FileChannel
            inChannel = raf.getChannel();
            // 创建一个写数据的 buffer
            ByteBuffer writeBuf = ByteBuffer.allocate(1024);
            // 写入数据
            writeBuf.put("FileChannel test NIO Channel Buffer Selector".getBytes());
            // 把 Buffer 变为读模式
            writeBuf.flip();
            // 从 Buffer 中读取数据并写入到 FileChannel 中
            inChannel.write(writeBuf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readFile() {
        RandomAccessFile raf = null;
        FileChannel inChannel = null;
        try {
            raf = new RandomAccessFile("input.txt", "rw");
            // 获取 FileChannel
            inChannel = raf.getChannel();
            // 创建用来读取数据的 Buffer
            ByteBuffer readBuf = ByteBuffer.allocate(1024);
            // 从 Channel 中读取数据到 Buffer中
            int read = inChannel.read(readBuf);
            while (read != -1) {
                System.out.println("read " + read);
                // 把 Buffer 切换为读模式
                readBuf.flip();
                // 如果还有未读数据
                while (readBuf.hasRemaining()) {
                    System.out.print((char) readBuf.get());
                }
                // 清空缓冲区
                readBuf.clear();
                read = inChannel.read(readBuf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
