package com.rhwayfun.springboot.quickstart.mockito;

import com.rhwayfun.springboot.quickstart.doamin.User;

import java.util.List;

/**
 * Created by chubin on 2017/3/26.
 */
public interface UserDAO {

    User findById(int id);

    void deleteById(int id);

    List<User> findLists(int... ids);

    boolean modifyUser(User user);

    boolean modifyUser(Integer id, String name, List<String> hobbies);

    User findByName(String name);

}
