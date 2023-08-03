package com.iweb.DAO.Impl;

import com.iweb.DAO.SpeakDAO;
import com.iweb.pojo.Speak;
import com.iweb.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpeakDAOImpl implements SpeakDAO {
    private QueryRunner qr =new QueryRunner(DruidUtil.getDataSource());
    @Override
    public List<Speak> list(String mid) {
        List<Speak> m=new ArrayList<>();
        String sql ="select * from speak where mid=?";
        try{
            m=qr.query(sql,new BeanListHandler<>(Speak.class),mid);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m.isEmpty()?null:m;
    }

    @Override
    public boolean add(Speak speak) {

        String sql ="insert into speak(spid,uid,mid,contain) values(?,?,?,?)";
        try{
            int count = qr.update(sql,speak.getSpid(),speak.getUid(),speak.getMid(),speak.getContain());
            return count>0;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String spid) {
        String sql ="delete from speak where spid=?";
        try{
            int count=qr.update(sql,spid);
            return count>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Speak> select(String uid,String mid) {
        List<Speak> m=new ArrayList<>();
        String sql ="select * from speak where uid=? and mid=?";
        try{
            m=qr.query(sql,new BeanListHandler<>(Speak.class),uid,mid);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m.isEmpty()?null:m;
    }
}
