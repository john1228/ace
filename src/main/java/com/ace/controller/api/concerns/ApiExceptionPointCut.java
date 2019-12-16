package com.ace.controller.api.concerns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john
 * @date 19-5-21 下午2:49
 */
@ControllerAdvice(annotations = RestController.class)
public class ApiExceptionPointCut {
    Logger logger = LoggerFactory.getLogger(ApiExceptionPointCut.class);

//    @ExceptionHandler(Exception.class)
//    @JsonView(ApiView.Base.class)
//    @ResponseBody
//    public Result handleException(Exception e) {
//        logger.info("系统异常:" + e.getMessage());
//        return new Failure(e.getMessage());
//    }
}
