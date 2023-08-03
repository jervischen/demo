package com.example.demo.java8;

import java.io.File;
import java.util.function.IntFunction;

/**
 * @author Chen Xiao
 * @since 2019-10-08 18:15
 */
public class Test {
    public static void main(String[] args) {
//        Collections.sort();
        new File(".").listFiles(File::isHidden);
        IntFunction<Integer> integerIntFunction = (int x) -> x + 1;
        Integer apply = integerIntFunction.apply(2);
        System.out.println(apply);


//        long i = 2<< 32;
//        System.out.println(i);
//        System.out.println(i/(1024));

        int i=1234;
        long j = 1234L;
        int k = 12345;
        System.out.println(i==j);
        System.out.println(k>j);
        long b = a();
        System.out.println(b);
    }


    public static int a(){
        return 11;
    }
}
