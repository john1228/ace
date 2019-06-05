package com.ace.controller.callback;

import com.ace.entity.Receipt;
import com.ace.service.api.OrderService;
import com.ace.util.AlipayBuilder;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author john
 * @date 19-6-5 上午9:38
 */
@RestController
public class AlipayController {

    @Resource
    OrderService orderService;

    @RequestMapping("/callback/alipay")
    public String home(HttpServletRequest request, Receipt receipt) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        try {
            boolean checked = AlipayBuilder.instance.verify(null, params);
            if (checked) orderService.paying(receipt, "支付宝");
            return "success";

        } catch (Exception exp) {
            return "failed";
        }

    }

}
