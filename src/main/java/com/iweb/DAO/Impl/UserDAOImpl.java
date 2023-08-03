package com.iweb.DAO.Impl;

import com.iweb.util.DruidUtil;
import com.iweb.DAO.UserDAO;
import com.iweb.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private QueryRunner qr =new QueryRunner(DruidUtil.getDataSource());
    @Override
    public boolean add(User user) {
        String sql ="insert into m_user(id,username,password) values(?,?,?)";
        try{
            int count = qr.update(sql,user.getId(),user.getUsername(),user.getPassword());
            return count>0;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User login(User user) {
        String sql ="select * from m_user where username=? and password=?";
        try{
            user = qr.query(sql,new BeanHandler<>(User.class),user.getUsername(),user.getPassword());
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean verifyUsername(String username) {
        String sql ="select count(*) from m_user where username=?";
        try{
            Number count = (Number) qr.query(sql,new ScalarHandler<>(),username);
            return count.intValue()>0;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
