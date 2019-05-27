package com.ace.util.wxpay;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author john
 * @date 19-5-18 下午7:50
 */
public enum Wxpay {
    Instance;
    private final String appId = "wxdb60f3e97a338462"; //"wx365ab323b9269d30";
    private final String mchId = "1432701802";//"1266307801";
    private final String notifyUrl = "http://m1.aidong.me/coach/weixin/asynchronousNotify.json";
    private final String tradeType = "APP";
    private final String secretKey = "e10adc3949ba59abbe56e057f20f883e";
    private final String getway = "https://api.mch.weixin.qq.com/pay/unifiedorder";



}
