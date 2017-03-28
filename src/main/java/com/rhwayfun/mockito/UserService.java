package com.rhwayfun.mockito;

import com.rhwayfun.doamin.User;

import java.util.List;

/**
 * Created by chubin on 2017/3/26.
 */
public class UserService {

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

}
