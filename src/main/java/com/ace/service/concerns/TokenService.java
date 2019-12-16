package com.ace.service.concerns;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 * @date 19-4-25 下午7:17
 */
@Service("api-token-service")
@Log4j2
public class TokenService {
    private static final String host = "http://passport.baobanwang.com/login/getAccountData";

    public Account account(String token) {
        log.info("验证登录");
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(host);
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("token", token));
        try {
            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            StringBuffer accountInfo = new StringBuffer();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                accountInfo.append(inputLine);
            }
            log.info(accountInfo.toString());
            JSONObject jsonObject = new JSONObject(accountInfo.toString());
            if (jsonObject.getString("code").equals("000000000")) {
                JSONObject actObj = jsonObject.getJSONObject("data");
                Account account = new Account(actObj.getString("accountId"), actObj.getString("accountName"));
                account.setToken(token);
                boolean isAdmin = actObj.getInt("accountRoles") == 7;
                account.setAdmin(isAdmin);
                if (!isAdmin) {
                    JSONArray empList = actObj.getJSONArray("employee");
                    List<Staff> staffList = new ArrayList<>();
                    for (int i = 0; i < empList.length(); i++) {
                        JSONObject empObj = empList.getJSONObject(i);
                        staffList.add(new Staff(
                                account,
                                empObj.getString("projectId"),
                                empObj.getString("projectName"),
                                empObj.getString("orgId"),
                                empObj.getString("orgName"),
                                empObj.getString("empId"),
                                empObj.getString("empName")
                        ));
                    }
                    account.setStaffList(staffList);
                }
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
