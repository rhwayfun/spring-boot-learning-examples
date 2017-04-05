package com.rhwayfun.mockito.stubbing;

import com.google.common.collect.Lists;
import com.rhwayfun.doamin.User;
import com.rhwayfun.mockito.MainService;
import com.rhwayfun.mockito.UserDAO;
import com.rhwayfun.mockito.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ZhongCB on 2017/4/5.
 */
@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private MainService mainService;

    @Before
    public void setUp(){
        mainService.setUserService(userService);
    }

    @Test
    public void stubTest1() throws Exception{
        User user = new User();
        user.setAge(2);
        user.setId(1);
        user.setHobbies(Lists.newArrayList("足球"));

        when(mainService.getUser(anyInt())).thenReturn(user);

        assertEquals(mainService.getUser(1), user);
    }

    @Test
    public void stubTest2() throws Exception{
        User user = new User();
        user.setAge(2);
        user.setId(1);
        user.setHobbies(Lists.newArrayList("足球"));

        User user2 = new User();
        user.setAge(2);
        user.setId(2);
        user.setHobbies(Lists.newArrayList("篮球"));

        when(mainService.getUser(anyInt())).thenReturn(user);
        when(mainService.getUser(anyInt())).thenReturn(user2);
        assertEquals(mainService.getUser(1), user2);
    }

    @Test
    public void stubTest3() throws Exception{
        User user = new User();
        user.setAge(2);
        user.setId(1);
        user.setHobbies(Lists.newArrayList("足球"));

        when(mainService.getUser(1)).thenReturn(user);
        when(mainService.getUser(2)).thenThrow(new RuntimeException("不存在"));

        assertEquals(mainService.getUser(1), user);
        System.out.println(mainService.getUser(2));
        assertEquals(mainService.getUser(3), null);
    }

}
