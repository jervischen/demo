package com.javap;

/**
 * @author Chen Xiao
 * @since 2021-10-16 00:22
 */
public class ThreadB {

    /**
     * abcd
     * @param args
     */
    public static void main(String[] args) {
        new Thread(()-> System.out.println(11));
    }
}
