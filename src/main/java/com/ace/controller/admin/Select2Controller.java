package com.ace.controller.admin;

import com.ace.controller.admin.concerns.RemoteOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/select2")
public class Select2Controller {
    Logger logger = LoggerFactory.getLogger(Select2Controller.class);

    @RequestMapping("{source}")
    public RemoteOption options(@PathVariable("source") String source) {
        logger.info("1111");
        RemoteOption remoteOption = new RemoteOption();
        RemoteOption.Option option = remoteOption.new Option();
        option.setId("1");
        option.setText("会议室１");
        remoteOption.getResults().add(option);
        return remoteOption;
    }
}
