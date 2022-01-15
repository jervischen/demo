package com.example.demo.lombok;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Synchronized;

/**
 * @author Chen Xiao
 * @since 2020-03-17 10:41
 */
public class LearnLombok {

    private  String val ="abc";

    public static void main(String[] args) {
        LearnLombok learnLombok = new LearnLombok();
        learnLombok.testNull("");
    }


    @SneakyThrows
    @Synchronized
    public void testNull(@NonNull String a){
        try {
            String v  ="bcd";
            System.out.println(1/0);
            System.out.println(a);
        }catch (Exception e){
            throw  e;
        }
        System.out.println(111);
    }
}
