package com.rhwayfun.springboot.security.datasource.domain;

import com.rhwayfun.springboot.security.datasource.model.User;
import com.rhwayfun.springboot.security.datasource.model.UserAuthority;

import java.util.List;

/**
 * Created by chubin on 2017/8/13.
 */
public class UserRole {

    private User user;
    private List<UserAuthority> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserAuthority> getRoles() {
        return roles;
    }

    public void setRoles(List<UserAuthority> roles) {
        this.roles = roles;
    }
}
