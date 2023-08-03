package com.iweb.DAO.Impl;

import com.iweb.DAO.ScDAO;
import com.iweb.pojo.Sc;
import com.iweb.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScDAOImpl implements ScDAO {
    private QueryRunner qr =new QueryRunner(DruidUtil.getDataSource());
    @Override
    public boolean addSc(Sc sc) {
        String sql ="insert into sc(sid,uid,mid) values(?,?,?)";
        try {

            int count =qr.update(sql,sc.getSid(),sc.getUid(),sc.getMid());
            return count>0;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(String uid,String mid) {
        String sql ="delete from sc where uid=? and mid=?";
        try{
            int count=qr.update(sql,uid,mid);
            return count>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean verifyUidAndMid(String uid,String mid) {
        String sql ="select count(*) from sc where uid=? and mid=?";
        try {
            Number count = (Number) qr.query(sql,new ScalarHandler<>(),uid,mid);
            return count.intValue()>0;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Sc> list(String uid) {
        String sql ="select * from sc where uid=?";
        List<Sc> scList =new ArrayList<>();

            try{
                scList=qr.query(sql,new BeanListHandler<>(Sc.class),uid);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        return scList.isEmpty()?null:scList;
    }

    @Override
    public boolean deleteId(String mid) {
        String sql ="delete from sc where mid=?";
        try{
            int count=qr.update(sql,mid);
            return count>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
