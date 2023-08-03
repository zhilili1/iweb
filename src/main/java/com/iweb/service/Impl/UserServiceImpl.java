package com.iweb.service.Impl;

import com.iweb.DAO.Impl.UserDAOImpl;
import com.iweb.DAO.UserDAO;
import com.iweb.pojo.User;
import com.iweb.service.UserService;
import com.iweb.util.MD5Util;
import com.iweb.util.UUIDUtil;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO =new UserDAOImpl();
    @Override
    public boolean add(User user) {
        user.setId(UUIDUtil.uuid());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        return userDAO.add(user);
    }

    @Override
    public User login(User user) {
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        return userDAO.login(user);
    }

    @Override
    public boolean verifyUsername(String username) {
        return userDAO.verifyUsername(username);
    }
}
