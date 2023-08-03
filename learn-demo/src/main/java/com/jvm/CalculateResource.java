package com.jvm;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

/**
 * @author Chen Xiao
 * @since 2021-04-01 14:19
 */
public class CalculateResource {
    private static final String DEFAULT_DELIMITER = ":";



    /**
     *
     */
    @Test
    public void k8s() {
        String json = "[{\"usage\":3,\"count\":8,\"serviceName\":\"lz_pp_rank\"},{\"usage\":38,\"count\":8,\"serviceName\":\"lz_pp_user_group\"},{\"usage\":1,\"count\":8,\"serviceName\":\"lz_pp_operating_activities\"},{\"usage\":23,\"count\":4,\"serviceName\":\"lz_pp_review\"},{\"usage\":29,\"count\":8,\"serviceName\":\"lz_pp_studio\"},{\"usage\":19,\"count\":16,\"serviceName\":\"lz_pp_security\"},{\"usage\":9,\"count\":16,\"serviceName\":\"app_pp_social\"},{\"usage\":4,\"count\":8,\"serviceName\":\"web_pp_operating_assistant\"},{\"usage\":24,\"count\":16,\"serviceName\":\"app_pp_common\"},{\"usage\":22,\"count\":48,\"serviceName\":\"lz_pp_amusement_task\"},{\"usage\":17,\"count\":8,\"serviceName\":\"lz_pp_play_game\"},{\"usage\":73,\"count\":16,\"serviceName\":\"pp_web_lizhinj\"},{\"usage\":37,\"count\":16,\"serviceName\":\"web_pp_h5\"},{\"usage\":45,\"count\":6,\"serviceName\":\"lz_pp_social\"},{\"usage\":6,\"count\":8,\"serviceName\":\"lz_pp_common_amusement\"},{\"usage\":20,\"count\":12,\"serviceName\":\"lz_pp_content\"},{\"usage\":49,\"count\":24,\"serviceName\":\"lz_pp_amusement\"},{\"usage\":12,\"count\":8,\"serviceName\":\"lz_pp_gift_wall\"},{\"usage\":1,\"count\":2,\"serviceName\":\"web_pp_payment_management\"},{\"usage\":26,\"count\":8,\"serviceName\":\"web_pp_family\"},{\"usage\":15,\"count\":8,\"serviceName\":\"lz_pp_stay_time\"},{\"usage\":2,\"count\":16,\"serviceName\":\"web_pp_amusement_task\"},{\"usage\":15,\"count\":8,\"serviceName\":\"web_pp_nj\"},{\"usage\":17,\"count\":20,\"serviceName\":\"app_pp_assets\"},{\"usage\":46,\"count\":24,\"serviceName\":\"app_pp_live\"},{\"usage\":2,\"count\":8,\"serviceName\":\"lz_pp_trade\"},{\"usage\":64,\"count\":2,\"serviceName\":\"web_pp_manager\"},{\"usage\":1,\"count\":8,\"serviceName\":\"lz_pp_middle_user_relation\"},{\"usage\":4,\"count\":8,\"serviceName\":\"lz_pp_oss\"},{\"usage\":10,\"count\":8,\"serviceName\":\"lz_pp_lottery\"},{\"usage\":10,\"count\":16,\"serviceName\":\"app_pp_content\"},{\"usage\":14,\"count\":8,\"serviceName\":\"lz_pp_call\"},{\"usage\":3,\"count\":4,\"serviceName\":\"web_pp_common_gems\"},{\"usage\":1,\"count\":8,\"serviceName\":\"lz_pp_trend\"},{\"usage\":1,\"count\":4,\"serviceName\":\"web_pp_common_groot\"},{\"usage\":5,\"count\":24,\"serviceName\":\"lz_pp_gift\"},{\"usage\":8,\"count\":8,\"serviceName\":\"lz_pp_cover\"},{\"usage\":2,\"count\":8,\"serviceName\":\"lz_pp_timed_task\"},{\"usage\":11,\"count\":8,\"serviceName\":\"lz_pp_gifteffect\"},{\"usage\":25,\"count\":12,\"serviceName\":\"lz_pp_comment\"},{\"usage\":40,\"count\":28,\"serviceName\":\"lz_pp_user_account\"},{\"usage\":18,\"count\":24,\"serviceName\":\"lz_pp_activity\"},{\"usage\":1,\"count\":8,\"serviceName\":\"lz_pp_command\"},{\"usage\":24,\"count\":4,\"serviceName\":\"lz_pp_family\"},{\"usage\":12,\"count\":8,\"serviceName\":\"lz_pp_prop\"},{\"usage\":1,\"count\":4,\"serviceName\":\"web_pp_rec_platformadmin\"},{\"usage\":15,\"count\":12,\"serviceName\":\"lz_pp_chat\"},{\"usage\":1,\"count\":8,\"serviceName\":\"web_pp_amusement_manager\"},{\"usage\":14,\"count\":8,\"serviceName\":\"lz_live_pp_activity\"},{\"usage\":34,\"count\":16,\"serviceName\":\"app_pp_account\"},{\"usage\":5,\"count\":8,\"serviceName\":\"lz_pp_revenue\"},{\"usage\":1,\"count\":8,\"serviceName\":\"web_pp_amusements_activity\"},{\"usage\":5,\"count\":8,\"serviceName\":\"web_pp_activities\"},{\"usage\":46,\"count\":24,\"serviceName\":\"lz_pp_pp_core\"},{\"usage\":51,\"count\":8,\"serviceName\":\"lz_pp_vip\"},{\"usage\":20,\"count\":12,\"serviceName\":\"lz_pp_wealth\"},{\"usage\":30,\"count\":8,\"serviceName\":\"lz_pp_enternotice\"},{\"usage\":4,\"count\":20,\"serviceName\":\"app_pp_activity\"},{\"usage\":26,\"count\":8,\"serviceName\":\"lz_pp_push\"},{\"usage\":9,\"count\":12,\"serviceName\":\"lz_pp_popularity\"},{\"usage\":39,\"count\":8,\"serviceName\":\"lz_pp_account\"},{\"usage\":9,\"count\":8,\"serviceName\":\"lz_pp_pp_common\"},{\"usage\":1,\"count\":8,\"serviceName\":\"lz_pp_scene\"},{\"usage\":17,\"count\":16,\"serviceName\":\"lz_pp_data\"},{\"usage\":17,\"count\":24,\"serviceName\":\"pp_rec_recommend_platform\"},{\"usage\":2,\"count\":8,\"serviceName\":\"lz_pp_user_verify\"},{\"usage\":3,\"count\":2,\"serviceName\":\"web_pp_activity_management\"},{\"usage\":2,\"count\":8,\"serviceName\":\"web_pp_ops\"},{\"usage\":79,\"count\":16,\"serviceName\":\"lz_pp_room\"}]";
        List<JSONObject> jsonObjects = JSONObject.parseArray(json, JSONObject.class);
        int use5 = 0;
        int use10 = 0;
        int use70 = 0;
        int use100 = 0;
        int sum = 0;
        for (JSONObject jsonObject : jsonObjects) {
            if (jsonObject.getInteger("usage") < 5) {
                use5 = jsonObject.getInteger("count") + use5;
            } else if (jsonObject.getInteger("usage") >= 5 && jsonObject.getInteger("usage") < 10) {
                use10 = jsonObject.getInteger("count") + use10;
            } else if (jsonObject.getInteger("usage") >= 10 && jsonObject.getInteger("usage") < 70) {
                use70 = jsonObject.getInteger("count") + use70;
            } else if (jsonObject.getInteger("usage") >= 70) {
                use100 = jsonObject.getInteger("count") + use100;
            }
            sum = sum + jsonObject.getInteger("count");
        }

        System.out.println("use5 =" + use5);
        System.out.println("use10 =" + use10);
        System.out.println("use70 =" + use70);
        System.out.println("use100 =" + use100);
        System.out.println("useSum =" + sum);
    }

    @Test
    public void mysql() {
        String json = "[{\"storage\":\"192.168.197.84:3344\",\"count\":0},{\"storage\":\"192.168.197.82:3323\",\"count\":0},{\"storage\":\"192.168.197.79:3362\",\"count\":0},{\"storage\":\"192.168.197.86:3313\",\"count\":0},{\"storage\":\"192.168.197.79:3343\",\"count\":0},{\"storage\":\"192.168.197.81:3353\",\"count\":0},{\"storage\":\"192.168.197.82:3317\",\"count\":1},{\"storage\":\"192.168.197.84:3363\",\"count\":2},{\"storage\":\"192.168.197.85:3316\",\"count\":2},{\"storage\":\"192.168.197.77:3345\",\"count\":2},{\"storage\":\"192.168.197.81:3345\",\"count\":3},{\"storage\":\"192.168.197.76:3363\",\"count\":6},{\"storage\":\"192.168.197.76:3006\",\"count\":22},{\"storage\":\"192.168.197.83:3312\",\"count\":29},{\"storage\":\"192.168.197.75:3367\",\"count\":41},{\"storage\":\"192.168.197.75:3020\",\"count\":44},{\"storage\":\"192.168.197.85:3307\",\"count\":49}]";
        List<JSONObject> jsonObjects = JSONObject.parseArray(json, JSONObject.class);
        int use5 = 0;
        int use10 = 0;
        int use70 = 0;
        int use100 = 0;
        int sum = 0;
        for (JSONObject jsonObject : jsonObjects) {
            if (jsonObject.getInteger("count") < 5) {
                use5 = 1 + use5;
            } else if (jsonObject.getInteger("count") >= 5 && jsonObject.getInteger("count") < 10) {
                use10 = 1 + use10;
            } else if (jsonObject.getInteger("count") >= 10 && jsonObject.getInteger("count") < 70) {
                use70 = 1 + use70;
            } else if (jsonObject.getInteger("count") >= 70) {
                use100 = 1 + use100;
            }
            sum = sum + 1;
        }

        System.out.println("use5 =" + use5);
        System.out.println("use10 =" + use10);
        System.out.println("use70 =" + use70);
        System.out.println("use100 =" + use100);
        System.out.println("useSum =" + sum);
    }

    @Test
    public void redis() {
        String json = "[{\"storage\":\"192.168.197.88:6097\",\"usage\":29,\"mem\":1},{\"storage\":\"192.168.197.128:6007\",\"usage\":57,\"mem\":1},{\"storage\":\"192.168.197.128:6055\",\"usage\":136,\"mem\":32},{\"storage\":\"192.168.197.128:6356\",\"usage\":87,\"mem\":2},{\"storage\":\"192.168.197.128:6470\",\"usage\":13,\"mem\":1},{\"storage\":\"192.168.197.92:6423\",\"usage\":14,\"mem\":1},{\"storage\":\"192.168.197.87:6021\",\"usage\":63,\"mem\":2},{\"storage\":\"192.168.197.90:6181\",\"usage\":58,\"mem\":1},{\"storage\":\"192.168.197.87:6005\",\"usage\":69,\"mem\":16},{\"storage\":\"192.168.197.89:6118\",\"usage\":63,\"mem\":2},{\"storage\":\"192.168.178.140:6331\",\"usage\":20,\"mem\":1},{\"storage\":\"192.168.178.132:6222\",\"usage\":48,\"mem\":1},{\"storage\":\"192.168.100.229:6220\",\"usage\":28,\"mem\":1},{\"storage\":\"192.168.197.128:6395\",\"usage\":14,\"mem\":1},{\"storage\":\"192.168.197.89:6141\",\"usage\":22,\"mem\":1},{\"storage\":\"192.168.178.136:6056\",\"usage\":72,\"mem\":4},{\"storage\":\"192.168.197.89:6140\",\"usage\":77,\"mem\":16},{\"storage\":\"192.168.197.88:6098\",\"usage\":56,\"mem\":32},{\"storage\":\"192.168.197.90:6175\",\"usage\":98,\"mem\":8},{\"storage\":\"192.168.197.92:6434\",\"usage\":14,\"mem\":1},{\"storage\":\"192.168.197.89:6129\",\"usage\":113,\"mem\":8},{\"storage\":\"192.168.197.128:6015\",\"usage\":61,\"mem\":4},{\"storage\":\"192.168.197.128:6104\",\"usage\":30,\"mem\":1},{\"storage\":\"192.168.197.89:6135\",\"usage\":69,\"mem\":4},{\"storage\":\"192.168.197.90:6902\",\"usage\":13,\"mem\":1},{\"storage\":\"192.168.197.90:6901\",\"usage\":58,\"mem\":32},{\"storage\":\"192.168.178.136:6033\",\"usage\":30,\"mem\":4},{\"storage\":\"192.168.197.128:6363\",\"usage\":73,\"mem\":32},{\"storage\":\"192.168.197.128:6257\",\"usage\":16,\"mem\":1},{\"storage\":\"192.168.197.90:6371\",\"usage\":22,\"mem\":1},{\"storage\":\"192.168.197.89:6101\",\"usage\":96,\"mem\":8},{\"storage\":\"192.168.197.89:6136\",\"usage\":89,\"mem\":4}]";
        List<JSONObject> jsonObjects = JSONObject.parseArray(json, JSONObject.class);
        int use5 = 0;
        int use10 = 0;
        int use70 = 0;
        int use100 = 0;
        int sum = 0;
        for (JSONObject jsonObject : jsonObjects) {
            if (jsonObject.getInteger("usage") < 5) {
                use5 = jsonObject.getInteger("mem") + use5;
            } else if (jsonObject.getInteger("usage") >= 5 && jsonObject.getInteger("usage") < 10) {
                use10 = jsonObject.getInteger("mem") + use10;
            } else if (jsonObject.getInteger("usage") >= 10 && jsonObject.getInteger("usage") < 70) {
                use70 = jsonObject.getInteger("mem") + use70;
            } else if (jsonObject.getInteger("usage") >= 70) {
                use100 = jsonObject.getInteger("mem") + use100;
            }
            sum = sum + jsonObject.getInteger("mem");
        }

        System.out.println("use5 =" + use5);
        System.out.println("use10 =" + use10);
        System.out.println("use70 =" + use70);
        System.out.println("use100 =" + use100);
        System.out.println("useSum =" + sum);
    }
}
