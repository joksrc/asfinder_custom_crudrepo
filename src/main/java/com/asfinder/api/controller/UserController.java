package com.asfinder.api.controller;

import com.asfinder.api.model.User;
import com.asfinder.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/userController")
public class UserController {

    //@Autowired
    //UserRepository userRepository;

    @Autowired
    UserService userService;

    /*@Autowired
    public UserController(UserRepository userRepository, UserService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }*/

    /*
    @RequestMapping("/getUser/{idUser}")
    public User getUser(@PathVariable final Integer idUser){

        return userRepository.findById(idUser);
    }

    @RequestMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable final Integer idUser){
        userRepository.delete(idUser);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public void updateUser(@RequestBody final User user){
        userRepository.save(user);
    }*/

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public List<User> addUser(@RequestBody User user) throws Exception {

        System.out.println("breakpoint");
        userService.addUser(user);

        return userService.getUsers();
    }

}
