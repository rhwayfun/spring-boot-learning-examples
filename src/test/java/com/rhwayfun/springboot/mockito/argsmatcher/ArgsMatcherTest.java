package com.rhwayfun.springboot.mockito.argsmatcher;

import com.rhwayfun.springboot.doamin.User;
import com.rhwayfun.springboot.mockito.BaseMockitoTest;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.testng.collections.Lists;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Matchers.argThat;
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

    @Test
    public void argsTest5(){
        //创建mock对象
        List<String> mock = mock(List.class);

        //argThat(Matches<T> matcher)方法用来应用自定义的规则，可以传入任何实现Matcher接口的实现类。
        when(mock.addAll(argThat(new IsValid()))).thenReturn(true);

        mock.addAll(Arrays.asList("one","two"));
        //IsListofTwoElements用来匹配size为2的List，因为例子传入List为三个元素，所以此时将失败。
        verify(mock).addAll(argThat(new IsValid()));
    }

    class IsValid implements ArgumentMatcher<List> {
        @Override
        public boolean matches(List argument) {
            return argument.size() == 2;
        }
    }

}
