package com.example.mock;

/**
 * @author Chen Xiao
 * @since 2021-05-23 13:51
 */
public class Person {

    private final int id;
    private final String name;
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
