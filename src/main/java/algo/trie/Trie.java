package algo.trie;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Objects;

public class Trie<T> {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    static class TrieNode<T> {
        private TrieNode[] children = new TrieNode[2];
        private T local = null;

        public T getLocal() {
            return local;
        }

        public void setLocal(T local) {
            this.local = local;
        }

        public TrieNode getChild(int pos) {
            return children[pos];
        }

        public void setChild(int pos, TrieNode child) {
            children[pos] = child;
        }
    }

    public void insert(BinaryIP ip, int mask, T local) {
        if (ip == null || mask <= 0) {
            return;
        }
        TrieNode node = root;
        for (int i = 0; i < mask; i++) {
            int pos = ip.valueAt(i);
            TrieNode child = node.getChild(pos);
            if (child == null) {
                child = new TrieNode();
                node.setChild(pos, child);
            }
            node = child;
        }
        node.setLocal(local);
    }

    public T search(BinaryIP ip) {
        if (ip == null) {
            return null;
        }
        TrieNode node = root;
        for (int i = 0; i < 128; i++) {
            int pos = ip.valueAt(i);
            TrieNode child = node.getChild(pos);
            if (child == null) {
                return null;
            }
            T local = (T) child.getLocal();
            if (local != null) {
                return local;
            }
            node = child;
        }
        return null;
    }


    public TrieNode getRoot() {
        return this.root;
    }

    public static void main(String[] args) throws Exception {

        Trie<String> cache = new Trie<>();

        String path = "D:\\outIpv6Info.txt";
        String content = FileUtils.readFileToString(new File(path), "UTF-8");
        String[] lines = content.split("\n");
        System.out.println("个数：" + lines.length);
        for (String line : lines) {
            String[] items = line.split("\t");
            String[] infos = items[0].split("/");
            String ip = infos[0];
            Integer mask = Integer.parseInt(infos[1]);
            String localId = items[4];
            cache.insert(new BinaryIP(ip), mask, localId);
        }
        for (String line : lines) {
            String[] items = line.split("\t");
            String[] infos = items[0].split("/");
            String ip = infos[0];
            String localId = items[4];
            String local = cache.search(new BinaryIP(ip));
            System.out.println(local);
            Assert.notNull(local);
            Assert.isTrue(Objects.equals(local, localId));
        }
    }
}

/*
public class Trie {

    private TrieNode root = new TrieNode('/');

    // 插入字符串
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    // 查找字符串
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false; // 没找到
            }
            p = p.children[index];
        }
        return p.isEndingChar;
    }

    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public TrieNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String a = "adddc";
        trie.insert(a.toCharArray());
        boolean b = trie.find("adddc".toCharArray());
        System.out.println(b);
    }
}
*/
