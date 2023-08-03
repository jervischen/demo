package robot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FSrobot {


    private static final Map<String,String> map = new HashMap<String,String>(){
        {
            put("陈潇", "ou_667cc51f7231b8d8d3792e90e6649cf9");
        }
    };

    public static void main(String[] args) throws Exception {
//        String a = "陈潇";
//        String[] split = a.split("[^\\u4E00-\\u9FFF]");
//        System.out.println(split.length);
//        System.out.println(split[0]);
//        System.out.println(split[1]);

//        sendDuty();
        getDutyList();
    }


    public static void findCell(String date){
        String url =  "https://open.feishu.cn/open-apis/sheets/v3/spreadsheets/LJ8as8pqwhwrkJtG77lcp9qenAu/sheets/MaeSIq/find";
        Map<String,Object> param = new HashMap<>();
        param.put("find","06");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("range","MaeSIq!A4:A40");
        param.put("find_condition",jsonObject.toString());

        String res = HttpClientUtil.doPostJson(url, param);
        if (StringUtils.isBlank(res)) {
            return;
        }
    }


    /**
     * 获取值班列表
     */
    private static String getDutyList() {
//        String url = "https://open.feishu.cn/open-apis/sheets/v2/spreadsheets/LJ8as8pqwhwrkJtG77lcp9qenAu/values/MaeSIq!B5:E5?dateTimeRenderOption=FormattedString";
//        String res = HttpClientUtil.doGet(url);
//        JSONObject jsonObject = JSONObject.parseObject(res);
//        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("valueRange").getJSONArray("values");
//        if (jsonArray.isEmpty()) {
//            return "";
//        }
//        JSONArray jsonArray1 = jsonArray.getJSONArray(0);
        StringBuilder sb = new StringBuilder();
        sb.append("(测试数据请无视)本周值班人员，辛苦各位为服务的稳定保驾护航！！！\n");
//        for (Object obj : jsonArray1) {
//            sb.append("@").append(obj.toString());
//        }
        String name = map.get("陈潇");
        sb.append(String.format("<at user_id=\"%s\">陈潇</at>",null));
        sb.append("<at user_id=\"all\">所有人</at>\n");
        sb.append("如果有问题请@以上值班人员，谢谢配合~~~");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 发送值班消息
     */
    private static void sendDuty() {
        String url = "https://open.feishu.cn/open-apis/im/v1/messages?receive_id_type=chat_id";

        Map<String, Object> param = new HashMap<>();
        param.put("receive_id", "oc_4d710c3cfab934a0c86ae447bd38b6b8");
        param.put("msg_type", "text");
        param.put("uuid", UUID.randomUUID());

        JSONObject content = new JSONObject();
        content.put("text", getDutyList());
        param.put("content", content.toString());


        String res = HttpClientUtil.doPostJson(url, param);

        if (StringUtils.isBlank(res)) {
            return;
        }

        JSONObject jsonObject = JSONObject.parseObject(res);
        if (jsonObject.getInteger("code") == 0) {
            String messageId = jsonObject.getJSONObject("data").getString("message_id");
//            makeTop(messageId);
        }

    }

    /**
     * 置顶值班消息
     */
    private static void makeTop(String messageId) {
        String url = "https://open.feishu.cn/open-apis/im/v1/chats/oc_4d710c3cfab934a0c86ae447bd38b6b8/top_notice/put_top_notice";
        Map<String, Object> param = new HashMap<>();

        JSONArray array = new JSONArray();
        JSONObject topNotice = new JSONObject();
        topNotice.put("action_type", "1");
        topNotice.put("message_id", messageId);
        array.add(topNotice);

        param.put("chat_top_notice", array);

        String res = HttpClientUtil.doPostJson(url, param);

    }


}
