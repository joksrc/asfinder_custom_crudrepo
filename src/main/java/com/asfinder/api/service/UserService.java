package com.asfinder.api.service;

import com.asfinder.api.model.User;
import com.asfinder.api.repository.UserRepository;
import com.asfinder.api.repository.UserRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;

    public List<User> getUsersByCountry(String countryKey){
        return userRepositoryCustom.getUsersByCountry(countryKey);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
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

    public List<User> getCheckedUsers() throws ExecutionException, InterruptedException {
        System.out.println("getChekedUser: "+Thread.currentThread());
        CompletableFuture<List<User>> userList = CompletableFuture.supplyAsync(() -> this.getListOfUsers())
                .thenCompose(users -> {
            List<User> checkedUsers = null;
            try{
                TimeUnit.SECONDS.sleep(10);
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

        for (User user : userList.get()) {
            System.out.println(user.getIdUser()+" - "+user.getFirstName());
        }
        return userList.get();
    }

    private CompletableFuture<List<User>> getListOfUsers() {
        List<User> users;

        users = this.getUsers();
        return CompletableFuture.completedFuture(users);
    }
}
