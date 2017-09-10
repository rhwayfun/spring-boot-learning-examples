package com.rhwayfun.springboot.quickstart.cas;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chubin on 2017/8/13.
 */

@ConfigurationProperties(prefix = CasProperties.CAS_PREFIX)
public class CasProperties {

    public static final String CAS_PREFIX = "cas";


}
