package com.op;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Auther: linyous
 * @Date: 2022-04-28 11:45
 * @Description:
 */
public class Encoder {
    private String  classPath = "/Users/chenxiao/Desktop/test/";
    public static  List<String> JarPageName = new ArrayList<>();
    public static  List<String> ProjectName = new ArrayList<>();


    @Test
    public void Scanner(){
        //扫描文件夹中的jar包数据，并解压
        preReadClassFile();
        for (String s : JarPageName) {
            extractJar(classPath,classPath+s);
        }

        System.out.println("cshi");
    }

    @Test
    public void classLoaderFun(){
        ClassPathClassLoader classPathClassLoader = new ClassPathClassLoader(classPath);
    }
    public static void main(String[] args) {
        String s = "fm.lizhi.live.pp.core.manager.PpGloryManager$1";

        if(s.contains("$")){
            System.out.println("$$$$$$$$");
        }
        String[] split = s.split("\\$");
        for (String s1 : split) {
            System.out.println(s1);
        }

    }

    public void preReadClassFile() {
        File[] files = new File(classPath).listFiles();
        if (files != null) {
            for (File file : files) {
                scanClassFile(file);
            }
        }
    }
    public String getProjectName(String  jarname){
        String[] split = jarname.split(".jar");
        String replace = split[0].replace("-", "_");
        return replace;
    }
    public void scanClassFile(File file) {
        if (file.exists()) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                JarPageName.add(file.getName());
             ProjectName.add(getProjectName(file.getName()));
            } else if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    scanClassFile(f);
                }
            }
        }
    }
    public static void extractJar(String destDir,String jarFile) {
        //    String jarFile = "E:/test/com.ide.core_1.0.0.jar";
        if((jarFile == null) || (jarFile.equals("")) || !jarFile.endsWith(".jar")) {
            return;
        }
        try {
            JarFile jar = new JarFile(jarFile);
            Enumeration<?> enumeration = jar.entries();
            while(enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement();
                String[] names = jarEntry.getName().split("/");
                for(int i = 0; i < names.length; i++) {
                    String path = destDir;
                    for(int j = 0; j < (i + 1); j++) {
                        path += File.separator + names[j];
                    }
                    File file = new File(path);
                    if(!file.exists()) {
                        if((i == (names.length - 1)) && !jarEntry.getName().endsWith("/")) {
                            file.createNewFile();
                        } else {
                            file.mkdirs();
                            continue;
                        }
                    } else {
                        continue;
                    }
                    InputStream is = jar.getInputStream(jarEntry);
                    FileOutputStream fos = new FileOutputStream(file);
                    while(is.available() > 0) {
                        fos.write(is.read());
                    }
                    fos.flush();
                    fos.close();
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
