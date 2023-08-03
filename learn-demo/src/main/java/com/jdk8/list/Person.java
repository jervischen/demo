package com.jdk8.list;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

    private int id;

    private String name;
}
