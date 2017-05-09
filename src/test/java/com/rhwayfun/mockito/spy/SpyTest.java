package com.rhwayfun.mockito.spy;

import com.rhwayfun.mockito.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by chubin on 2017/4/15.
 */
public class SpyTest extends BaseMockitoTest{

    @Test
    public void spyTest1() throws Exception{
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(1));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    @Test
    public void spyTest2() throws Exception{
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        //makes a real method call on the spy
        //while doReturn not
        //when(spy.get(0)).thenReturn("Tim");
        doReturn("Tim").when(spy).get(0);

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

    }

    @Test
    public void spyTest3() throws Exception{
        List list = new ArrayList();
        List spy = spy(list);

        list.add("one");
        list.add("three");

        spy.add("two");

        //doReturn("two").when(spy).get(0);

        System.out.println(spy.get(0));
        System.out.println(list.get(1));
    }

    @Test
    public void spyTest4() throws Exception{
        UserDAOImpl userDAOImpl = new UserDAOImpl();

        UserDAOImpl spy = spy(userDAOImpl);

        when(spy.getNow()).thenReturn(new Date(System.currentTimeMillis() + 1000));

        System.out.println(spy.getNow());
        System.out.println(userDAOImpl.getNow());
    }
    
    @Test
    public void spyTest5() throws Exception{
        EmailVerification spy = spy(EmailVerification.class);

        spy.verifyEmail("zhongchubin@dianwoba2017.com");//false
        spy.verifyEmail("zhongchubin@dianwoba.com");//true

        // 修改默认行为
        when(spy.verifyEmail(anyString())).thenReturn(true);

        spy.verifyEmail("zhongchubin@dianwoba.com2017");//true
        spy.verifyEmail("zhongchubin@dianwoba.com");//true

        verify(spy).verifyEmail("zhongchubin@dianwoba2017.com");//pass
    }
}
