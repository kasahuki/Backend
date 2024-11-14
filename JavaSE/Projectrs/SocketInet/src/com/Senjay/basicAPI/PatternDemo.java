package com.Senjay.basicAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("adsf");
        Matcher m = p.matcher("adsf45sadsfd");
        while (m.find()) {
//            System.out.println(m.group(0));表示匹配内容
            // 里头参数 表示捕获组 0 就表示全部捕获 i：第i个捕获组
            System.out.println(m.start() + " " + m.end());
        }
    }
}
