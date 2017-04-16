package com.rhwayfun;

import org.junit.Test;

import java.util.List;

import static com.rhwayfun.mockito.Mockito.mock;
import static com.rhwayfun.mockito.Mockito.when;

/**
 * Created by chubin on 2017/4/16.
 */
public class MyMockitoTest {

    @Test
    public void test() throws Exception{
        List mock = mock(List.class);
        when(mock.get(0)).thenReturn("hello");
        System.out.println(mock.get(0));
    }

}
