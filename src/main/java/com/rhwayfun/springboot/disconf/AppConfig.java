/*
package com.rhwayfun.springboot.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@DisconfFile(filename = "app.properties")
@DisconfUpdateService(classes = {AppConfig.class})
public class AppConfig implements IDisconfUpdate {

    private static Logger log = LoggerFactory.getLogger(AppConfig.class);

    private String env;

    private String app;

    private String prefix;

    private String suffix;

    private Integer port;

    private String logPath;

    @DisconfFileItem(name = "env", associateField = "env")
    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    @DisconfFileItem(name = "spring.application.name", associateField = "app")
    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @DisconfFileItem(name = "spring.mvc.view.prefix", associateField = "prefix")
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @DisconfFileItem(name = "spring.mvc.view.suffix", associateField = "suffix")
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @DisconfFileItem(name = "server.port", associateField = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @DisconfFileItem(name = "logging.path", associateField = "logPath")
    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    @Override
    public void reload() throws Exception {
        log.info("app.properties>>>>>>>配置更新");
    }
}
*/
