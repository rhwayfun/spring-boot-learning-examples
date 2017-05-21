package com.rhwayfun.springboot.mockito;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by chubin on 2017/4/9.
 */
@RunWith(MockitoJUnitRunner.class) // 使用这个注解在使用@Mock注解创建的对象时不会NPE
public abstract class BaseMockitoTest {

    @InjectMocks
    protected UserService userService;

    @Mock
    protected UserDAO userDAO;

    @InjectMocks
    protected MainService mainService;

    @Before
    public void setUp(){
        mainService.setUserService(userService);
    }
}
