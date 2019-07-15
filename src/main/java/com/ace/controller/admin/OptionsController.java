package com.ace.controller.admin;

import com.ace.util.remote.Data;
import com.ace.util.remote.DataUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/options")
@Log4j2
public class OptionsController {


    @GetMapping("/{source}")
    @ResponseBody
    public List<Data> options(
            @PathVariable("source") String source,
            @RequestParam(value = "link", defaultValue = "") String linkId,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        if (Strings.isBlank(linkId)) return new ArrayList<>();
        switch (source) {
            case "orgs":
                return DataUtils.orgList(linkId);
            case "employee":
                List<Data> empList = DataUtils.employee(linkId);
                return empList.stream().filter(data -> data.getText().contains(keyword)).collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }
}
