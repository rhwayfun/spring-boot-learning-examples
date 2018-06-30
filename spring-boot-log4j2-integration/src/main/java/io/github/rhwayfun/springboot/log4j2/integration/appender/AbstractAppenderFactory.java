package io.github.rhwayfun.springboot.log4j2.integration.appender;

import io.github.rhwayfun.springboot.log4j2.integration.en.AppenderTypeEn;
import io.github.rhwayfun.springboot.log4j2.integration.en.PatternLayoutEn;
import io.github.rhwayfun.springboot.log4j2.integration.util.AppenderUtil;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingRandomAccessFileAppender;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public abstract class AbstractAppenderFactory implements AppenderFactory {

    @Override
    public Appender createAppender(LoggerContext ctx, AppenderTypeEn type) {
        if (isAppropriate(type)) {
            final Configuration config = ctx.getConfiguration();
            // 日志文件名称
            String currentLogFileName = getCurrentLogFileName();
            // 日志命名模式
            String logFilePattern = getFilePattern(currentLogFileName);
            // layout
            PatternLayout layout = getPatternLayout(config);
            // 日志截断方式
            TriggeringPolicy tp = getTriggeringPolicy();
            // filter
            ThresholdFilter thresholdFilter = ThresholdFilter.createFilter(type.getLevel(), Filter.Result.ACCEPT, Filter.Result.NEUTRAL);
            // Appender
            return newAppender(currentLogFileName, logFilePattern, layout, tp, thresholdFilter, type.getName(), config);
        }
        return null;
    }

    /**
     * 是否指定类型Appender
     *
     * @param type AppenderType
     * @see AppenderTypeEn
     * @return match type
     */
    protected abstract boolean isAppropriate(AppenderTypeEn type);

    protected String getCurrentLogFileName() {
        return getLogFilePath() + getProjectName() + ".log";
    }

    protected String getFilePattern(String currentLogFileName) {
        return currentLogFileName + "-%d{yyyy-MM-dd}";
    }

    protected PatternLayout getPatternLayout(Configuration config) {
        String xmlLayout = AppenderUtil.getLoggerProperty("layout");
        String layoutStr = xmlLayout == null ? PatternLayoutEn.COMMON.getLayout() : xmlLayout;
        return PatternLayout.createLayout(layoutStr, null, config, null, UTF_8, true, false, null, null);
    }

    protected TriggeringPolicy getTriggeringPolicy() {
        return TimeBasedTriggeringPolicy.createPolicy("1", "true");
    }

    protected Appender newAppender(String currentLogFileName, String logFilePattern, PatternLayout layout,
                                   TriggeringPolicy tp, ThresholdFilter thresholdFilter, String appenderName, Configuration config) {
        return RollingRandomAccessFileAppender.createAppender(currentLogFileName, logFilePattern, null, appenderName,
                "true", null, tp, null, layout, thresholdFilter, "false", null, null, config);
    }

    protected String getLogFilePath() {
        String path = System.getProperty("logging.file.path");
        if (path == null) {
            return "C:/logs/default/";
        }
        if (path.endsWith("/")) {
            return path;
        }
        return path + "/";
    }

    protected String getProjectName() {
        String projectName = System.getProperty("project.name");
        if (projectName == null) {
            return "default";
        }
        return projectName;
    }

}
