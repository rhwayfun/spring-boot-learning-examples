package com.rhwayfun.springboot.quickstart.mockito;

import com.rhwayfun.springboot.quickstart.doamin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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

    public User getUser(String name){
        try {
            return userService.findByName(name);
        } catch (Exception e){
            log.error("occurs error:{}", name, e);
            return null;
        }
    }

    public boolean modify(Integer id, String name, List hobbies){
        boolean modify = userService.modifyUser(id, name, hobbies);
        log.info("modify user--:{},name,hobbies:{}", id, name, hobbies);
        return modify;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
