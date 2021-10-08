package com.asfinder.api.controller;

import com.asfinder.api.model.User;
import com.asfinder.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/userController")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public List<User> addUser(@RequestBody User user) throws Exception {

        userService.addUser(user);
        return userService.getUsers();
    }

}
