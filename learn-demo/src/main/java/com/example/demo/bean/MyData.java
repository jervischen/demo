package com.example.demo.bean;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created in 2018-02-01 19:53.
 *
 * @author chenxiao
 */
@Data
public class MyData {

    private boolean isFull;

    private boolean urlShareable=false;

    private Map<String,Integer> map;

    private Date  date;

}
