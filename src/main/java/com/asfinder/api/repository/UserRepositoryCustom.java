package com.asfinder.api.repository;

import com.asfinder.api.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> getUsersByCountry(String coutryKey);
}
