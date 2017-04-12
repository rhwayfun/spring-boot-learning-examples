package com.rhwayfun.mockito.argsmatcher;

import com.rhwayfun.doamin.User;
import com.rhwayfun.mockito.BaseMockitoTest;
import org.junit.Test;
import org.testng.collections.Lists;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by chubin on 2017/4/9.
 */
public class ArgsMatcherTest extends BaseMockitoTest {

    @Test
    public void argsTest1() throws Exception{
        User user = new User("chubin",1, 24);

        when(mainService.getUser(anyInt())).thenReturn(user);

        System.out.println(mainService.getUser(1));
    }

    @Test
    public void argsTest2() throws Exception{
        when(mainService.modify(anyInt(), anyString(), anyList())).thenReturn(true);

        System.out.println(mainService.modify(1, "chubin", Lists.newArrayList("PingPangBall", "FootBall")));
    }

    @Test
    public void argsTest3() throws Exception{
        List<String> mock = mock(List.class);

        mock.add("hello");

        verify(mock).add(argThat(str -> str.length() > 3));
    }

    @Test
    public void argsTest4() throws Exception{
        User user = new User("chubin",1, 24);

        when(userDAO.findByName(eq("chubin"))).thenReturn(user);

        System.out.println(userDAO.findByName("chubin"));

        verify(userDAO).findByName(eq("chubin"));
    }

}
