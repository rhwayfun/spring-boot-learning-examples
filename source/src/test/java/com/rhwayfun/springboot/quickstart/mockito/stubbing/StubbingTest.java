package com.rhwayfun.springboot.quickstart.mockito.stubbing;

import com.google.common.collect.Lists;
import com.rhwayfun.springboot.quickstart.doamin.User;
import com.rhwayfun.springboot.quickstart.mockito.BaseMockitoTest;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

        //create inOrder memory passing any mocks that need to be verified in order
        inOrder = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    @Test
    public void stubTest5() throws Exception{
        List mockList = mock(List.class);

        when(mockList.get(Integer.MAX_VALUE)).thenAnswer(invocation -> {
            Object mock = invocation.getMock();
            Object[] arguments = invocation.getArguments();
            return new StringBuilder().append(mock).append(",").append(Arrays.asList(arguments)).toString();
        });

        System.out.println(mockList.get(Integer.MAX_VALUE));

        verify(mockList).get(Integer.MAX_VALUE);
    }

    @Test
    public void returnsSmartNullsTest() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));

        //使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常。另外控制台窗口会提示信息“SmartNull returned by unstubbed get() method on mock”
        System.out.println(mock.toArray().length);
    }

    @Test
    public void deepstubsTest(){
        Account account=mock(Account.class,RETURNS_DEEP_STUBS);
        when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing",account.getRailwayTicket().getDestination());
    }
    @Test
    public void deepstubsTest2(){
        Account account=mock(Account.class);
        RailwayTicket railwayTicket=mock(RailwayTicket.class);
        when(account.getRailwayTicket()).thenReturn(railwayTicket);
        when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing",account.getRailwayTicket().getDestination());
    }

    public class RailwayTicket{
        private String destination;

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }

    public class Account{
        private RailwayTicket railwayTicket;

        public RailwayTicket getRailwayTicket() {
            return railwayTicket;
        }

        public void setRailwayTicket(RailwayTicket railwayTicket) {
            this.railwayTicket = railwayTicket;
        }
    }
}
