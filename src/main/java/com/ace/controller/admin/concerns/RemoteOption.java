package com.ace.controller.admin.concerns;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RemoteOption {
    private List<Option> results;

    @Getter
    @Setter
    public class Option {
        private String id;
        private String text;
    }
}
