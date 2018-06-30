package io.github.rhwayfun.springboot.log4j2.integration.appender;

import io.github.rhwayfun.springboot.log4j2.integration.en.AppenderTypeEn;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public interface AppenderFactory {

    Appender createAppender(LoggerContext ctx, AppenderTypeEn type);

}
