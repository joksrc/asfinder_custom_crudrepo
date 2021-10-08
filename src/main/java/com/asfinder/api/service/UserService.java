package com.asfinder.api.service;

import com.asfinder.api.model.User;
import com.asfinder.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user) throws ExecutionException, InterruptedException {

        CompletableFuture<List<User>> userList =CompletableFuture.supplyAsync(() -> {
            System.out.println("addUser: "+Thread.currentThread());
            try {
                return this.getCheckedUsers();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        user.setCreationDate(new Date());
        userRepository.save(user);

    }

    public CompletableFuture<List<User>> userList = CompletableFuture.supplyAsync(() -> {
        System.out.println("getChekedUser into CompFuture: "+Thread.currentThread());
        return getListOfUsers();
    }).thenCompose(users -> {
        List<User> checkedUsers = null;
        try{
            TimeUnit.SECONDS.sleep(1);
            checkedUsers = users.get().stream().map(
                    user->{
                        user.setFirstName(user.getFirstName().toUpperCase());
                        user.setLastName(user.getLastName().toUpperCase());
                        return user;
                    }).collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Tarea terminada");
        return CompletableFuture.completedFuture(checkedUsers);

    });

    public List<User> getCheckedUsers() throws ExecutionException, InterruptedException {
        System.out.println("getChekedUser: "+Thread.currentThread());
        CompletableFuture<List<User>> userList = CompletableFuture.supplyAsync(() -> {
            System.out.println("getChekedUser into CompFuture: "+Thread.currentThread());
            return getListOfUsers();
                }).thenCompose(users -> {
            List<User> checkedUsers = null;
            try{
                TimeUnit.SECONDS.sleep(25);
                checkedUsers = users.get().stream().map(
                        user->{
                            user.setFirstName(user.getFirstName().toUpperCase());
                            user.setLastName(user.getLastName().toUpperCase());
                            return user;
                        }).collect(Collectors.toList());
            }catch(Exception e){
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture(checkedUsers);
        });
        //userList.get().forEach(System.out::println);
        for (User user : userList.get()) {
            System.out.println(user.getFirstName());
        }
        return userList.get();
    }

    private CompletableFuture<List<User>> getListOfUsers() {
        List<User> users = new ArrayList<>();

        //users = this.getUsers();
        users.add(new User("Jack", "Reacher", "mx"));
        users.add(new User("Remington", "Steele", "es"));
        users.add(new User("Laura", "Holt", "fr"));
        users.add(new User("Jonathan", "Raven", "it"));
        users.add(new User("Tom", "Hanson", "br"));
        users.add(new User("Alexander", "Scott", "eu"));
        users.add(new User("Jim", "Phelps", "cr"));

        return CompletableFuture.completedFuture(users);
    }
}
