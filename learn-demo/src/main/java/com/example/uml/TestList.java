package com.example.uml;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chen Xiao
 * @since 2021-12-15 11:45
 */
public class TestList {


    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("123 ", " 456"," 445 ");

        List<Long> collect = strings.stream().map(num -> Long.parseLong(num.trim())).collect(Collectors.toList());
        System.out.println(collect);
    }
}
