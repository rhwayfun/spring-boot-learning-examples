package com.rhwayfun.mockito.stubbing;

import com.google.common.collect.Lists;
import com.rhwayfun.doamin.User;
import com.rhwayfun.mockito.BaseMockitoTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by ZhongCB on 2017/4/5.
 */
public class StubbingTest extends BaseMockitoTest {

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
