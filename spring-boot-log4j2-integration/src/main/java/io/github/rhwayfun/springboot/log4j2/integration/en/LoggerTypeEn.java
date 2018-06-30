package io.github.rhwayfun.springboot.log4j2.integration.en;

import org.apache.logging.log4j.Level;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public enum LoggerTypeEn {
    /**
     * Spring
     */
    SPRING_LOGGER("org.springframework", Level.INFO);

    private String loggerName;
    private Level level;

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    LoggerTypeEn(String loggerName, Level level) {
        this.loggerName = loggerName;
        this.level = level;
    }
}
