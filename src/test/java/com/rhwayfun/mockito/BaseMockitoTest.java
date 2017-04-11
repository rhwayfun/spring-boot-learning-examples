package com.rhwayfun.mockito;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by chubin on 2017/4/9.
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseMockitoTest {

    @InjectMocks
    protected UserService userService;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    protected MainService mainService;

    @Before
    public void setUp(){
        mainService.setUserService(userService);
    }
}
