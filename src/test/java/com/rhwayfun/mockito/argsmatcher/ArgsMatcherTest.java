package com.rhwayfun.mockito.argsmatcher;

import com.rhwayfun.doamin.User;
import com.rhwayfun.mockito.BaseMockitoTest;
import org.junit.Test;
import org.testng.collections.Lists;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by chubin on 2017/4/9.
 */
public class ArgsMatcherTest extends BaseMockitoTest {

    @Test
    public void argsTest1() throws Exception{
        // mock data
        User user = new User("chubin",1, 24);

        // stub
        when(mainService.getUser(anyInt())).thenReturn(user);

        // result
        System.out.println(mainService.getUser(1));
    }

    @Test
    public void argsTest2() throws Exception{
        when(mainService.modify(anyInt(), anyString(), anyList())).thenReturn(true);

        System.out.println(mainService.modify(1, "chubin", Lists.newArrayList("PingPangBall", "FootBall")));
    }
}
