package com.ace.controller.admin;

import com.ace.util.remote.Data;
import com.ace.util.remote.DataUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/options")
@Log4j2
public class OptionsController {


    @RequestMapping("/{source}")
    @ResponseBody
    public List<Data> options(
            @PathVariable("source") String source,
            @RequestParam("link") String linkId,
            @RequestParam("keyword") String keyword
    ) {
        switch (source) {
            case "employee":
                List<Data> empList = DataUtils.employee(linkId);
                return empList.stream().filter(data -> data.getText().contains(keyword)).collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }
}
