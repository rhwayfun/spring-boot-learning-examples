package com.rhwayfun.springboot.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdatePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
        }
    }

    @Override
    public void reloadDisconfItem(String key, Object content) throws Exception {

    }

}
