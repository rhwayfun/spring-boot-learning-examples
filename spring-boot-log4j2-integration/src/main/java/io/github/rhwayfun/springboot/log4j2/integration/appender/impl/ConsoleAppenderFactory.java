package io.github.rhwayfun.springboot.log4j2.integration.appender.impl;

import io.github.rhwayfun.springboot.log4j2.integration.appender.AbstractAppenderFactory;
import io.github.rhwayfun.springboot.log4j2.integration.en.AppenderTypeEn;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class ConsoleAppenderFactory extends AbstractAppenderFactory {

    @Override
    protected boolean isAppropriate(AppenderTypeEn type) {
        return type == AppenderTypeEn.CONSOLE_APPENDER;
    }

    @Override
    protected Appender newAppender(String currentLogFileName, String logFilePattern, PatternLayout layout,
                                   TriggeringPolicy tp, ThresholdFilter thresholdFilter, String appenderName,
                                   Configuration config) {
        return ConsoleAppender.createDefaultAppenderForLayout(layout);
    }

}
