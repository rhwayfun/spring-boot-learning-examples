package com.rhwayfun.springboot.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 带前缀属性配置
 * 使用@ConfigurationProperties将property的配置映射到这个类的属性中
 *
 * property加载顺序：
 *
 * 1. Devtools global settings properties on your home directory (~/.spring-boot-devtools.properties when devtools is active).
 * 2. @TestPropertySource annotations on your tests.
 * 3. @SpringBootTest#properties annotation attribute on your tests.
 * 4. Command line arguments.
 * 5. Properties from SPRING_APPLICATION_JSON (inline JSON embedded in an environment variable or system property)
 * 6. ServletConfig init parameters.
 * 7. ServletContext init parameters.
 * 8. JNDI attributes from java:comp/env.
 * 9. Java System properties (System.getProperties()).
 * 10. OS environment variables.
 * 11. A RandomValuePropertySource that only has properties in random.*.
 * 12. Profile-specific application properties outside of your packaged jar (application-{profile}.properties and YAML variants)
 * 13. Profile-specific application properties packaged inside your jar (application-{profile}.properties and YAML variants)
 * 14. Application properties outside of your packaged jar (application.properties and YAML variants).
 * 15. Application properties packaged inside your jar (application.properties and YAML variants).
 * 16. @PropertySource annotations on your @Configuration classes.
 * 17. Default properties (specified using SpringApplication.setDefaultProperties).
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@Configuration
@ConfigurationProperties(prefix = "my.config")
public class SimpleProperty {

    private String app;

    private String user;

    private int age;

    private String email;

    private String blog;

    private String github;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}
