package loder;

import org.junit.Test;

import java.util.HashMap;

public class TestMyClassLoader {
    public static void main(String[] args) throws Exception
    {
//        MyClassLoader mcl = new MyClassLoader("/Users/chenxiao/IdeaProjects/demo/learn-demo/src/main/java/loder");
////        Thread.currentThread().setContextClassLoader(mcl);
////        Class<?> c1 = Class.forName("loder.Person", true, mcl);
//
//        Class<?> c1 = mcl.loadClass("Person");
//        Object obj = c1.newInstance();
//        System.out.println(obj);
//        System.out.println(obj.getClass().getClassLoader());

        ThreadLocal<String> a = new ThreadLocal<>();
        a.set("a");

        System.out.println(11);

       new ThreadLocal<>().set("b");
        System.out.println(22);

        HashMap<Object, Object> map = new HashMap<>();

        new Integer(11);
    }

    @Test
    public void a(){
        String a="123,44565,666-77-99好好55";
        String[] split = a.split("[^0-9]");

        for (String s : split) {
            System.out.println(s);
        }
    }
}
