package com.rhwayfun.springboot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author happyxiaofan
 * @since 0.0.1
 */
@ConfigurationProperties(prefix = "demo.user")
public class DemoProperties {

    private String id;

    private String name;

    private int age;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
