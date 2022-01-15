package com.example.demo.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Xiao
 * @since 2020-02-05 19:40
 */
@Data
public class Menu {
    private  int type;

    private boolean open;

    private String  whiteList ="";

    private Content content = new Content();


    private List<String> list = new ArrayList(){{add("a");add("b");}};
}
