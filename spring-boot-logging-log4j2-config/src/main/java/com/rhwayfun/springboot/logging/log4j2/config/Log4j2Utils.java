package com.rhwayfun.springboot.logging.log4j2.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class Log4j2Utils {

    public static LayoutComponentBuilder newLayoutOnlyContent(ConfigurationBuilder<BuiltConfiguration> builder) {
        return builder.newLayout("PatternLayout").addAttribute("pattern", "%d %p [%t] %c{1.}:%M:%L %X{orderId,groupId} %m%n");
    }

    public static LayoutComponentBuilder newLayout(ConfigurationBuilder<BuiltConfiguration> builder) {
        return builder.newLayout("PatternLayout").addAttribute("pattern", "%m%n");
    }

    public static ComponentBuilder newTriggeringPolicy(ConfigurationBuilder<BuiltConfiguration> builder) {
        return builder.newComponent("Policies")
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy"));
    }

    public static FilterComponentBuilder newThresholdFilter(ConfigurationBuilder<BuiltConfiguration> builder) {
        return builder.newFilter("ThresholdFilter", Filter.Result.ACCEPT, Filter.Result.NEUTRAL)
                .addAttribute("level", Level.INFO);
    }

    public static AppenderComponentBuilder newRollingRandomAccessFileAppender(ConfigurationBuilder<BuiltConfiguration> builder, String appenderName, String fileName, String filePattern) {
        return builder.newAppender(appenderName, "RollingRandomAccessFile")
                .add(newThresholdFilter(builder))
                .addAttribute("fileName", fileName)
                .addAttribute("filePattern", filePattern)
                .addAttribute("immediateFlush", "true")
                .add(newLayoutOnlyContent(builder))
                .addComponent(newTriggeringPolicy(builder));
    }

    public static LoggerComponentBuilder newLoggerWithRef(ConfigurationBuilder<BuiltConfiguration> builder, String loggerName, String ref) {
        return builder.newLogger(loggerName, Level.INFO).
                add(builder.newAppenderRef(ref)).
                addAttribute("additivity", false);
    }

    public static LoggerComponentBuilder newLogger(ConfigurationBuilder<BuiltConfiguration> builder, String loggerName, Level level) {
        return builder.newLogger(loggerName, level);
    }

}
