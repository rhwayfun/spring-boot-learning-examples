package com.rhwayfun.springboot.logging.log4j2.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class Log4j2Configuration extends ConfigurationFactory {

    @Override
    protected String[] getSupportedTypes() {
        return new String[]{"*"};
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource configurationSource) {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        return createCustomConfiguration(configurationSource.toString(), builder);
    }

    protected Configuration createCustomConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Level.WARN);

        /** Properties */
        builder.addProperty("local.logging.path", "C:/logs");
        builder.addProperty("project.name", "${bundle:application:app.code}");
        builder.addProperty("logging.file.path", "${local.logging.path}/${project.name}");
        builder.addProperty("logging.file.name", "${sys:logging.file.path}/${project.name}.log");
        builder.addProperty("logging.file.warn.name", "${sys:logging.file.path}/${project.name}-warn.log");
        builder.addProperty("logging.file.error.name", "${sys:logging.file.path}/${project.name}-error.log");
        builder.addProperty("logging.file.monitor.name", "${sys:logging.file.path}/../monitor/${project.name}-monitor.log");
        builder.addProperty("logging.file.ridermq.name", "${sys:logging.file.path}/ridermq.log");
        builder.addProperty("logging.file.riderspace.name", "${sys:logging.file.path}/riderspace.log");
        builder.addProperty("layout", "%d %p [%t] %c{1.}:%M:%L %X{orderId,groupId} %m%n");

        /** Appender */
        AppenderComponentBuilder componentBuilder = builder.newAppender("Stdout", "CONSOLE").
                addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        componentBuilder.add(builder.newLayout("PatternLayout").
                addAttribute("pattern", "${layout}"));
        builder.add(componentBuilder);

        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "${layout}");
        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy"));

        componentBuilder = builder.newAppender("Rolling", "RollingRandomAccessFile")
                .addAttribute("fileName", "${logging.file.name}")
                .addAttribute("filePattern", "${logging.file.name}-%d{yyyy-MM-dd-HH}")
                .addAttribute("immediateFlush", "true")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(componentBuilder);
        componentBuilder = builder.newAppender("RollingWarn", "RollingRandomAccessFile")
                .add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.DENY)
                        .addAttribute("level", Level.WARN))
                .addAttribute("fileName", "${logging.file.warn.name}")
                .addAttribute("filePattern", "${logging.file.warn.name}-%d{yyyy-MM-dd-HH}")
                .addAttribute("immediateFlush", "true")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(componentBuilder);
        componentBuilder = builder.newAppender("RollingError", "RollingRandomAccessFile")
                .add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.DENY)
                        .addAttribute("level", Level.ERROR))
                .addAttribute("fileName", "${logging.file.error.name}")
                .addAttribute("filePattern", "${logging.file.error.name}-%d{yyyy-MM-dd-HH}")
                .addAttribute("immediateFlush", "true")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(componentBuilder);
        componentBuilder = builder.newAppender("RollingMonitor", "RollingRandomAccessFile")
                .add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                        .addAttribute("level", Level.INFO))
                .addAttribute("fileName", "${logging.file.monitor.name}")
                .addAttribute("filePattern", "${logging.file.monitor.name}-%d{yyyy-MM-dd-HH}")
                .addAttribute("immediateFlush", "true")
                .add(builder.newLayout("PatternLayout").addAttribute("pattern", "%m%n"))
                .addComponent(triggeringPolicy);
        builder.add(componentBuilder);
        componentBuilder = builder.newAppender("RollingRiderMq", "RollingRandomAccessFile")
                .add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                        .addAttribute("level", Level.INFO))
                .addAttribute("fileName", "${logging.file.ridermq.name}")
                .addAttribute("filePattern", "${logging.file.ridermq.name}-%d{yyyy-MM-dd-HH}")
                .addAttribute("immediateFlush", "true")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(componentBuilder);
        componentBuilder = builder.newAppender("RollingRiderSpace", "RollingRandomAccessFile")
                .add(builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                        .addAttribute("level", Level.INFO))
                .addAttribute("fileName", "${logging.file.riderspace.name}")
                .addAttribute("filePattern", "${logging.file.riderspace.name}-%d{yyyy-MM-dd-HH}")
                .addAttribute("immediateFlush", "true")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(componentBuilder);

        /** Loggers */
        builder.add(builder.newLogger("com.dianwoba.monitor.client.MonitorUtilImpl", Level.INFO).
                add(builder.newAppenderRef("RollingMonitor")).
                addAttribute("additivity", false));
        builder.add(builder.newLogger("com.dianwoba.rider.stalker.client.RiderClinetCacheKeeperConsumer", Level.INFO).
                add(builder.newAppenderRef("RollingRiderMq")).
                addAttribute("additivity", false));
        builder.add(builder.newLogger("com.dianwoba.dispatch.client.cache.rider.space.listener.RiderSpaceCaffeineListener", Level.INFO).
                add(builder.newAppenderRef("RollingRiderSpace")).
                addAttribute("additivity", false));
        builder.add(builder.newLogger("org.springframework", Level.INFO));
        builder.add(builder.newLogger("org.apache", Level.WARN));
        builder.add(builder.newLogger("druid.sql", Level.WARN));
        builder.add(builder.newLogger("RocketmqClient", Level.WARN));
        builder.add(builder.newLogger("com.alibaba.dubbo.registry.zookeeper", Level.WARN));
        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO).addAttribute("includeLocation", "true");
        AppenderRefComponentBuilder stdout = rootLogger.getBuilder().newAppenderRef("Stdout");
        AppenderRefComponentBuilder rolling = rootLogger.getBuilder().newAppenderRef("Rolling");
        AppenderRefComponentBuilder rollingWarn = rootLogger.getBuilder().newAppenderRef("RollingWarn");
        AppenderRefComponentBuilder rollingError = rootLogger.getBuilder().newAppenderRef("RollingError");
        rootLogger.add(stdout);
        rootLogger.add(rolling);
        rootLogger.add(rollingWarn);
        rootLogger.add(rollingError);
        builder.add(rootLogger);
        return builder.build();
    }

}
