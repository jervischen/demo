package com.pattern.factory;

/**
 * 顾客
 */
public class Customer {

    public static void main(String[] args) {

        FruitsFactory fruitsFactory = new FruitsFactory();
        System.out.println("我要苹果");
        String apple = fruitsFactory.getHandler("apple").create();
        System.out.println(apple);

        System.out.println("我要橙子");
        String orange = fruitsFactory.getHandler("orange").create();
        System.out.println(orange);


        System.out.println("我要xxx水果");

    }
}
