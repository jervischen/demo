package demo;


import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.utils.SourceRoot;
import com.util.DateUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AsDcCatParser {
    static String separator = System.getProperties().getProperty("file.separator");

    static String packagePath = "";
    static String projectName = "";
    /**
     * as或者dc接口
     */
    static Set<Method> methods = new HashSet<>();

    public static void main(String[] args) {
//        printAsInterface("/Users/chenxiao/IdeaProjects/xm/app-xm-core/src/main/java/fm/lizhi/live/pp/core","fm.lizhi.live.pp.core.handle");
        char c = 103;
        System.out.println(c);
    }


    /**
     * 输出有用as协议
     * @param inputPath 源码目录 xx/xx/xx
     * @param pac 协议所在包路径 a.b.c
     */
    private static void printAsInterface(String inputPath,String pac){
        try {
            Path path = Paths.get(inputPath);
            SourceRoot sourceRoot = new SourceRoot(path);
            sourceRoot.tryToParse();
            getAllValidAsInterface(sourceRoot.getCompilationUnits(),pac);
        }catch (Exception e){

        }
    }

    private static void getProjectName() {
        String dir = System.getProperty("user.dir");
        String projName = dir.substring(dir.lastIndexOf(separator) + 1, dir.length());
        projectName = projName.replaceAll("-", "_");
    }


    public static Set<String> getAllValidAsInterface(List<CompilationUnit> units, String pac) {
        packagePath = pac;
        getProjectName();
        for (CompilationUnit unit : units) {
            getAsOp(unit);
        }

        String currentMonthStart = DateUtil.formatDateToString(DateUtil.getMonthStart(new Date()), DateUtil.date);
        String nextMonthStart = DateUtil.formatDateToString(DateUtil.getMonthAfter(new Date(),1), DateUtil.date);
        String currentMonthUrl = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=month&type=URL&startDate=" + currentMonthStart + "&endDate=" + nextMonthStart + "&domain=" + projectName;
        String weekMonthStart = DateUtil.formatDateToString(DateUtil.getDayBefore(7), DateUtil.date);
        String weekMonthEnd = DateUtil.formatDateToString(DateUtil.getCurrentDay(), DateUtil.date);
        String weekUrl = "http://ymcat_monitor_web.lizhi.fm/cat/r/t?op=history&ip=All&reportType=week&type=URL&startDate=" + weekMonthStart + "&endDate=" + weekMonthEnd + "&domain=" + projectName;

        Set<String> countMethod = countMethod(getHTML(currentMonthUrl));
        countMethod.addAll(countMethod(getHTML(weekUrl)));
        return countMethod;
    }

    /**
     * 统计出无用的方法
     *
     * @param catRes
     * @return
     */
    private static Set<String> countMethod(String catRes) {
        Set<String> useSet = new HashSet<>();
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
            if (catRes.contains(pat + "<") || catRes.contains(pat + "(")) {
                System.out.println("cat有流量"+pat + " 接口=" + clzName);
                useSet.add(clzName);
            }
        }

        return useSet;
    }


    private static void getAsOp(CompilationUnit unit) {
        if (!unit.getPackageDeclaration().isPresent()) {
            return;
        }
        String packageName = unit.getPackageDeclaration().get().getName().toString();
        if (packageName.contains(packagePath)) {
            List<ClassOrInterfaceDeclaration> clazzs = unit.getNodesByType(ClassOrInterfaceDeclaration.class);
            for (ClassOrInterfaceDeclaration clinter : clazzs) {
                try {
                    if (!clinter.isAnnotationPresent("AutoBindSingleton")) {
                        continue;
                    }
                    String clazzName = packageName
                            + "." + clinter.getName().toString();
                    Class<?> clazz = Class.forName(clazzName);
                    methods.add(clazz.getMethod("getOP"));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    private static String getHTML(String urlToRead) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result.toString();
    }
}
