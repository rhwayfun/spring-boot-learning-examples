package com.rhwayfun.springboot.logging.log4j2.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingRandomAccessFileAppender;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.springframework.util.StringUtils;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Plugin(name = "CustomXmlConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(10)
public class Log4j2XmlConfiguration extends ConfigurationFactory {

    public static final String[] SUFFIXES = new String[]{".xml", "*"};

    @Override
    protected String[] getSupportedTypes() {
        return SUFFIXES;
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource source) {
        return new Log4j2XmlCommonConfiguration(loggerContext, source);
    }

    public class Log4j2XmlCommonConfiguration extends XmlConfiguration {
        public Log4j2XmlCommonConfiguration(LoggerContext loggerContext, ConfigurationSource configurationSource) {
            super(loggerContext, configurationSource);
        }

        @Override
        protected void doConfigure() {
            super.doConfigure();
            LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
            Configuration configuration = loggerContext.getConfiguration();
            String projectName = System.getProperty("project.name");
            String loggingFilePath = System.getProperty("logging.file.path");
            if (!StringUtils.isEmpty(projectName) && !StringUtils.isEmpty(loggingFilePath)) {
                String loggingFileName = loggingFilePath + "/" + projectName + ".log";
                String loggingFileWarnName = loggingFilePath + "/" + projectName + "-warn.log";
                String loggingFileErrorName = loggingFilePath + "/" + projectName + "-error.log";
                String loggingFileRiderMqName = loggingFilePath + "/" + "ridermq.log";
                String loggingFileRiderSpaceName = loggingFilePath + "/" + "riderspace.log";
                String loggingFileMonitorName = loggingFilePath + "/" + "../monitor/" + projectName + "-monitor.log";

                /* Appender */
                PatternLayout patternLayout = PatternLayout.createLayout("%d %p [%t] %c{1.}:%M:%L %X{orderId,groupId} %m%n", null, configuration, null, null, true, true, null, null);
                PatternLayout msnPatternLayout = PatternLayout.createLayout("%m%n", null, configuration, null, null, true, true, null, null);
                TimeBasedTriggeringPolicy timeBasedTriggeringPolicy = TimeBasedTriggeringPolicy.createPolicy(null, null);
                ThresholdFilter thresholdFilter = ThresholdFilter.createFilter(Level.INFO, Filter.Result.ACCEPT, Filter.Result.NEUTRAL);
                ThresholdFilter warnThresholdFilter = ThresholdFilter.createFilter(Level.WARN, Filter.Result.ACCEPT, Filter.Result.NEUTRAL);
                ThresholdFilter errorThresholdFilter = ThresholdFilter.createFilter(Level.ERROR, Filter.Result.ACCEPT, Filter.Result.NEUTRAL);

                LoggerConfig rootLogger = getRootLogger();
                rootLogger.setLevel(Level.DEBUG);
                ConsoleAppender consoleAppender = ConsoleAppender.createDefaultAppenderForLayout(patternLayout);
                consoleAppender.start();
                addAppender(consoleAppender);
                RollingRandomAccessFileAppender randomAccessFileAppender;
                randomAccessFileAppender = RollingRandomAccessFileAppender.createAppender(loggingFileName, loggingFileName + "-%d{yyyy-MM-dd-HH}", null, "Rolling", "true", null, timeBasedTriggeringPolicy, null, patternLayout, thresholdFilter, "false", null, null, configuration);
                randomAccessFileAppender.start();
                addAppender(randomAccessFileAppender);
                rootLogger.addAppender(randomAccessFileAppender, Level.INFO, null);
                randomAccessFileAppender = RollingRandomAccessFileAppender.createAppender(loggingFileWarnName, loggingFileWarnName + "-%d{yyyy-MM-dd}", null, "RollingWarn", "true", null, timeBasedTriggeringPolicy, null, patternLayout, warnThresholdFilter, null, null, null, configuration);
                randomAccessFileAppender.start();
                addAppender(randomAccessFileAppender);
                rootLogger.addAppender(randomAccessFileAppender, Level.WARN, null);
                randomAccessFileAppender = RollingRandomAccessFileAppender.createAppender(loggingFileErrorName, loggingFileErrorName + "-%d{yyyy-MM-dd}", null, "RollingError", "true", null, timeBasedTriggeringPolicy, null, patternLayout, errorThresholdFilter, null, null, null, configuration);
                randomAccessFileAppender.start();
                addAppender(randomAccessFileAppender);
                rootLogger.addAppender(randomAccessFileAppender, Level.ERROR, null);
                randomAccessFileAppender = RollingRandomAccessFileAppender.createAppender(loggingFileMonitorName, loggingFileMonitorName + "-monitor.log-%d{yyyy-MM-dd-HH}", null, "RollingMonitor", "true", null, timeBasedTriggeringPolicy, null, msnPatternLayout, thresholdFilter, null, null, null, configuration);
                randomAccessFileAppender.start();
                addAppender(randomAccessFileAppender);
                randomAccessFileAppender = RollingRandomAccessFileAppender.createAppender(loggingFileRiderMqName, loggingFileRiderMqName + "-%d{yyyy-MM-dd-HH}", null, "RollingRiderMq", "true", null, timeBasedTriggeringPolicy, null, patternLayout, thresholdFilter, null, null, null, configuration);
                randomAccessFileAppender.start();
                addAppender(randomAccessFileAppender);
                randomAccessFileAppender = RollingRandomAccessFileAppender.createAppender(loggingFileRiderSpaceName, loggingFileRiderSpaceName + "-%d{yyyy-MM-dd-HH}", null, "RollingRiderSpace", "true", null, timeBasedTriggeringPolicy, null, patternLayout, thresholdFilter, null, null, null, configuration);
                randomAccessFileAppender.start();
                addAppender(randomAccessFileAppender);

                /* Loggers */
                AppenderRef appenderRef = AppenderRef.createAppenderRef("RollingMonitor", Level.INFO, thresholdFilter);
                AppenderRef[] appenderRefs = new AppenderRef[]{appenderRef};
                LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, "com.dianwoba.monitor.client.MonitorUtilImpl", "true", appenderRefs, null, configuration, null);
                addLogger("com.dianwoba.monitor.client.MonitorUtilImpl", loggerConfig);
                appenderRef = AppenderRef.createAppenderRef("RollingRiderMq", Level.INFO, thresholdFilter);
                appenderRefs = new AppenderRef[]{appenderRef};
                loggerConfig = LoggerConfig.createLogger(false, Level.INFO, "com.dianwoba.rider.stalker.client.RiderClinetCacheKeeperConsumer", "true", appenderRefs, null, configuration, null);
                addLogger("com.dianwoba.rider.stalker.client.RiderClinetCacheKeeperConsumer", loggerConfig);
                appenderRef = AppenderRef.createAppenderRef("RollingRiderSpace", Level.INFO, thresholdFilter);
                appenderRefs = new AppenderRef[]{appenderRef};
                loggerConfig = LoggerConfig.createLogger(false, Level.INFO, "com.dianwoba.dispatch.client.cache.rider.space.listener.RiderSpaceCaffeineListener", "true", appenderRefs, null, configuration, null);
                addLogger("com.dianwoba.dispatch.client.cache.rider.space.listener.RiderSpaceCaffeineListener", loggerConfig);

                loggerConfig = LoggerConfig.createLogger(false, Level.INFO, "org.springframework", "true", new AppenderRef[]{}, null, configuration, null);
                addLogger("org.springframework", loggerConfig);
                loggerConfig = LoggerConfig.createLogger(false, Level.WARN, "org.apache", "true", new AppenderRef[]{}, null, configuration, null);
                addLogger("org.apache", loggerConfig);
                loggerConfig = LoggerConfig.createLogger(false, Level.WARN, "druid.sql", "true", new AppenderRef[]{}, null, configuration, null);
                addLogger("druid.sql", loggerConfig);
                loggerConfig = LoggerConfig.createLogger(false, Level.WARN, "RocketmqClient", "true", new AppenderRef[]{}, null, configuration, null);
                addLogger("RocketmqClient", loggerConfig);
                loggerConfig = LoggerConfig.createLogger(false, Level.WARN, "com.alibaba.dubbo.registry.zookeeper", "true", new AppenderRef[]{}, null, configuration, null);
                addLogger("com.alibaba.dubbo.registry.zookeeper", loggerConfig);
            }
        }
    }
}
