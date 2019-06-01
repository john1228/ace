package com.ace.service.concerns;

import com.ace.entity.Account;
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
//            JSONObject jsonObject = new JSONObject(accountInfo.toString());
//            JSONObject accountObj = jsonObject.getJSONObject("data");

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        TokenService tk = new TokenService();
        tk.account("3e968c13114a49a89f7551c6e81e142d");
    }
}