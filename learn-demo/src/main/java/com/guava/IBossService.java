package com.guava;

/**
 * @author Chen Xiao
 * @since 2021-10-12 17:20
 */
@FunctionalInterface
public interface IBossService {

    default String m1(){
        return "m1";
    }

    static String m2(){
        return "m2";
    }


    void m3();

}
