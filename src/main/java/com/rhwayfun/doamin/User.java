package com.rhwayfun.doamin;

import lombok.Data;

import java.util.List;

/**
 * Created by chubin on 2017/2/12.
 */
@Data
public class User {
    private String name;
    private int id;
    private int age;
    private List<String> hobbies;

    public User(){}

    public User(String name, int id, int age, List<String> hobbies) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.hobbies = hobbies;
    }

    public User(int id) {
        this.id = id;
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }
}
