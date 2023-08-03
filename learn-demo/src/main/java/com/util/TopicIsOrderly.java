package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TopicIsOrderly {

    public static String sendPostByJson(String url, Map<String, Object> params,
                                        Map<String, String> headers) {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            if (params != null && !params.isEmpty()) {
                List<Header> tmpHeaders = new ArrayList<>();
                tmpHeaders.add(new BasicHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType()));
                if (headers != null) {
                    headers.keySet().stream().forEach(key -> {
                        Header header = new BasicHeader(key, headers.get(key));
                        tmpHeaders.add(header);
                    });
                }
                post.setHeaders(tmpHeaders.toArray(new Header[] {}));
                String json = JSONObject.toJSONString(params);
                StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
                post.setEntity(entity);
            }
            int timeout = (int) TimeUnit.MINUTES.toMillis(3);
            RequestConfig timeOutRequestConfig = RequestConfig.custom().setSocketTimeout(timeout)
                    .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
            post.setConfig(timeOutRequestConfig);
            CloseableHttpResponse response = httpclient.execute(post);
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity, StandardCharsets.UTF_8);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Cookie","token=26abfdb3cec4442a8faf9df1b0a999b2");
//        headers.put("cooke","token=26abfdb3cec4442a8faf9df1b0a999b2");
        headers.put("token","26abfdb3cec4442a8faf9df1b0a999b2");
        headers.put(":authority","mqadmcn.183me.com");
        headers.put("referer","https://mqadmcn.183me.com/");
        headers.put("x-requested-with","XMLHttpRequest");
        return headers;
    }


    public static Map<String, Object> buildBodyParam(String topic) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("region", "cn");
        param.put("businessEnv", "lizhi");
        param.put("clusterName", "");
        param.put("clusterAddress", "");
        param.put("pageNumber", 1);
        param.put("pageSize", 20);
        param.put("deployment", "cn_lizhi_PRO_lizhi");
        param.put("deployEnv", "PRO");
        param.put("onlinetime", 1650470400000L);
        param.put("label", "lizhi");
        param.put("topic", topic);
        return param;
    }

    public static void main(String[] args) {
        FileInputStream fileInput = null;//创建文件输入流
        XSSFWorkbook wb = null;//由输入流文件得到工作簿对象
        try {
            fileInput = new FileInputStream("/Users/chenxiao/IdeaProjects/demo/learn-demo/src/main/java/com/util/pp_topic.xlsx");
            wb = new XSSFWorkbook(fileInput);
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数
            int rowBegin = 0; //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
            for (int i = rowBegin; i <= lastRowNum; ++i) {
                XSSFRow row = sheet.getRow(i);//获取每一行
                String topic = row.getCell(0).getStringCellValue().trim();
                if(StringUtils.isEmpty(topic)) {
                    continue;
                }

                Map<String, String> headers = buildHeaders();
                Map<String, Object> param = buildBodyParam(topic);

                String result = HttpClientUtil.doGet("https://mqadmcn.183me.com/api/kafka/topics/", param, headers);
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject.getInteger("rCode") == 0){
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray topics = data.getJSONArray("topics");
                    for (int j = 0; j < topics.size(); j++) {
                        JSONObject topicObj = topics.getJSONObject(j);
                        if (topicObj.getString("topic").equals(topic)){
                            if (topicObj.getInteger("partitionNumber") == 1){
                                System.out.println("topic=" + topic + " 分区数量=" + topicObj.getInteger("partitionNumber"));
                            }
                        }
                    }

                }else{
                    System.out.println("错误topic=" + topic);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != wb) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileInput) {
                try {
                    fileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
