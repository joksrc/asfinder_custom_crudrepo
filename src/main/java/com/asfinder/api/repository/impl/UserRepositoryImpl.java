package com.asfinder.api.repository.impl;

import com.asfinder.api.repository.UserRepository;
import com.asfinder.api.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User, Integer> implements UserRepository {
}
