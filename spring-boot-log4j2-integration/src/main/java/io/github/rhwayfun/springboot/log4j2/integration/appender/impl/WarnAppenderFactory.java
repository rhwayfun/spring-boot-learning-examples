package io.github.rhwayfun.springboot.log4j2.integration.appender.impl;

import io.github.rhwayfun.springboot.log4j2.integration.appender.AbstractAppenderFactory;
import io.github.rhwayfun.springboot.log4j2.integration.en.AppenderTypeEn;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class WarnAppenderFactory extends AbstractAppenderFactory {

    @Override
    protected boolean isAppropriate(AppenderTypeEn type) {
        return type == AppenderTypeEn.WARN_APPENDER;
    }

    @Override
    protected String getCurrentLogFileName() {
        return getLogFilePath() + getProjectName() + "-warn.log";
    }
}
