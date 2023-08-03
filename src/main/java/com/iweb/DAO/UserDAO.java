package com.iweb.DAO;

import com.iweb.pojo.User;

import java.util.List;

public interface UserDAO {
  boolean add(User user);
  User login(User user);
  boolean verifyUsername(String username);

}
