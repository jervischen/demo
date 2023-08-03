package com.util;

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

public class CreateTopicApply {

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
        headers.put("X-Token", "45cb7054672043bd9f84390043e3bf36");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36");
        headers.put("X-Requested-With", "XMLHttpRequest");
        return headers;
    }

    public static Map<String, Object> buildBodyParam() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("business_name", "39-ximi(互动娱乐中心/水晶球计划)");
        param.put("desc", "app-xm-live");
        param.put("domain", "");
        param.put("internet", "否");
        param.put("isnat", "");
        param.put("itemdesc", "直播AS");
        param.put("itemname", "app_xm_live");
        param.put("jdk", "java1.8");
        param.put("onlinetime", 1650470400000L);
        param.put("onlinetype", "预发&线上");
        param.put("packway", "maven");
        param.put("port", 0);
        param.put("project_level", "二级重要服务");
        param.put("qps", "");
        param.put("repositories", "git@gitlab.lizhi.fm:xm/app-xm-live.git");
        param.put("servicetype", "中国小西米");
        param.put("ticket_tmpl_id", 36);
        param.put("url", "https://lzstack.lizhi.fm/doc/%E8%BF%90%E7%BB%B4/%E8%BF%90%E7%BB%B4_%E9%87%8A%E8%BF%A6_%E5%B9%B3%E5%8F%B0%E4%BD%BF%E7%94%A8%E6%96%87%E6%A1%A3/%E5%8F%91%E5%B8%83%E5%B9%B3%E5%8F%B0%E4%BB%8B%E7%BB%8D/%E5%8F%91%E5%B8%83%E5%B9%B3%E5%8F%B0%E6%93%8D%E4%BD%9C%E6%95%99%E7%A8%8B.html");
        return param;
    }

    public static Map<String, Object> buildBodyParam(String projectName, String gitAddress, String projectLevel, String itemdesc, String internet, String domain, int port) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("business_name", "39-ximi(互动娱乐中心/水晶球计划)");
        param.put("desc", projectName);
        param.put("domain", domain);
        param.put("internet", internet);
        param.put("isnat", "");
        param.put("itemdesc", itemdesc);
        param.put("itemname", projectName.replaceAll("-", "_"));
        param.put("jdk", "java1.8");
        param.put("onlinetime", 1650470400000L);
        param.put("onlinetype", "预发&线上");
        param.put("packway", "maven");
        param.put("port", port);
        param.put("project_level", projectLevel);
        param.put("qps", "");
        param.put("repositories", gitAddress);
        param.put("servicetype", "中国小西米");
        param.put("ticket_tmpl_id", 36);
        param.put("url", "https://lzstack.lizhi.fm/doc/%E8%BF%90%E7%BB%B4/%E8%BF%90%E7%BB%B4_%E9%87%8A%E8%BF%A6_%E5%B9%B3%E5%8F%B0%E4%BD%BF%E7%94%A8%E6%96%87%E6%A1%A3/%E5%8F%91%E5%B8%83%E5%B9%B3%E5%8F%B0%E4%BB%8B%E7%BB%8D/%E5%8F%91%E5%B8%83%E5%B9%B3%E5%8F%B0%E6%93%8D%E4%BD%9C%E6%95%99%E7%A8%8B.html");
        return param;
    }

    public static void main(String[] args) {
        FileInputStream fileInput = null;//创建文件输入流
        XSSFWorkbook wb = null;//由输入流文件得到工作簿对象
        try {
            fileInput = new FileInputStream("C:\\createOnLineProject.xlsx");
            wb = new XSSFWorkbook(fileInput);
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数
            int rowBegin = 1; //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
            for (int i = rowBegin; i <= lastRowNum; ++i) {
                XSSFRow row = sheet.getRow(i);//获取每一行
                String projectName = row.getCell(0).getStringCellValue().trim();
                if(StringUtils.isEmpty(projectName)) {
                    continue;
                }
                String gitAddress = row.getCell(1).getStringCellValue().trim();
                String projectLevel = row.getCell(2).getStringCellValue().trim();
                String itemdesc = row.getCell(3).getStringCellValue().trim();
                String internet = row.getCell(4).getStringCellValue().trim();
                String domain = row.getCell(5).getStringCellValue().trim();
                int port = (int) row.getCell(6).getNumericCellValue();
                //System.out.println("index=" + i + ",projectName=" + projectName + ",gitAddress=" + gitAddress + ",projectLevel=" + projectLevel + ",itemdesc=" + itemdesc + ",internet=" + internet + ",domain=" + domain + ",port=" + port);
                Map<String, String> headers = buildHeaders();
                Map<String, Object> param = buildBodyParam(projectName, gitAddress, projectLevel, itemdesc, internet, domain, port);
                String result = sendPostByJson("http://sakya.lizhi.fm/reqmgmt/v1/tickets/", param, headers);
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



//		Map<String, String> headers = buildHeaders();
//		Map<String, Object> param = buildBodyParam();
//		String result = sendPostByJson("http://sakya.lizhi.fm/reqmgmt/v1/tickets/", param, headers);
//		System.out.println(result);
    }
}
