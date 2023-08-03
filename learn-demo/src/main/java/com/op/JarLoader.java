package com.op;

import com.netflix.governator.annotations.AutoBindSingleton;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class JarLoader {
    private URLClassLoader urlClassLoader;

    public JarLoader(URLClassLoader urlClassLoader) {

        this.urlClassLoader = urlClassLoader;

    }

    public void loadJar(URL url) throws Exception {

//        Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//
//        addURL.setAccessible(true);
//
//        addURL.invoke(urlClassLoader, url);

        // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        //获取方法的访问权限以便写回
        boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.setAccessible(accessible);
        }


    }


    private static void loadjar(JarLoader jarLoader, String path) throws MalformedURLException, Exception {

        File libdir = new File(path);

        if (libdir != null && libdir.isDirectory()) {

            File[] listFiles = libdir.listFiles(new FileFilter() {

                @Override

                public boolean accept(File file) {

// TODO Auto-generated method stub

                    return file.exists() && file.isFile() && file.getName().endsWith(".jar");

                }

            });

            for (File file : listFiles) {

                jarLoader.loadJar(file.toURI().toURL());

            }

        } else {

            System.out.println("[Console Message] Directory [" + path + "] does not exsit, please check it");

            System.exit(0);

        }

    }

    public static void main(String[] args) throws Exception {

        JarLoader jarLoader = new JarLoader((URLClassLoader) ClassLoader.getSystemClassLoader());

        loadjar(jarLoader, "/Users/chenxiao/Desktop/test/");

        Reflections reflections = new Reflections("fm.lizhi.pp.scene");
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(AutoBindSingleton.class);
        Set<Method> methods = new HashSet<>();
        for (Class<?> clz : set) {
            String superClassName = clz.getSuperclass().getSimpleName();
            if (!superClassName.contains("Service")) {
                continue;
            }
            Method method = null;
            try {
                method = clz.getDeclaredMethod("getOP");
                methods.add(method);
                System.out.println(clz.getSimpleName());
            } catch (Exception e) {
                continue;
            }
        }

        for (Method method : methods) {
            int op = 0;
            String clzName = method.getDeclaringClass().getSimpleName();
            try {
                op = (int) method.invoke(method.getDeclaringClass().newInstance());
            } catch (Throwable e) {
                System.out.println("OP拿不到：" + clzName);
                continue;
            }
            String pat = "OP=" + op;
            System.out.println(method.getName() + " " + pat);
        }

        System.in.read();
    }
}
