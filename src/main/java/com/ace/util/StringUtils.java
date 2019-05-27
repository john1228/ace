package com.ace.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.*;

/**
 * 字符串帮助类
 *
 * @author hack
 */
public class StringUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date getStringToDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static long getLongByString(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(date);
            long time = getDateToLong(d);
            return time;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getLongToString(long time) {
        try {
            Date d = getLongToDate(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDatetToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static String getDateToSmallString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String getDateToSmallString(long time) {
        Date d = getLongToDate(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(d);
    }


    public static long getDateToLong(Date date) {
        return date.getTime();
    }

    public static Date getLongToDate(long time) {
        return new Date(time);
    }

    /**
     * 格式转换
     *
     * @param temp DatePicker 传输数据格式 [dd/MM/yyyy - dd/MM/yyyy)
     * @return beforeTime and afterTime;
     */
    public static Map<String, Long> getBetweenTime(String temp) {
        String before = temp.split("-")[0].trim();
        String after = temp.split("-")[1].trim();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("beforeTime", getLongByString(before + " 00:00:00", "MM/dd/yyyy HH:mm:ss"));
        map.put("afterTime", getLongByString(after + " 23:59:59", "MM/dd/yyyy HH:mm:ss"));
        return map;
    }

    public static long getLongByString(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date d = sdf.parse(date);
            long time = getDateToLong(d);
            return time;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        URL realUrl = new URL("https://poll.kuaidi100.com/poll/query.do");
        //打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        //设置通用的请求属性
//        conn.setRequestProperty("accept", "*/*");
//        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent",
//                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        //建立实际的连接
        conn.setDoOutput(true);
        conn.setDoInput(true);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        //发送请求参数
        out.print("customer=23649B67CD95D38114BDECF6F1887A92&param=%7B%22com%22%3A%22zhongtong%22%2C%22num%22%3A%2273111935198843%22%2C%22resultv2%22%3A0%7D&sign=B2AD1538A0E8701841835C1BAF86D2C8");
        //flush输出流的缓冲
        out.flush();
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        System.err.println(sb.toString());
    }

    public static final char UNDERLINE = '_';

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
