package com.asfinder.api.controller;

import com.asfinder.api.model.User;
import com.asfinder.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userController")
public class UserController {
    private UserService userService;

    UserController (UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public List<User> addUser(@RequestBody User user) throws Exception {

        userService.addUser(user);
        return userService.getUsers();
    }

}
