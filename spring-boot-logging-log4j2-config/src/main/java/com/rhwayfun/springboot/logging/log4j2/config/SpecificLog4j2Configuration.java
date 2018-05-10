package com.rhwayfun.springboot.logging.log4j2.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class SpecificLog4j2Configuration extends Log4j2Configuration {

    @Override
    public Configuration createCustomConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        super.createCustomConfiguration(name, builder);

        /* Properties */
        builder.addProperty("logging.file.action.name", "${sys:logging.file.path}/../bigdata/${sys:project.name}-action.log");

        /* Appender */
        AppenderComponentBuilder componentBuilder = Log4j2Utils.newRollingRandomAccessFileAppender(builder,
                "RollingAction", "${logging.file.action.name}", "${logging.file.action.name}-%d{yyyy-MM-dd-HH}");
        builder.add(componentBuilder);

        /* Logger */
        LoggerComponentBuilder loggerWithRef = Log4j2Utils.newLoggerWithRef(builder, "com.rhwayfun.springboot.logging.log4j2.config.BigdataUtilImpl", "RollingAction");
        builder.add(loggerWithRef);
        LoggerComponentBuilder logger = Log4j2Utils.newLogger(builder, "com.alibaba.dubbo.monitor", Level.WARN);
        builder.add(logger);
        return builder.build();
    }
}
