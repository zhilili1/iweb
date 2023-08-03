package com.iweb.service;

import com.iweb.pojo.User;

public interface UserService {
    boolean add(User user);
    User login(User user);
    boolean verifyUsername(String username);
}
