package com.rhwayfun.mockito;

import com.rhwayfun.doamin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ZhongCB on 2017/4/5.
 */
@Component
public class MainService {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(MainService.class);

    @Resource
    private UserService userService;

    public User getUser(Integer userId){
        try {
            return userService.findById(userId);
        } catch (Exception e){
            log.error("occurs error:{}", userId, e);
            return null;
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
