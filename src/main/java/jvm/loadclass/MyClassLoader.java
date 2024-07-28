package jvm.loadclass;

import java.io.*;
import java.util.Objects;

public class MyClassLoader extends ClassLoader {

    private String bashPath;

    public MyClassLoader(String bashPath) {
        this.bashPath = bashPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 尝试自己加载
        byte[] classData = new byte[0];
        try {
            classData = getClassData(name);
            if (Objects.nonNull(classData)) {
                return defineClass(name, classData, 0, classData.length);
            }
        } catch (IOException e) {
            // todo 处理异常
        }
        // 如果不能加载，委托给父类
        return super.loadClass(name);


        // 如果无法加载，委托给父类
    }

    /**
     * 将 .class 文件转成二进制
     *
     * @param name
     * @return
     * @throws IOException
     */
    private byte[] getClassData(String name) throws IOException {
        name = name.replace("\\.", "/");
        String path = bashPath + File.separator + name.replace('.', File.separatorChar) + ".class";
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesReadNum = 0;
            while ((bytesReadNum = fis.read(buffer)) != -1) {
                // 从 buffer中读取写入到 ByteArrayOutputStream中
                baos.write(buffer, 0 , bytesReadNum);
            }
            return baos.toByteArray();
        }
        return null;
    }

}
