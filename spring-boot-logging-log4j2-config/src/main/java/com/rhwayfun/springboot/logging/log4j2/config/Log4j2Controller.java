package com.rhwayfun.springboot.logging.log4j2.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@RestController
public class Log4j2Controller {
    /** Logger */
    private static Logger logger = LoggerFactory.getLogger(Log4j2Controller.class);

    @RequestMapping("/{id}")
    @ResponseBody
    public String id(@PathVariable String id) {
        logger.info("test id:{}", id);
        BigdataUtilImpl.logAction(new BigDateObject(1, new Date()));
        try {
            if (Integer.parseInt(id) % 2 == 0) {
                throw new RuntimeException("非法id:" + id);
            }
        } catch (Exception e) {
            logger.error("参数非法", e);
        }
        return System.currentTimeMillis() + "," + id;
    }

    @Data
    @AllArgsConstructor
    public static class BigDateObject {
        private int id;
        private Date date;
    }
}
