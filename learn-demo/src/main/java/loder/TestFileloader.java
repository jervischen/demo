package loder;

import java.util.ServiceLoader;

public class TestFileloader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 创建自定义的类加载器
        FileReadClassLoader fileReadClassLoader = new FileReadClassLoader("/Users/chenxiao/IdeaProjects/demo/learn-demo/src/main/java/loder");
        // 加载类
        Class<?> account = fileReadClassLoader.loadClass("Person");
        Object o = account.newInstance();
        ClassLoader classLoader = o.getClass().getClassLoader();
        System.out.println("加载当前类的类加载器为：" + classLoader);
        System.out.println("父类加载器为：" + classLoader.getParent());


        Person person = new Person();
        System.out.println(person.getClass().getClassLoader());

        ServiceLoader.load(Person.class);

    }
}
