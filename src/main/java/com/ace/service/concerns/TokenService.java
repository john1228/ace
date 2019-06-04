package com.ace.service.concerns;

import com.ace.entity.Account;
import com.ace.entity.Staff;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    RedisTemplate<String, Staff> redisTemplate;

    public Account account(String token) {
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
            Account account = new Account();
            account.setAccountId("001");
            account.setAccountName("001-NAME");
            List<Staff> staffList = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Staff staff = new Staff();
                staff.setId(Long.valueOf(i));
                staff.setAccountId("001");
                staff.setAccountName("001-NAME");
                staff.setProjectId("001-P-" + i);
                staff.setProjectName("001-PN-" + i);
                staff.setOrgId("001-O-" + i);
                staff.setOrgName("001-ON" + i);
                staff.setEmpId("001-E-" + i);
                staff.setEmpName("001-EM-" + i);
                staffList.add(staff);
                redisTemplate.opsForList().leftPush(account.getAccountId(), staff);
            }
            account.setStaffList(staffList);
            return account;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        TokenService tk = new TokenService();
        tk.account("3e968c13114a49a89f7551c6e81e142d");
    }
}
