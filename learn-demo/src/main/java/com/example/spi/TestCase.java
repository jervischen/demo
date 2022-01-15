package com.example.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Chen Xiao
 * @since 2021-04-25 15:48
 */
public class TestCase {
    public static void main(String[] args) {
//        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
//        Iterator<Search> iterator = s.iterator();
//        while (iterator.hasNext()) {
//            Search search =  iterator.next();
//            search.searchDoc("hello world");
//        }


        String s = "pp_topic_core_date_play_create_order    ";

        System.out.println(s.trim());
    }
}
