package com.ace.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author john
 * @date 19-5-22 下午6:37
 */
public enum Menu {
    room(new String[]{"/admin/supports", "/admin/rooms", "/admin/schedules", "/admin/prices", "/admin/coupons", "/admin/channels", "/admin/operations"}),
    order(new String[]{"/admin/orders", "/admin/invoices", "/admin/receipts", "/admin/applications"}),
    setting(new String[]{"/admin/protocol", "/admin/alipays", "/admin/wxpays"}),
    log(new String[]{"/admin/oper_logs"});
    private List<String> items;

    Menu(String[] items) {
        this.items = Arrays.asList(items);
    }

    public boolean contain(String path) {
        String[] pathInfo = path.split("/");
        if (pathInfo.length >= 3) {
            String judge = String.join("/", Arrays.copyOfRange(pathInfo, 0, 3));
            if (items.contains(judge)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        for (String tmp : "/admin/supports".split("/")) {
            System.err.println(tmp);
        }
    }
}
