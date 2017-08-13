package com.rhwayfun.springboot.controller;

import com.google.common.collect.Lists;
import com.rhwayfun.springboot.controller.rest.MyException;
import com.rhwayfun.springboot.controller.rest.MyRestResponse;
import com.rhwayfun.springboot.doamin.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chubin on 2017/2/12.
 */
@Controller
public class WelcomeController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @RequestMapping(value = {"/", "/index"})
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin")
    public @ResponseBody String admin(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        model.addAttribute("msg", "admin用户才有权限哦");
        return model.toString();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public @ResponseBody
    List<User> list(@RequestParam(required = false) String name, @RequestParam(required = false) int age){
        List<User> users = new ArrayList<>();
        User user1 = new User("user1", 1, 10, Lists.newArrayList("football"));
        User user2 = new User("user2", 2, 20, Lists.newArrayList("baseball"));
        User user3 = new User("user3", 3, 30, Lists.newArrayList("basketball"));
        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }

    @RequestMapping("/fail")
    public String fail() {
        throw new MyException("Oh dear!");
    }

    @RequestMapping("/fail2")
    public String fail2() {
        throw new IllegalStateException();
    }

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    MyRestResponse handleMyRuntimeException(MyException exception) {
        return new MyRestResponse("Some data I want to send back to the client.");
    }
}
