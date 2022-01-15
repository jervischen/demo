package com.example.demo.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created in 2018-03-13 15:00.
 *
 * @author chenxiao
 */
public class TestFile {
    private static Logger logger = LoggerFactory.getLogger(TestFile.class);


    public void writing() {
        try {
            //Whatever the file path is.
            File statText = new File("D:/statsTest.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write("POTATO!!!");
            w.write("哈哈哈");
            w.close();
            System.out.println("结束");
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
    }

    public static void write(StringBuilder sb, String outputpath){
        try {
            // 创建一个 FileWriter 实例对象，
            FileWriter fw = new FileWriter(outputpath);
            // 创建一个 BufferedWriter 实例对象
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
            fw.close();
            System.out.println("文件创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args) {
        TestFile write = new TestFile();
        write.writing();
    }
}
