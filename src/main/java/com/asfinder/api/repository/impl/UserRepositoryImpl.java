package com.asfinder.api.repository.impl;

import com.asfinder.api.config.Persistence;
import com.asfinder.api.model.User;
import com.asfinder.api.repository.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getUsersByCountry(String countryKey){
        TypedQuery<User> query = em
                .createQuery(Persistence.USER_QUERY_GETUSERSBYCOUNTRY, User.class);
        query.setParameter("countryKey", countryKey);
        return query.getResultList();
    }


}
