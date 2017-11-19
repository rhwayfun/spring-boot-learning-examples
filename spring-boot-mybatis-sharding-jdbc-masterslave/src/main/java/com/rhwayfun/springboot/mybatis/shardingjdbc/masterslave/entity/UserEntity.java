package com.rhwayfun.springboot.mybatis.shardingjdbc.masterslave.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类，纯POJO
 *
 * @author rhwayfun
 * @since 0.0.1
 */
public class UserEntity implements Serializable {

    private Integer id;

    private Long userId;

    private Integer cityId;

    private String userName;

    private Integer age;

    private Date birth;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", cityId=" + cityId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                '}';
    }
}