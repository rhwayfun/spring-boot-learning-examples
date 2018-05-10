package com.rhwayfun.springboot.logging.log4j2.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class BigdataUtilImpl {

    /** Logger */
    private static Logger logger = LoggerFactory.getLogger(BigdataUtilImpl.class);

    public static void logAction(Object o) {
        for (int i = 0; i < 100; i++) {
            logger.info(logger.getName() + JSON.toJSONString(o));
        }
    }
}
