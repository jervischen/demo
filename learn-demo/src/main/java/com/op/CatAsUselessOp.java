package com.op;

import com.google.common.collect.Sets;
import com.netflix.governator.annotations.AutoBindSingleton;
import com.util.DateUtil;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CatAsUselessOp {
    public static void main(String[] args) throws Exception {
        String projectname = System.getProperty("user.dir");
        String projName = projectname.substring(projectname.lastIndexOf('\\')+1,projectname.length());
//        String projName = "app-hy-core";
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

        String currentMonthStart = DateUtil.formatDateToString(DateUtil.getMonthStart(new Date()), DateUtil.date);
        String nextMonthStart = DateUtil.formatDateToString(DateUtil.getMonthAfter(new Date(),1), DateUtil.date);
        String preMonthStart = DateUtil.formatDateToString(DateUtil.getMonthBefore(new Date(),1), DateUtil.date);
        String preTwoMonthStart = DateUtil.formatDateToString(DateUtil.getMonthBefore(new Date(),2), DateUtil.date);

        String currentMonthUrl = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=month&type=URL&startDate="+currentMonthStart+"&endDate="+nextMonthStart+"&domain=" + projName;
        System.out.println(currentMonthUrl);
        String preMonthUrl     = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=month&type=URL&startDate="+preMonthStart+"&endDate="+currentMonthStart+"&domain=" + projName;
        System.out.println(preMonthUrl);
        String preTwoMonthUrl     = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=month&type=URL&startDate="+preTwoMonthStart+"&endDate="+preMonthStart+"&domain=" + projName;
        System.out.println(preTwoMonthUrl);
        System.out.println("-----------------------------------------");
        String catCurrentRes = getHTML(currentMonthUrl);
        String catPreRes = getHTML(preMonthUrl);
        String catPreTwoRes = getHTML(preTwoMonthUrl);
        //统计出无用的方法
//        System.out.println(projName);
        Set<String> currentMonthUselessSet = countMethod( methods, catCurrentRes);
//        System.out.println(projName);
        Set<String> preMonthUselessSet = countMethod( methods, catPreRes);
        Set<String> preTwoMonthUselessSet = countMethod( methods, catPreTwoRes);


        Set<String> retainAllSet = new HashSet<>();
        retainAllSet.addAll(currentMonthUselessSet);
        retainAllSet.addAll(preMonthUselessSet);
        retainAllSet.addAll(preTwoMonthUselessSet);
//        //取交集
        retainAllSet.retainAll(preMonthUselessSet);
        retainAllSet.retainAll(currentMonthUselessSet);
        retainAllSet.retainAll(preTwoMonthUselessSet);
        System.out.println("-----------------------------------------");
        System.out.println("三个月数据的交集：");
        for (String s : retainAllSet) {
            System.out.println(s);
        }

    }


    /**
     * 统计出无用的方法
     * @param methods
     * @param catRes
     * @return
     */
    private static Set<String> countMethod(Set<Method> methods,String catRes) {
        int all = methods.size();
        int notUse = 0;
        int use = 0;
        Set<String> notUseSet = new HashSet<>();
        StringBuilder bdr = new StringBuilder();
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
                use++;
            } else {
                notUse++;
                //只加入无用的
                notUseSet.add(clzName + ", " + pat);
                bdr.append(clzName);
                bdr.append(", ").append(pat);
                bdr.append(System.lineSeparator());
            }
        }


//        //无用/有用
//        System.out.printf("无用方法占比：%d/%d\n", notUse, all);
//        //无用名
//        System.out.println("无用方法：");
//        System.out.println(bdr.toString());
        return notUseSet;
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
