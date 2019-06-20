package com.ace.service.concerns;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class TokenService {
    Logger logger = LoggerFactory.getLogger(TokenService.class);
    private static final String host = "http://passport.baobanwang.com/login/getAccountData";

    public Account account(String token) {
        if (token.equals("john-token-123")) {
            Account account = new Account();
            account.setAccountId("001");
            account.setAccountName("001-NAME");
            account.setManager(true);
            List<Staff> staffList = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Staff staff = new Staff();
                staff.setAccountId("001");
                staff.setAccountName("001-NAME");
                staff.setProjectId("001-P-" + i);
                staff.setProjectName("001-PN-" + i);
                staff.setOrgId("001-O-" + i);
                staff.setOrgName("001-ON" + i);
                staff.setEmpId("001-E-" + i);
                staff.setEmpName("001-EM-" + i);
                staffList.add(staff);
            }
            account.setStaffList(staffList);
            return account;
        } else {
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
                logger.info(accountInfo.toString());
                JSONObject jsonObject = new JSONObject(accountInfo.toString());
                if (jsonObject.getString("code").equals("000000000")) {
                    JSONObject actObj = jsonObject.getJSONObject("data");
                    Account account = new Account(actObj.getString("accountId"), actObj.getString("accountName"));
                    account.setManager(true);
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
                        logger.info("登录用户:" + staffList.get(i).getEmpName());
                    }
                    account.setStaffList(staffList);
                    return account;
                }

                logger.info("登录成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
