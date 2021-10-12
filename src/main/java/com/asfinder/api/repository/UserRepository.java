package com.asfinder.api.repository;

import com.asfinder.api.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
