package com.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Chen Xiao
 * @since 2021-04-27 19:59
 */
public class Square {
    Byte[] a = new Byte[1024 * 1024 * 1];


    public static void main(String[] args) {
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<List<Integer>> partition = Lists.partition(numbers, 5);
        for (List<Integer> integers : partition) {
            System.out.println(integers);
        }

    }
}