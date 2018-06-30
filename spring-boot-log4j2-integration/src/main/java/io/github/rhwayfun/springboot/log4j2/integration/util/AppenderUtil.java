package io.github.rhwayfun.springboot.log4j2.integration.util;

import io.github.rhwayfun.springboot.log4j2.integration.appender.AppenderFactory;
import io.github.rhwayfun.springboot.log4j2.integration.en.AppenderTypeEn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.lookup.StrLookup;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;

import java.util.ServiceLoader;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class AppenderUtil {

    public static org.apache.logging.log4j.core.Appender createAppender(LoggerContext ctx, AppenderTypeEn type) {
        ClassLoader classLoader = AppenderUtil.class.getClassLoader();
        ServiceLoader<AppenderFactory> serviceLoader = ServiceLoader.load(AppenderFactory.class, classLoader);
        for (AppenderFactory appenderFactory : serviceLoader) {
            org.apache.logging.log4j.core.Appender appender = appenderFactory.createAppender(ctx, type);
            if (appender != null) {
                return appender;
            }
        }
        return null;
    }

    public static String getLoggerProperty(String propertyName) {
        final LoggerContext loggerContext = (LoggerContext) LogManager.getContext(true);
        final Configuration config = loggerContext.getConfiguration();
        final StrSubstitutor strSubstitutor = config.getStrSubstitutor();
        final StrLookup variableResolver = strSubstitutor.getVariableResolver();
        return variableResolver.lookup(propertyName);
    }

}
