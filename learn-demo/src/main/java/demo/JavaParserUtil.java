package demo;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.utils.SourceRoot;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * https://weibo.com/ttarticle/p/show?id=2309404188803928605458
 * https://www.javadoc.io/doc/com.github.javaparser/javaparser-core/latest/index.html
 */
public class JavaParserUtil {
    /**
     * 磁盘文件符
     */
    static String separator = System.getProperties().getProperty("file.separator");

    public enum ServiceType {
        AS, DC;
    }

    static String mainPath = "src" + separator + "main" + separator + "java";
    static ServiceType serviceType = ServiceType.AS;
    /**
     * 需要输出的java文件
     */
    static HashSet<CompilationUnit> javaCodeSet = new HashSet<>();
    /**
     * 需要输出的java文件类名
     */
    static HashSet<String> javaCodeName = new HashSet<>();

    /**
     * 需要输出的java方法名
     */
    static HashSet<String> includeMethodName = new HashSet<>();

    /**
     * 需要迁移的接口
     */
    static Set<String> interfaceSet = new HashSet<>();


    /**
     * 所有的java文件
     */
    static List<CompilationUnit> allUnits = new ArrayList<>(1);
    /**
     * 代码扫描输入路径(目录)
     */
    static String inputPath = "";
    /**
     * 代码输入路径(目录)
     */
    static String outputPath = "";
    /**
     * 输入包根路径(xx.xx.xx)
     */
    static String inPackageRoot = "";
    /**
     * 输出包路径
     */
    static String outPackagePath = "";

    /**
     * 扫描接口包路径
     */
    static String interfacePackagePath = "";

    static String javaCode = "";
    static SourceRoot sourceRoot ;

    static {

    }

    /**
     * 有些扫描不到的可以自己添加方法名
     * @param methodNames
     */
    public static void setIncludeMethodName(List<String> methodNames){
        includeMethodName.addAll(methodNames);
    }


    public static void main(String[] args) throws Exception {
        migrateAS("/Users/chenxiao/IdeaProjects/xm/app-xm-core/src/main/java/fm/lizhi/live/pp/core",
                "/Users/chenxiao/IdeaProjects/xm/app-xm-core/src/main/java/fm/lizhi/app/live",
                "fm.lizhi.live.pp.core.handle", "core", ServiceType.AS);

//        parserDC();

//        MigrateUtil.startAsMigrate("app-xm-core","/Users/chenxiao/IdeaProjects/xm/app-xm-core/src/main/java/fm/lizhi/live/pp/core",
//                "/Users/chenxiao/IdeaProjects/xm/app-xm-core/src/main/java/fm/lizhi/app/live",
//                "fm.lizhi.live.pp.core.handle", "core",false);

    }



    private static void createFile(String fileName) {
        String dirPath = fileName.substring(0, fileName.lastIndexOf(separator) + 1);
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(fileName);
        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
            bufferedWriterFile(fileName);
            System.out.println("迁移成功：" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 BufferedWriter 写文件
     *
     * @param filepath 文件目录
     * @throws IOException
     */
    private static void bufferedWriterFile(String filepath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(javaCode);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * 迁移AS
     * @param in               输入绝对目录 /xxx/src/xxx
     * @param out              输出绝对目录 /xxx/src/xxx
     * @param interPackagePath 需要扫描的协议所在包路径 xx.xxx.xx(as或dc的协议包路径)
     * @param suffix           输出包路径后缀 xxx.xx.suffix
     * @throws IOException
     */
    public static void migrateAS(String in, String out, String interPackagePath,
                                 String suffix, ServiceType type) throws IOException {
        pathVerify(in);
        pathVerify(out);
        pathVerify(interPackagePath);
        pathVerify(suffix);


        initPath(in, out, suffix, interPackagePath, type);
        start(interPackagePath);
    }

    /**
     * 迁移DC
     * @param in               输入绝对目录 /xxx/src/xxx
     * @param out              输出绝对目录 /xxx/src/xxx
     * @param interPackagePath 需要扫描的协议所在包路径 xx.xxx.xx(as或dc的协议包路径)
     * @param suffix           输出包路径后缀 xxx.xx.suffix
     * @throws IOException
     */
    public static void migrateDC(String in, String out, String interPackagePath,
                                 String suffix, ServiceType type) throws IOException {
        pathVerify(in);
        pathVerify(out);
        pathVerify(interPackagePath);
        pathVerify(suffix);


        initPath(in, out, suffix, interPackagePath, type);
        start(interPackagePath);
    }



    private static void pathVerify(String path) {
        if (path.endsWith(separator) || path.endsWith(".")) {
            System.err.println("目录不要以" + separator + "结尾");
            System.exit(-1);
        }
    }


    /**
     * @param inPackagePath 代码扫描输入路径(目录)
     * @throws IOException
     */
    private static void start(String inPackagePath) throws IOException {
        parseJava(inPackagePath);
        createJava();
    }

    /**
     * 生成java
     */
    private static void createJava() {
        for (CompilationUnit unit : javaCodeSet) {
            removeInvalidMethod(unit);
        }

        for (CompilationUnit unit : javaCodeSet) {
            createCode(unit);
        }
    }

    /**
     * 解析java
     *
     * @param inPackagePath
     * @throws IOException
     */
    private static void parseJava(String inPackagePath) throws IOException {
        Path path = Paths.get(inputPath);
        sourceRoot = new SourceRoot(path);
        sourceRoot.tryToParse();
        allUnits = sourceRoot.getCompilationUnits();

        interfaceSet = AsDcCatParser.getAllValidAsInterface(allUnits, inPackagePath);

        for (CompilationUnit unit : allUnits) {
            if (unit.getTypes().isEmpty()) {
                //过滤空类
                continue;
            }
            if (interfaceSet.contains(unit.getType(0).getNameAsString())) {
                getLinkClass(unit);
            }
            //PPCloseLiveService  DatePlayPageManager
//            if (unit.getType(0).getNameAsString().equals("HttpUtil")) {
//                getLinkClass(unit);
//            }
        }
    }

    /**
     * 获取关联的类
     *
     * @param unit
     */
    private static void getLinkClass(CompilationUnit unit) {
        try {

            getValidMethodCallExpr(unit);
            getClassOrInterfaceDeclaration(unit);
            getEnumDeclaration(unit);
            getCallableDeclaration(unit);
            getVariableDeclarator(unit);
            getFieldDeclaration(unit);
            getParameter(unit);
            getImports(unit);
            getNameExpr(unit);
//            getExpression(unit);
        } catch (Exception e) {
            System.out.println(unit.toString() + e);
        }

    }

    /**
     * 初始化各种路径
     *
     * @param in
     * @param out
     */
    private static void initPath(String in, String out, String suffix, String interPackagePath, ServiceType type) {

        inputPath = in;
        inPackageRoot = in.substring(in.lastIndexOf(mainPath) + 14).replace(separator, ".");
        outputPath = out.substring(0, in.lastIndexOf(mainPath) + 14);
        outPackagePath = out.substring(in.lastIndexOf(mainPath) + 14).replace(separator, ".");
        if (StringUtils.isNotBlank(suffix)) {
            outPackagePath = outPackagePath + "." + suffix;
        }
        interfacePackagePath = interPackagePath;
        serviceType = type;
    }


    /**
     * 获取各种命名
     *
     * @param unit
     */
    private static void getNameExpr(CompilationUnit unit) {
        List<NameExpr> clazzs = unit.getNodesByType(NameExpr.class);
        for (NameExpr clazz : clazzs) {
            getJavaCode(clazz.getNameAsString());
        }
    }

    /**
     * 获取有用的方法名
     *
     * @param unit
     */
    private static void getValidMethodCallExpr(CompilationUnit unit) {
        List<MethodCallExpr> clazzs = unit.getNodesByType(MethodCallExpr.class);
        for (MethodCallExpr clazz : clazzs) {
            if (!includeMethodName.contains(clazz.getNameAsString())) {
                includeMethodName.add(clazz.getNameAsString());
            }
        }

        List<MethodDeclaration> mes = unit.getNodesByType(MethodDeclaration.class);
        for (MethodDeclaration me : mes) {
            if (unit.getPackageDeclaration().get().getNameAsString().contains(interfacePackagePath)) {
                includeMethodName.add(me.getNameAsString());
            }
            //获取子方法(方法中的方法)
            List<MethodDeclaration> childNodesByType = me.getNodesByType(MethodDeclaration.class);
            for (MethodDeclaration methodDeclaration : childNodesByType) {
                includeMethodName.add(methodDeclaration.getNameAsString());
            }
        }
    }




    /**
     * 获取Expression包
     *
     * @param unit
     */
    private static void getExpression(CompilationUnit unit) {
        List<Expression> clazzs = unit.getNodesByType(Expression.class);
        System.out.println(11);
    }

    /**
     * 获取import 包
     *
     * @param unit
     */
    private static void getImports(CompilationUnit unit) {
        NodeList<ImportDeclaration> imports = unit.getImports();
        for (ImportDeclaration anImport : imports) {
            getJavaCode(anImport.getName().getIdentifier());
        }
    }

    /**
     * 获取返回体
     *
     * @param unit
     */
    private static void getCallableDeclaration(CompilationUnit unit) {
        List<CallableDeclaration> clazzs = unit.getNodesByType(CallableDeclaration.class);
        for (CallableDeclaration method : clazzs) {
            getJavaCode(((MethodDeclaration) method).getType().toString());
        }
    }


    /**
     * 获取类
     *
     * @param unit
     */
    private static void getClassOrInterfaceDeclaration(CompilationUnit unit) {
        List<CompactConstructorDeclaration> a = unit.getNodesByType(CompactConstructorDeclaration.class);
        List<ConstructorDeclaration> b = unit.getNodesByType(ConstructorDeclaration.class);
        List<RecordDeclaration> c = unit.getNodesByType(RecordDeclaration.class);
        List<ReceiverParameter> d = unit.getNodesByType(ReceiverParameter.class);
        System.out.println(11);

        List<ClassOrInterfaceDeclaration> clazzs = unit.getNodesByType(ClassOrInterfaceDeclaration.class);
        for (ClassOrInterfaceDeclaration method : clazzs) {
            getJavaCode(method.getNameAsString());
            method.getExtendedTypes().stream().forEach(m -> {
                getJavaCode(m.getNameAsString());
            });

            method.getImplementedTypes().stream().forEach(m -> {
                getJavaCode(m.getNameAsString());
            });

        }
    }

    /**
     * 枚举
     *
     * @param unit
     */
    private static void getEnumDeclaration(CompilationUnit unit) {
        List<EnumDeclaration> clazzs = unit.getNodesByType(EnumDeclaration.class);
        for (EnumDeclaration method : clazzs) {
            getJavaCode(method.getNameAsString());
        }
    }


    /**
     * 获取方法参数
     *
     * @param unit
     */
    private static void getParameter(CompilationUnit unit) {
        List<Parameter> methods = unit.getNodesByType(Parameter.class);
        for (Parameter method : methods) {
            getJavaCode(method.getType().toString());
        }
    }

    /**
     * 获取字段定义
     *
     * @param unit
     */
    private static void getFieldDeclaration(CompilationUnit unit) {
        List<FieldDeclaration> methods = unit.getNodesByType(FieldDeclaration.class);
        for (FieldDeclaration method : methods) {
            method.getVariables().forEach(v -> {
                getJavaCode(v.getType().toString());
            });
        }
    }


    /**
     * 获取定义变量
     *
     * @param unit
     */
    private static void getVariableDeclarator(CompilationUnit unit) {
        List<VariableDeclarator> methods = unit.getNodesByType(VariableDeclarator.class);
        for (VariableDeclarator method : methods) {
            getJavaCode(method.getType().toString());
        }
    }

    /**
     * 获取java代码
     *
     * @param className
     */
    private static void getJavaCode(String className) {
        getInterfaceCode(className);
        getClassCode(className);
        getEnumCode(className);
    }

    /**
     * 获取接口源码
     *
     * @param className
     */
    private static void getInterfaceCode(String className) {
        Optional<CompilationUnit> first = allUnits.stream().filter(unit -> {
            Optional<ClassOrInterfaceDeclaration> optional = unit.getInterfaceByName(className);
            return optional.isPresent();
        }).findFirst();

        if (first.isPresent()) {
            CompilationUnit unit = first.get();
            javaCodeSet.add(unit);
            javaCodeName.add(unit.getType(0).getNameAsString());
        }
    }

    /**
     * 获取枚举源码
     *
     * @param className
     */
    private static void getEnumCode(String className) {
        Optional<CompilationUnit> first = allUnits.stream().filter(unit -> {
            Optional<EnumDeclaration> optional = unit.getEnumByName(className);
            return optional.isPresent();
        }).findFirst();

        if (first.isPresent()) {
            CompilationUnit unit = first.get();
            if (!javaCodeSet.contains(unit)) {
                javaCodeSet.add(unit);
                javaCodeName.add(unit.getType(0).getNameAsString());
                getLinkClass(unit);
            }
        }
    }

    /**
     * 获取类源码
     *
     * @param className
     */
    private static void getClassCode(String className) {
        Optional<CompilationUnit> first = allUnits.stream().filter(unit -> {
            Optional<ClassOrInterfaceDeclaration> optional = unit.getClassByName(className);
            return optional.isPresent();
        }).findFirst();

        if (first.isPresent()) {
            CompilationUnit unit = first.get();
            if (!javaCodeSet.contains(unit)) {
                javaCodeSet.add(unit);
                javaCodeName.add(unit.getType(0).getNameAsString());
                getLinkClass(unit);
            }
        }
    }

    /**
     * 删除无用方法
     *
     * @param unit
     */
    private static void removeInvalidMethod(CompilationUnit unit) {
        List<MethodDeclaration> mes = unit.getChildNodesByType(MethodDeclaration.class);
        Iterator<MethodDeclaration> iterator = mes.iterator();
        while (iterator.hasNext()) {
            MethodDeclaration next = iterator.next();
            if (!includeMethodName.contains(next.getNameAsString())) {
                next.remove();
            }
        }
    }

    private static void createCode(CompilationUnit unit) {
        if (unit.getTypes().isEmpty()) {
            //过滤空类
            return;
        }
        //包路径
        String packageDec = unit.getPackageDeclaration().get().getNameAsString();
        packageDec = packageDec.replace(inPackageRoot, outPackagePath);
        unit.setPackageDeclaration(packageDec);
        for (ImportDeclaration anImport : unit.getImports()) {
            if (anImport.getName().toString().contains(inPackageRoot)) {
                if (javaCodeName.contains(anImport.getName().getIdentifier()) || anImport.toString().contains("*")) {
                    anImport.setName(anImport.getName().toString().replace(inPackageRoot, outPackagePath));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(unit.toString());
        javaCode = sb.toString();
        String javaName = unit.getTypes().get(0).getName().toString();
        javaName = packageDec.replace(".", separator) + separator + javaName + ".java";
        javaName = outputPath + separator + javaName;
        sourceRoot.saveAll(Paths.get("/Users/chenxiao/IdeaProjects/xm/app-xm-core/src/main/java/fm/lizhi/app/live"));
//        System.out.println("java文件名" + javaName);
//        createFile(javaName);
    }

}
