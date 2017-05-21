package com.rhwayfun.springboot.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.internal.matchers.Contains;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by chubin on 2017/3/26.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    private List mockList;

    @Test
    public void testStub(){
        when(mockList.get(0)).thenReturn(1);
        System.out.println(mockList.get(0));
    }

    @Test
    public void testVerify(){
        mockList.add(1);
        mockList.add(2);
        verify(mockList, times(1)).add(1);
        verify(mockList, times(1)).add(2);
    }

    @Test
    public void testArgsMatch(){
        when(mockList.get(anyInt())).thenReturn("Hello Chubin");
        when(mockList.contains(argThat(new Contains("Chubin")))).thenReturn(true);


        assertEquals(mockList.get(1), "Hello Chubin");
        assertTrue(mockList.contains("Chubin"));

        verify(mockList).get(anyInt());
    }

    @Test
    public void testException(){
        when(mockList.get(Integer.MAX_VALUE)).thenThrow(new ArrayIndexOutOfBoundsException("下标越界！"));
        System.out.println(mockList.get(Integer.MAX_VALUE));
    }

    @Test
    public void testOrder(){
        List simpleList1 = mock(List.class);
        List simpleList2 = mock(List.class);

        simpleList1.add("first call");
        simpleList2.add("second call");

        InOrder inOrder = inOrder(simpleList1, simpleList2);

        //验证调用顺序
        inOrder.verify(simpleList1).add("first call");
        inOrder.verify(simpleList2).add("second call");

    }

    @Test
    public void testCallBack(){
        when(mockList.get(Integer.MAX_VALUE)).thenAnswer(invocation -> {
            Object mock = invocation.getMock();
            Object[] arguments = invocation.getArguments();
            return new StringBuilder().append(mock).append(",").append(Arrays.asList(arguments)).toString();
        });

        System.out.println(mockList.get(Integer.MAX_VALUE));
        verify(mockList).get(Integer.MAX_VALUE);
    }

}
