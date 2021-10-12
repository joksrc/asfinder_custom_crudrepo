package com.asfinder.api.config;

public class Persistence {

    public final static String USER_QUERY_GETUSERSBYCOUNTRY = "select u from User u where u.country =: countryKey ";
}
