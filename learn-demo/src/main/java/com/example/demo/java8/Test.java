package com.example.demo.java8;

import java.io.File;
import java.util.Collections;
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
    }
}
