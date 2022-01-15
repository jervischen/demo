package com.jvm;

import com.google.common.collect.Lists;

/**
 * @author Chen Xiao
 * @since 2021-04-01 14:19
 */
public class TryCatch {
    public static void main(String[] args) {

        System.out.println(Math.max(5170818422446787711L, 5170806495383213183L));

        System.out.println(Lists.newArrayList().isEmpty());



        String[] ss = new String[]{};
        System.out.println(ss.getClass().isArray());

        System.out.println(Lists.newArrayList().getClass().isArray());

    }

}
