package com.rhwayfun.springboot.quickstart.mockito;

import com.rhwayfun.springboot.quickstart.doamin.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by chubin on 2017/4/16.
 */
@Repository
public class UserDAOImpl implements UserDAO{

    @Override
    public User findById(int id) {
        return new User("Tim", 2, 10);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<User> findLists(int... ids) {
        return null;
    }

    @Override
    public boolean modifyUser(User user) {
        return false;
    }

    @Override
    public boolean modifyUser(Integer id, String name, List<String> hobbies) {
        return false;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    public final Date getNow() {
        return new Date();
    }
}