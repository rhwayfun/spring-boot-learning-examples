package io.github.rhwayfun.springboot.log4j2.integration.en;

import org.apache.logging.log4j.Level;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public enum  AppenderTypeEn {
    /**
     * CONSOLE
     */
    CONSOLE_APPENDER("console", Level.ALL, true),
    INFO_APPENDER("info", Level.INFO, true),
    WARN_APPENDER("warn", Level.WARN, true),
    ERROR_APPENDER("error", Level.ERROR, true);
    private String name;
    private Level level;
    private boolean subRootAppender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isSubRootAppender() {
        return subRootAppender;
    }

    public void setSubRootAppender(boolean subRootAppender) {
        this.subRootAppender = subRootAppender;
    }

    AppenderTypeEn(String name, Level level, boolean subRootAppender) {
        this.name = name;
        this.level = level;
        this.subRootAppender = subRootAppender;
    }
}
