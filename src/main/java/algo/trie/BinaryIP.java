package algo.trie;

import java.net.InetAddress;

public class BinaryIP {

    private static int[] temp = new int[]{0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x1};

    private byte[] bytes;
    private String ip;

    public BinaryIP(String ip) throws Exception {
        this.ip = ip;
        InetAddress address = InetAddress.getByName(ip);
        System.out.println("ip:" + ip);
        System.out.println("address:" + address);
        this.bytes = address.getAddress();
        if (bytes.length != 16) {
            throw new IllegalArgumentException("不正确的 IP 格式");
        }
    }

    public byte valueAt(int pos) {
        if (pos < 0 || pos >= 128) {
            throw new IllegalArgumentException("pos 的取值范围是 [0, 128)");
        }
        int num = pos / 8;
        int p = pos % 8;
        return (byte) ((bytes[num] & temp[p]) >>> (7 - p));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            if (i > 0 && i % 16 == 0) {
                builder.append(" ");
            }
            byte value = valueAt(i);
            builder.append(value);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(0x80);
        System.out.println(0x40);
        System.out.println(0x20);
        System.out.println(0x10);
        System.out.println(0x01);
    }
}
