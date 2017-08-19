package com.rhwayfun.springboot.security.service;

import com.rhwayfun.springboot.security.dao.UserRepository;
import com.rhwayfun.springboot.security.datasource.model.User;
import com.rhwayfun.springboot.security.datasource.model.UserAuthority;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chubin on 2017/8/13.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username can't be null!");
        }
        User user;
        try {
            user = userRepository.findByUserName(username);
        } catch (Exception e) {
            log.error("读取用户信息异常，", e);
            return null;
        }
        if (user == null) {
            return null;
        }
        List<UserAuthority> roles = userRepository.findRoles(user.getUserId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (UserAuthority role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(role.getAuthId()));
            authorities.add(authority);
        }
        return new org.springframework.security.core.userdetails.User(username, "1234", authorities);
    }


}
