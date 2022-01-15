package com.example.demo.lbean;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.lbean.pk.NextPkInfo;
import com.example.demo.lbean.pk.PkInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created in 2018-04-13 17:10.
 *
 * @author chenxiao
 */
public class LTestJson {
    private static Logger logger = LoggerFactory.getLogger(LTestJson.class);

    @Test
    public void testNextPk(){
        Map<String, Object> data = new HashMap<>();
        data.put("currentNjStatus", 111);
        data.put("nextPkInfo", new NextPkInfo());

        System.out.println(JSONObject.toJSON(data));
    }

    @Test
    public void testPkInfoList(){
         List<PkInfo> list = new ArrayList<>();
         list.add(new PkInfo());
        System.out.println(JSONObject.toJSON(list));
    }
}
