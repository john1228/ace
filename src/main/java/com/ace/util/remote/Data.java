package com.ace.util.remote;

import lombok.*;

/**
 * @author john
 * @date 19-6-17 下午12:59
 */
@Getter
@Setter
public class Data {
    private String id;
    private String text;

    public Data(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
