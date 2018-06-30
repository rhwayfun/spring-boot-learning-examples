package io.github.rhwayfun.springboot.log4j2.integration;

import io.github.rhwayfun.springboot.log4j2.integration.en.AppenderTypeEn;
import io.github.rhwayfun.springboot.log4j2.integration.en.LoggerTypeEn;
import io.github.rhwayfun.springboot.log4j2.integration.util.AppenderUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
@Plugin(name = "Log4j2JavaConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(10)
public class Log4j2JavaConfigurationFactory extends ConfigurationFactory {

    private static final String[] SUPPORT_TYPES = new String[]{".xml", "*"};

    @Override
    protected String[] getSupportedTypes() {
        return SUPPORT_TYPES;
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource source) {
        return new CustomXmlConfiguration(loggerContext, source);
    }

    public static class CustomXmlConfiguration extends XmlConfiguration {

        public CustomXmlConfiguration(LoggerContext loggerContext, ConfigurationSource configSource) {
            super(loggerContext, configSource);
        }

        @Override
        protected void setToDefault() {

        }

        @Override
        protected void doConfigure() {
            super.doConfigure();
            final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            final Configuration configuration = ctx.getConfiguration();
            LoggerConfig rootLogger = getRootLogger();
            rootLogger.setLevel(Level.INFO);
            for (AppenderTypeEn appenderTypeEn : AppenderTypeEn.values()) {
                Appender appender = AppenderUtil.createAppender(ctx, appenderTypeEn);
                if (appender == null) {
                    continue;
                }
                appender.start();
                addAppender(appender);
                addLoggerWithRef(configuration, rootLogger, appenderTypeEn, appender);
            }
            for (LoggerTypeEn logger : LoggerTypeEn.values()) {
                LoggerConfig loggerConfig = LoggerConfig.createLogger(true, logger.getLevel(), logger.getLoggerName(),
                        "true", new AppenderRef[]{}, null, configuration, null);
                addLogger(logger.getLoggerName(), loggerConfig);
            }
        }

        private void addLoggerWithRef(Configuration configuration, LoggerConfig rootLogger,
                                      AppenderTypeEn type, Appender appender) {
            if (type.isSubRootAppender()) {
                Level level = type.getLevel();
                rootLogger.addAppender(appender, level, null);
            } else {
                AppenderRef ref = AppenderRef.createAppenderRef(type.getName(), null, null);
                AppenderRef[] refs = new AppenderRef[] {ref};
                LoggerConfig loggerConfig = LoggerConfig.createLogger(false, type.getLevel(), type.getName(),
                        "true", refs, null, configuration, null);
                loggerConfig.addAppender(appender, type.getLevel(), null);
                addLogger(type.getName(), loggerConfig);
            }
        }
    }
}
