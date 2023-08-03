package com.file;

import java.io.FileInputStream;

public class TestFile {

    public static void main(String[] args) {
        String filePath ="/Users/chenxiao/IdeaProjects/demo/learn-demo/src/main/java/demo/";
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            //fileInputStream.available()表示读取文件全部内容
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            String s = new String(bytes);
            fileInputStream.close();

            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
