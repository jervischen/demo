package com.example.demo.bean;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created in 2018-08-21 11:30.
 *
 * @author chenxiao
 */
@Data
@Accessors(chain = true)
public class CategoryVo {


    private long id;
    /**
     * 完成次数
     */
    private int num;

    /**
     *
     */
    private int status;


    private long startTime;


    private long endTime;

    public static void main(String[] args) {
        JSONObject j= new JSONObject();
        String s = JSONObject.toJSONString(null);
        System.out.println(s);
    }
}
