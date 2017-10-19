package com.rhwayfun.springboot.quickstart.disconf;

import com.baidu.disconf.client.common.update.IDisconfUpdatePipeline;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chubin on 2017/6/11.
 */
@Service
public class PropertyUpdateCallBackV3 implements IDisconfUpdatePipeline {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(PropertyUpdateCallBackV3.class);

    private Map<String, Object> PARAM = new HashMap<>();

    @Resource
    private ConfigurableEnvironment env;

    @PostConstruct
    public void init() throws Exception {
        reload();
    }

    private void reload() {
        Map<String, Object> cacheMap = new HashMap<>();
        PropertySource<?> propertySource = env.getPropertySources().get("springboot-springboot");
        if (propertySource instanceof PropertiesPropertySource) {
            PropertiesPropertySource propertiesPropertySource = (PropertiesPropertySource) propertySource;
            Map<String, Object> objectMap = propertiesPropertySource.getSource();
            for (Map.Entry<String, Object> e : objectMap.entrySet()) {
                cacheMap.put(e.getKey(), e.getValue());
            }
        }
        PARAM = cacheMap;
    }

    @Override
    public void reloadDisconfFile(String key, String filePath) throws Exception {
        if ("common.properties".equals(key)) {
            log.info("{}>>>>>>>>>>>配置更新", key);
            reload();
        }
    }

    @Override
    public void reloadDisconfItem(String key, Object content) throws Exception {
    }

    public String getCalcFactorV2() {
        return (String) PARAM.get("CALC_FACTOR");
    }

    private String CLAC_FACTORT = "CLAC_FACTORT";

    private String OPEN_TEST = "OPEN_TEST";

    private String TEST_BALANCE = "TEST_BALANCE";

    public int getCalcFactor() {
        return getIntDefault(CLAC_FACTORT);
    }

    public boolean isOpenTest() {
        return getBooleanDefault(OPEN_TEST);
    }

    public double getTestBalance() {
        return getDoubleDefault(TEST_BALANCE);
    }

    private double getDoubleDefault(String key) {
        return Double.parseDouble(getValue(key));
    }

    private boolean getBooleanDefault(String key) {
        return Boolean.parseBoolean(getValue(key));
    }

    private int getIntDefault(String key) {
        return Integer.parseInt(StringUtils.isBlank(getValue(key)) ? "-1" : getValue(key));
    }

    private String getValue(String key) {
        return String.valueOf(PARAM.get(key));
    }

}
