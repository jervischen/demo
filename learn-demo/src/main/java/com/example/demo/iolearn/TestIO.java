package com.example.demo.iolearn;

import org.junit.Test;

import java.io.*;

/**
 * Created in 2018-02-28 16:35.
 *
 * @author chenxiao
 */
public class TestIO {
    @Test
    public void FileInputStreamTest() throws IOException {
        FileInputStream fis = new FileInputStream("tmp2.txt");
        FileOutputStream fos = new FileOutputStream("tmp3.txt");
        try {
            byte[] buf = new byte[1024];
            int hasRead = 0;
            //read()返回的是单个字节数据（字节数据可以直接专程int类型)，但是read(buf)返回的是读取到的字节数，真正的数据保存在buf中
            while ((hasRead = fis.read(buf)) > 0) {
                System.out.println(new String(buf, 0, hasRead));
                fos.write(buf, 0, hasRead);
            }
            fos.write("11111".getBytes());
            System.out.println("读取tmp2写入到tmp3完成");
        } catch (Exception e) {
            //在finally块里close更安全
            fis.close();
            fos.close();
        }


    }

    @Test
    public void FileReaderTest() throws IOException {
        // 在try() 中打开的文件， JVM会自动关闭
        try (FileReader fr = new FileReader("tmp2.txt");FileWriter fw = new FileWriter("FileWriter.txt")) {
            char[] buf = new char[32];
            int hasRead = 0;
            // 每个char都占两个字节，每个字符或者汉字都是占2个字节，因此无论buf长度为多少，总是能读取中文字符长度的整数倍,不会乱码
            while ((hasRead = fr.read(buf)) > 0) {
                fw.write(buf,0,hasRead);
            }
            System.out.println("读取tmp2写入到FileWriter完成");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void FileOutputStreamTest() throws FileNotFoundException, IOException {
        try (
                //在try()中打开文件会在结尾自动关闭
                FileInputStream fis = new FileInputStream("tmp2.txt");
                FileOutputStream fos = new FileOutputStream("tmp3.txt");
        ) {
            byte[] buf = new byte[4];
            int hasRead = 0;
            while ((hasRead = fis.read(buf)) > 0) {
                //每读取一次就写一次，读多少就写多少
                fos.write(buf, 0, hasRead);
            }
            System.out.println("write success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FileWriterTest() throws IOException {
        try (FileWriter fw = new FileWriter("tmp4.txt")) {
            fw.write("天王盖地虎\r\n");
            fw.write("宝塔镇河妖\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PipedOutputStreamTest(){
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
    }
}
