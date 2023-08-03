package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BatchAddIntegral {

    private static boolean isPro = true;
    private static final String PRE = isPro ? "http://amuseadmin.pparty.com" : "http://amuseadmin.yfxn.lizhi.fm/";

    public static void main(String[] args) {

        BatchAddIntegral bu = new BatchAddIntegral();
        try {
            List<String> items = Files.readLines(new File("/Users/chenxiao/IdeaProjects/demo/learn-demo/src/main/java/com/util/1219男神.txt"), Charsets.UTF_8);
            for (String item : items) {
                if (!Strings.isNullOrEmpty(item)) {
                    String[] ss = item.trim().split(",");
                    bu.addIntegral(ss[0].trim(), ss[1].trim());
                }
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean addIntegral(String uid, String score) throws Exception {
        String url = PRE + "/act_tpl/award/reissue";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        // 设置是否向HttpURLConnection输出
        con.setDoOutput(true);
        // 设置是否从httpUrlConnection读入
        con.setDoInput(true);
        // 设置是否使用缓存
        con.setUseCaches(false);
        //设置参数类型是json格式
        con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        con.setRequestProperty("Cookie", "token=c642d4600ac74ab5a2eac520adb4d422");

        con.connect();
        String body = "{\"userIds\":[\"" + uid
                + "\"],\"awards\":[{\"count\":" + score
                + ",\"awardType\":\"INTEGRAL\",\"awardId\":\"5561\",\"awardName\":\"充值积分\"}],\"activityId\":\"5251920289870627455\"}";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
        System.out.println(body);
        writer.write(body);
        writer.close();
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String resp = response.toString();
        if (responseCode != 200 || Strings.isNullOrEmpty(resp)) {
            System.out.println("failed!  uid=" + uid + " score=" + score + " code=" + responseCode);
            return false;
        } else {
            JSONObject ret = JSON.parseObject(resp);
            int rCode = ret.getIntValue("rCode");
            if (rCode != 0) {
                System.out.println("failed!  uid=" + uid + " score=" + score + " rCode=" + rCode + " msg=" + ret.getString("msg"));
                return false;
            }
            System.out.println("success! uid=" + uid + " score=" + score);
        }
        return true;
    }

}
