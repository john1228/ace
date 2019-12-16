package com.ace.controller.callback;

import com.ace.entity.Receipt;
import com.ace.service.api.OrderService;
import com.ace.service.callback.AlipayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝支付回调处理
 *
 * @author john
 * @date 19-6-5 上午9:38
 */
@RestController
@Log4j2
public class AlipayController {

    @Resource
    private OrderService orderService;
    @Resource
    private AlipayService alipayService;

    @RequestMapping("/callback/alipay")
    public String home(HttpServletRequest request) {
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
            log.info("支付宝[" + name + "]:" + valueStr);
            params.put(name, valueStr);
        }
        try {
            if (alipayService.check(params.get("out_trade_no"), params)) {
                Receipt receipt = new Receipt(params);
                orderService.paying(receipt, "支付宝");
                return "success";
            } else {
                log.error("支付宝回调验证出错");
                return "failed";
            }
        } catch (Exception exp) {
            return "failed";
        }
    }

}
