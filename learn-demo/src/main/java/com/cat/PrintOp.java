package com.cat;

import com.google.common.collect.Sets;
import com.netflix.governator.annotations.AutoBindSingleton;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class PrintOp {
    public static void main(String[] args) throws Exception {
        String projName = getProjName();
        projName = projName.replaceAll("-", "_");

        Reflections reflections = new Reflections("");
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
            } catch (Exception e) {
                continue;
            }
        }

        String url = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=month&type=URL&domain=" + projName;
        String catRes = getHTML(url);
        System.out.println(url);
        int all = methods.size();
        int notUse = 0;
        int use = 0;
        StringBuilder nonUseMethod = new StringBuilder();
        StringBuilder useMethod = new StringBuilder();
        Set<String> exSet = Sets.newHashSet("ResouceService");
        for (Method method : methods) {
            int op = 0;
            String clzName = method.getDeclaringClass().getSimpleName();
            if (exSet.contains(clzName)) continue;
            try {
                op = (int) method.invoke(method.getDeclaringClass().newInstance());
            } catch (Throwable e) {
                System.out.println("OP拿不到：" + clzName);
                continue;
            }
            String pat = "OP=" + op;
            if (catRes.contains(pat + "<") || catRes.contains(pat + "(")) {
                useMethod.append(clzName);
                useMethod.append(", ").append(pat);
                useMethod.append(System.lineSeparator());
                use++;
            } else {
                notUse++;
                nonUseMethod.append(clzName);
                nonUseMethod.append(", ").append(pat);
                nonUseMethod.append(System.lineSeparator());
            }
        }

        System.out.println(projName);
        //无用/有用
        System.out.printf("%d/%d\n", notUse, all);
        //有用方法
        System.out.println("有用方法：");
        System.out.println(useMethod.toString());
        //无用名
        System.out.println("无用方法：");
        System.out.println(nonUseMethod.toString());
    }

    static String getProjName() {
        String path = (String) System.getProperties().get("java.class.path");
        int end = path.indexOf("/target/classes");
        path = path.substring(0, end);
        return path.substring(path.lastIndexOf("/") + 1);
    }

    static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
