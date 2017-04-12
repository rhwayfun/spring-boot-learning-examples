package com.rhwayfun.mockito;

import com.rhwayfun.doamin.User;
import com.rhwayfun.mockito.UserDAO;
import com.rhwayfun.mockito.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.spy;

/**
 * Created by chubin on 2017/3/26.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceSpyTest {


    private UserService userService;
    private UserDAO userDAO;

    @Before
    public void setUp(){
        userService = new UserService();
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        userDAO = spy(userDAOImpl);
        userService.setUserDAO(userDAO);
    }

    @Test
    public void testSpy(){
        // 使用真实对象执行
        Assert.assertEquals(userService.findById(2).getName(), "Tim");
    }

    private static class UserDAOImpl implements UserDAO{

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
    }

}
