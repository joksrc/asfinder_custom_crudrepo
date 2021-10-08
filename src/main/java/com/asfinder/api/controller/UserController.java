package com.asfinder.api.controller;

import com.asfinder.api.model.User;
import com.asfinder.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/userController")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public List<User> addUser(@RequestBody User user) throws Exception {
        //System.out.println(Thread.currentThread());
        //CompletableFuture<List<User>> userList = userService.userList;

        //CompletableFuture<List<User>> userList =CompletableFuture.completedFuture(userService.getCheckedUsers());
        //CompletableFuture<List<User>> userList =CompletableFuture.supplyAsync(() ->userService.getCheckedUsers());
        //System.out.println(Thread.currentThread());
        userService.addUser(user);
        //userService.getCheckedUsers();
        return userService.getUsers();
    }

    /*@RequestMapping(value = "/getCheckedUser", method = RequestMethod.GET)
    public void getCheckedUser() throws ExecutionException, InterruptedException {
        userService.getCheckedUsers();
    }*/

}
