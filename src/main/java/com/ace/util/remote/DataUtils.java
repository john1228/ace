package com.ace.util.remote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 * @date 19-6-17 下午12:46
 */
@Log4j2
public class DataUtils {
    private static final String orgHost = "http://passport.baobanwang.com/getOrgListByPIdTest";
    private static final String empHost = "http://passport.baobanwang.com/getEmpListByOIdTest";

    public static List<Data> orgList(String projectId) {
        List<Data> dataList = new ArrayList<>();
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(orgHost);
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("data", String.format("{\"projectId\":\"%s\"}", projectId)));
        try {
            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            StringBuffer orgInfo = new StringBuffer();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                orgInfo.append(inputLine);
            }
            JSONObject jsonObject = new JSONObject(orgInfo.toString());
            if (jsonObject.getString("code").equals("000000000")) {
                JSONArray data = jsonObject.getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.getJSONObject(i);
                    dataList.add(new Data(obj.getString("orgId"), obj.getString("orgName")));
                }
            } else {
                throw new Exception(jsonObject.getString("message"));
            }
        } catch (Exception exp) {
            log.info("获取组织信息失败:" + exp.getMessage());
        }
        return dataList;
    }

    public static List<Data> employee(String orgId) {
        List<Data> dataList = new ArrayList<>();
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(empHost);
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("data", String.format("{\"orgId\":\"%s\"}", orgId)));
        try {
            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            StringBuffer orgInfo = new StringBuffer();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                orgInfo.append(inputLine);
            }
            JSONObject jsonObject = new JSONObject(orgInfo.toString());
            if (jsonObject.getString("code").equals("000000000")) {
                JSONArray data = jsonObject.getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.getJSONObject(i);
                    dataList.add(new Data(obj.getString("empId"), obj.getString("empName")));
                }
            } else {
                throw new Exception(jsonObject.getString("message"));
            }
        } catch (Exception exp) {
            log.info("获取组织信息失败:" + exp.getMessage());
        }
        return dataList;
    }
}
