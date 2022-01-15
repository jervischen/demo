package com.example.demo.regular;

import com.google.common.base.Strings;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created in 2018-02-12 16:24.
 *
 * @author chenxiao
 */
public class TestRegularExpression {
    private static Logger logger = LoggerFactory.getLogger(TestRegularExpression.class);


    /**
     * 中文、数字、英文
     */
    @Test
    public void testRegularChinaAndNumAndEglish() {
        String str = "abc123哈哈!";
        String regEx = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$";

        String newStr = str.replaceAll(regEx, "");
        System.out.println(newStr);
        testReg(str,regEx);
    }

    public void testReg(String regEx,String str) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println(m.matches());
    }

    /**
     * 正则表达式，去除所有空格
     */
    @Test
    public void testPatternBlank() {
        // 要验证的字符串
        String str = "biubiu biu";
        // 邮箱验证规则
        String regEx = "\\s+";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);

        System.out.println(str.replaceAll(regEx,""));
        String s = str.trim().replaceAll("\\s+", "");
        System.out.println(s);
    }

    /**
     * 正则表达式，空格
     */
    @Test
    public void testPatternBlank2() {
        // 要验证的字符串
        String str = " ";
        // 邮箱验证规则
        String regEx = "[\\s]+";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);

        System.out.println("   ".length());
    }

    @Test
    public void testS() {
        System.out.println(StringFilter("12345678932145687一二三四"));

    }

    /**
     * 正则表达式保留：数字、英文、中文
     * @param medalName
     * @return
     */
    public String StringFilter(String medalName) {
        if (Strings.isNullOrEmpty(medalName)) {
            return null;
        }
        //勋章名字长度最长为3
        String newMedalName = "";
        String regEx = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$";
        Pattern p = Pattern.compile(regEx);
        for (char ch : medalName.toCharArray()) {
            Matcher m = p.matcher(String.valueOf(ch));
            if (m.matches()) {
                newMedalName += String.valueOf(ch);
            }

            if (newMedalName.getBytes().length >= 200) {
                break;
            }
        }

        /**
         * 没有汉字，则六个字符
         * 有一个汉字则加3个数字或字母3+3=6
         * 有两个个汉字则加3个数字或字母3+3+3=9
         * utf-8编码汉字为3个字节
         */
        int chineseCharCount = getChineseCharCount(newMedalName);
        if (chineseCharCount == 0) {
            newMedalName = newMedalName.substring(0, newMedalName.length() > 6 ? 6 : newMedalName.length());
        } else if (chineseCharCount == 1) {
            while (newMedalName.getBytes().length > 6) {
                newMedalName = newMedalName.substring(0, newMedalName.length() - 1);
            }
        } else {
            while (newMedalName.getBytes().length > 9) {
                System.out.println(newMedalName.length());
                newMedalName = newMedalName.substring(0, newMedalName.length() - 1);
            }
            System.out.println(newMedalName.length());
        }

        return newMedalName;
    }

    private static int getChineseCharCount(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
                continue;
            }
            if ((tmp >= '0') && (tmp <= '9')) {
                continue;
            }
            count++;
        }
        return count;
    }

    public static String StringFilter2(String medalName) {
        if (Strings.isNullOrEmpty(medalName)) {
            return null;
        }

        //勋章名字长度最长为3
        String newMedalName = "";
        String regEx = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$";
        Pattern p = Pattern.compile(regEx);
        for (char ch : medalName.toCharArray()) {
            Matcher m = p.matcher(String.valueOf(ch));
            if (m.matches()) {
                newMedalName += String.valueOf(ch);
            }

            if (newMedalName.length() == 3) {
                break;
            }
        }

        return newMedalName;
    }



}
