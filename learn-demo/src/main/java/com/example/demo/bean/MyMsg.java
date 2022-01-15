package com.example.demo.bean;

import lombok.Data;

/**
 * Created in 2018-02-06 15:28.
 *
 * @author chenxiao
 */
@Data
public class MyMsg {

    private User fromUser;


    @Data
    class User{
        private long id;
        private String name;
    }
}
