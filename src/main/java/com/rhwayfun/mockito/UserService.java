package com.rhwayfun.mockito;

import com.rhwayfun.doamin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chubin on 2017/3/26.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public User findById(int id){
        return userDAO.findById(id);
    }

    public void deleteById(int id){
        userDAO.deleteById(id);
    }

    public List<User> findLists(int ... ids){
        return userDAO.findLists(ids);
    }

    public boolean modifyUser(User user){
        return userDAO.modifyUser(user);
    }

    public boolean modifyUser(Integer id, String name, List<String> hobbies){
        return userDAO.modifyUser(id, name, hobbies);
    }

    public User findByName(String name) {
        return userDAO.findByName(name);
    }
}
