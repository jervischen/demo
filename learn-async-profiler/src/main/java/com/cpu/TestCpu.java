package com.cpu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author Chen Xiao
 * @since 2021-09-27 19:47
 */
public class TestCpu {


    public static void main(String[] args) throws InterruptedException {

        JSONObject jsonObject = JSONObject.parseObject("{\"courseCode\":\"FINALS\",\"awardType\":\"EFFECTS\",\"awardIds\":[\"5216851450319078451\"]}");
        String awardType = jsonObject.getString("awardType");
        JSONArray awardIds = jsonObject.getJSONArray("awardIds");

       aa();
    }


    private static void aa(){
        ArrayList<Integer> integers = Lists.newArrayList(1, 2);
        System.out.println(integers.contains(1));
    }

    public void a() throws InterruptedException {
        while (true) {
            b();
            c();
        }
    }


    public void b() throws InterruptedException {
        Thread.sleep(500);
    }

    public void c() throws InterruptedException {
        Thread.sleep(1000);
    }
}
