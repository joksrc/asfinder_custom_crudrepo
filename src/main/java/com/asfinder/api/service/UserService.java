package com.asfinder.api.service;

import com.asfinder.api.model.User;
import com.asfinder.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user) throws Exception {

        user.setCreationDate(new Date());
        userRepository.save(user);
        /*boolean exist = userRepository.selectExistsUsername(user.getUserName());
        if(exist){
            throw new Exception("Username "+user.getUserName()+" already exist");
        }else{
            user.setCreationDate(new Date());
            userRepository.save(user);
        }*/
    }
}
