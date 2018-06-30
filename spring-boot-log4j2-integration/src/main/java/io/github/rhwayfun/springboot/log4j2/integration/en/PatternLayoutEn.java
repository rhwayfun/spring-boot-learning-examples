package io.github.rhwayfun.springboot.log4j2.integration.en;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public enum PatternLayoutEn {
    /**
     * default
     */
    COMMON("%d %p [%t] %c{10}:%M:%L %m%n");

    private String layout;

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    PatternLayoutEn(String layout) {
        this.layout = layout;
    }
}
