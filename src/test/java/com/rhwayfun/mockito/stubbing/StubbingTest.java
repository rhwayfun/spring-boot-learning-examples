package com.rhwayfun.mockito.stubbing;

import com.google.common.collect.Lists;
import com.rhwayfun.doamin.User;
import com.rhwayfun.mockito.BaseMockitoTest;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
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

    @Test
    public void stubTest4() throws Exception{

        // A. Single mock whose methods must be invoked in a particular order
        List<String> singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        inOrder = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

}
