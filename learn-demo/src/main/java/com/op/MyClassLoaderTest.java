package com.op;


import com.google.common.collect.Sets;
import com.netflix.governator.annotations.AutoBindSingleton;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


/**

 * Created by chenerzhu on 2018/10/4.

 */

public class MyClassLoaderTest {

    @Test
    public void testClassLoader() throws Exception {
        Encoder encoder = new Encoder();
        //扫描，解压该目录下的所有jar包
        encoder.Scanner();
        List<String> projectName = Encoder.ProjectName;
        //加载该路径下的所有class文件，并载入自定义类加载器
        ClassPathClassLoader myClassLoader = new ClassPathClassLoader("/Users/chenxiao/Desktop/test");
        Map<String, byte[]> classMap =
                ClassPathClassLoader.classMap;
        classMap.forEach((key,value) ->{
            try {
                System.out.println("key "+key);
                myClassLoader.loadClass(key);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        myClassLoader.getClass();
        Reflections reflections = new Reflections("");
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(AutoBindSingleton.class);
        Set<Method> methods = new HashSet<>();
        for (Class<?> clz : set) {
            String superClassName = clz.getSuperclass().getSimpleName();
            if (!superClassName.contains("Service")) {
                continue;
            }
            System.out.println(clz.getName());
            Method method = null;
            try {
                method = clz.getDeclaredMethod("getOP");
                methods.add(method);
            } catch (Exception e) {
                continue;
            }
        }
        for (String pName : projectName) {
            String url = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=month&type=URL&domain="+pName;
            String catRes = getHTML(url);
            System.out.println(url);
            int all = methods.size();
            int notUse = 0;
            int use = 0;
            StringBuilder bdr = new StringBuilder();
            Set<String> exSet = Sets.newHashSet("ResouceService");
            for (Method method : methods) {
                int op = 0;
                String clzName = method.getDeclaringClass().getSimpleName();
                if (exSet.contains(clzName)) continue;
                try {
                    op = (Integer) method.invoke(method.getDeclaringClass().newInstance());
                } catch (Throwable e) {
                    System.out.println("OP拿不到：" + clzName);
                    continue;
                }
                String pat = "OP=" + op;
                if (catRes.contains(pat + "<") || catRes.contains(pat + "(")) {
                    use++;
                } else {
                    notUse++;
                    bdr.append(clzName);
                    bdr.append(", ").append(pat);
                    bdr.append(System.lineSeparator());
                }
            }
            System.out.println(pName);
            //无用/有用
            System.out.printf("%d/%d\n", notUse, all);
            //无用名

            System.out.println("无用方法：");
            System.out.println(bdr.toString());
        }

    }


    static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    @Test
    public  void Tests() throws Exception {
        List<String> projectName = new ArrayList<>();
        projectName.add("app_pp_sence");
        projectName.add("app_live_pp_core");
        ClassLoader rbcClassLoader = RbcClassLoader.getRbcClassLoader("/Users/linyous/Desktop/Test");
        ClassLoader parent = rbcClassLoader.getParent();
        Reflections reflections = new Reflections("");
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(AutoBindSingleton.class);
        Set<Method> methods = new HashSet<>();
        for (Class<?> clz : set) {
            String superClassName = clz.getSuperclass().getSimpleName();
            if (!superClassName.contains("Service")) {
                continue;
            }
            System.out.println(clz.getName());
            Method method = null;
            try {
                method = clz.getDeclaredMethod("getOP");
                methods.add(method);
            } catch (Exception e) {
                continue;
            }
        }
        for (String pName : projectName) {
            String url = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=month&type=URL&domain="+pName;
            String catRes = getHTML(url);
            System.out.println(url);
            int all = methods.size();
            int notUse = 0;
            int use = 0;
            StringBuilder bdr = new StringBuilder();
            Set<String> exSet = Sets.newHashSet("ResouceService");
            for (Method method : methods) {
                int op = 0;
                String clzName = method.getDeclaringClass().getSimpleName();
                if (exSet.contains(clzName)) continue;
                try {
                    op = (Integer) method.invoke(method.getDeclaringClass().newInstance());
                } catch (Throwable e) {
                    System.out.println("OP拿不到：" + clzName);
                    continue;
                }
                String pat = "OP=" + op;
                if (catRes.contains(pat + "<") || catRes.contains(pat + "(")) {
                    use++;
                } else {
                    notUse++;
                    bdr.append(clzName);
                    bdr.append(", ").append(pat);
                    bdr.append(System.lineSeparator());
                }
            }
            System.out.println(pName);
            //无用/有用
            System.out.printf("%d/%d\n", notUse, all);
            //无用名

            System.out.println("无用方法：");
            System.out.println(bdr.toString());
        }
    }

}