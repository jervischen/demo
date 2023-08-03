package com.chat;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.rong.RongCloud;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author lujiabao
 * @since 2020-10-05
 */
public class RongScript {

    //    官方 5012919957700036140
    //    审核 5014458161688511020
    public static final String AUDIT_OFFICIAL = "5012919957700036140";
    static HttpClient cli = new DefaultHttpClient();
    static Random rand = new Random(42);
    static String appSecret = "fAbJYa6V4V8XR";
    static String appKey = "6tnym1br6p8b7";

    static RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    static int lineI = 0;

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/chenxiao/Downloads/2022-05-16-18");
        String[] lines = IOUtils.readLines(file);
        System.out.println("length:" + lines.length);

        Set<String> sentSet = new HashSet<>();
        for (String line : lines) {
            JSONObject jsonObj = JSON.parseObject(line.substring(19));
            String content = jsonObj.getJSONObject("content").getString("content");
            String targetId = jsonObj.getString("targetId");
            String fromUserId = jsonObj.getString("fromUserId");
//            fromUserId.equals("5012919957700036140")
            boolean isApply = content != null && content.contains("星选营地-好友召回任务");
            if (!isApply) {
                continue;
            }
            System.out.println(line);
            break;
            //是需要撤回的消息
//            System.out.println(lineI++);
//            String key = targetId + content;
//            if (sentSet.contains(key)) {
//                continue;
//            }
//            //撤回
//            String msgUID = jsonObj.getString("msgUID");
//            String msgSentTime = jsonObj.getString("timestamp");
//            //kv
//            StringBuilder sb = new StringBuilder();
//            sb.append("&conversationType=").append(URLEncoder.encode("1", "UTF-8"));
//            sb.append("&fromUserId=").append(URLEncoder.encode(AUDIT_OFFICIAL, "UTF-8"));
//            sb.append("&targetId=").append(URLEncoder.encode(targetId, "UTF-8"));
//            sb.append("&messageUID=").append(URLEncoder.encode(msgUID, "UTF-8"));
//            sb.append("&sentTime=").append(URLEncoder.encode(msgSentTime, "UTF-8"));
//            // 是否提示消息已撤回
//            sb.append("&isAdmin=").append("0");
//            sb.append("&isDelete=").append("1");
//            String body = sb.toString();
//            if (body.indexOf("&") == 0) {
//                body = body.substring(1, body.length());
//            }
//            HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/message/recall.json", "application/x-www-form-urlencoded");
//            HttpUtil.setBodyParameter(body, conn);
//            String s = HttpUtil.returnResult(conn);
//            System.out.println(s);
//            sentSet.add(key);
        }
    }
}
