package com.rhwayfun.springboot.security.datasource.dao.impl;

import com.rhwayfun.springboot.security.datasource.dao.UserRepository;
import com.rhwayfun.springboot.security.datasource.mapper.UserAuthorityMapper;
import com.rhwayfun.springboot.security.datasource.mapper.UserMapper;
import com.rhwayfun.springboot.security.datasource.model.User;
import com.rhwayfun.springboot.security.datasource.model.UserAuthority;
import com.rhwayfun.springboot.security.datasource.model.UserAuthorityExample;
import com.rhwayfun.springboot.security.datasource.model.UserExample;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chubin on 2017/8/13.
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Override
    public User findByUserName(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        return !CollectionUtils.isEmpty(users) ? users.get(0) : null;
    }

    @Override
    public List<UserAuthority> findRoles(int userId) {
        UserAuthorityExample example = new UserAuthorityExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userAuthorityMapper.selectByExample(example);
    }
}
