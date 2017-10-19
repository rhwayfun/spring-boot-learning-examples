package com.rhwayfun;

import com.rhwayfun.springboot.quickstart.doamin.User;
import com.rhwayfun.springboot.quickstart.mockito.UserDAO;
import com.rhwayfun.springboot.quickstart.mockito.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by chubin on 2017/3/26.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserDAO userDAO;

    @Test
    public void testFindUserById() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        User user = new User("Tom", 1, 13);
        userDAO.modifyUser(user);
        verify(userDAO).modifyUser(captor.capture());

        // 获取捕获的参数
        assertEquals(captor.getValue().getId(), 1);
        assertEquals(captor.getValue().getAge(), 13);
        assertEquals(captor.getValue().getName(), "Tom");

        // 添加条件返回值
        when(userDAO.findById(anyInt())).thenReturn(user);
        assertEquals(userDAO.findById(1).getId(), 1);

        // 测试是否相等
        assertEquals(userService.findById(1).getId(), 1);
        assertEquals(userService.findById(1).getAge(), 13);
        assertEquals(userService.findById(1).getName(), "Tom");
    }

}
