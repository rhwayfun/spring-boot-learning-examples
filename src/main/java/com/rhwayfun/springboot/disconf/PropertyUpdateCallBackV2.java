package com.rhwayfun.springboot.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdatePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
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
@Scope("singleton")
@DisconfFile(filename = "common.properties")
@DisconfUpdateService(classes = { PropertyUpdateCallBackV2.class })
public class PropertyUpdateCallBackV2 implements IDisconfUpdatePipeline {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(PropertyUpdateCallBackV2.class);

    private Map<String, Object> PARAM = new HashMap<>();

    @Resource
    private ConfigurableEnvironment env;

    @PostConstruct
    public void init() throws Exception {
        reload();
    }

    private void reload() {
        Map<String, Object> cacheMap = new HashMap<>();
        PropertySource<?> propertySource = env.getPropertySources().get("springboot-demo");
        if (propertySource instanceof PropertiesPropertySource) {
            PropertiesPropertySource propertiesPropertySource = (PropertiesPropertySource) propertySource;
            Map<String, Object> objectMap = propertiesPropertySource.getSource();
            for (Map.Entry<String, Object> e : objectMap.entrySet()) {
                log.info("设置环境>>>>key:{},value:{}", e.getKey(), e.getValue());
                cacheMap.put(e.getKey(), e.getValue());
            }
        }
        PARAM = cacheMap;
    }

    private boolean openTest;

    private int calcFactor;

    private String appName;

    @DisconfFileItem(name = "OPEN_TEST", associateField = "openTest")
    public boolean isOpenTest() {
        return openTest;
    }

    public void setOpenTest(boolean openTest) {
        this.openTest = openTest;
    }

    public int getCalcFactor() {
        return calcFactor;
    }

    @DisconfFileItem(name = "CALC_FACTOR", associateField = "calcFactor")
    public void setCalcFactor(int calcFactor) {
        this.calcFactor = calcFactor;
    }

    public String getAppName() {
        return appName;
    }

    @DisconfFileItem(name = "APP_NAME", associateField = "appName")
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "PropertyUpdateCallBack{" +
                "openTest=" + openTest +
                ", calcFactor=" + calcFactor +
                ", appName='" + appName + '\'' +
                '}';
    }

    @Override
    public void reloadDisconfFile(String key, String filePath) throws Exception {
        if ("common.properties".equals(key)) {
            log.info("{},{}>>>>>>>>>>>配置更新：{}", key, filePath, this);
            log.info("map:{}", PARAM);
            reload();
        }
    }

    @Override
    public void reloadDisconfItem(String key, Object content) throws Exception {

    }

}
