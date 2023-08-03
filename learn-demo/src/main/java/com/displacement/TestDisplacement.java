package com.displacement;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Chen Xiao
 * @since 2021-10-26 17:37
 */
public class TestDisplacement {


    public static void main(String[] args) {
//        System.out.println(String.format("左移是乘法 = %d", 1 << 63));
//
//
//        long l = Long.MAX_VALUE;
//
//        System.out.println(l);
//
//
//        Long a = 222L;
//
//        System.out.println(a.doubleValue() + 1111);
//
//        System.out.println(Math.log(11));
//
//
//        long time = DateUtil.formatStrToNormalDate("2021-10-01 00:00:00").getTime();
//        long t = System.currentTimeMillis() - time;
//        System.out.println("t=" + String.valueOf(t));
//        String format = String.format("%d.%d", 99999, t);
//        System.out.println("format");
//
//        System.out.println(Double.valueOf(format));
//
//        String num = "123";
//        boolean numeric = StringUtils.isNumeric(num);
//        System.out.println(numeric);
//
//        System.out.println(Integer.parseInt(num));
        a();
    }


    public static void a(){
        String js = "{\"propId\":\"5142263375304161846\",\"count\":0.9}";
        JSONObject jsonObject = JSONObject.parseObject(js);
        String count = jsonObject.getString("count");
        System.out.println("0.9是否为数字：" + StringUtils.isNumeric(count));

        js = "{\"propId\":\"5142263375304161846\",\"count\":-1}";
        jsonObject = JSONObject.parseObject(js);
        count = jsonObject.getString("count");
        System.out.println("-1是否为数字：" + StringUtils.isNumeric(count));

        js = "{\"propId\":\"5142263375304161846\",\"count\":1}";
        jsonObject = JSONObject.parseObject(js);
        count = jsonObject.getString("count");
        System.out.println("1是否为数字：" + StringUtils.isNumeric(count));
    }
}
