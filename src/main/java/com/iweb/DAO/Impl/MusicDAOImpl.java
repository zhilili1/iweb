package com.iweb.DAO.Impl;

import com.iweb.DAO.MusicDAO;
import com.iweb.pojo.Music;
import com.iweb.pojo.Sc;
import com.iweb.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicDAOImpl implements MusicDAO {
    private QueryRunner qr =new QueryRunner(DruidUtil.getDataSource());
    @Override
    public List<Music> musicList() {
        List<Music> m=new ArrayList<>();
        String sql ="select * from music";
        try{
            m=qr.query(sql,new BeanListHandler<>(Music.class));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m.isEmpty()?null:m;
    }

    @Override
    public boolean add(Music music) {
        String sql ="insert into music(mid,name,author,src) values(?,?,?,?)";
        try {

            int count =qr.update(sql,music.getMid(),music.getName(),music.getAuthor(),music.getSrc());
            return count>0;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String mid) {
        String sql ="delete from music where mid=?";
        try{
            int count =qr.update(sql,mid);
            return count>0;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Music music) {
        String sql ="update music set name=?,author=?,src=? where mid=?";
        try{
            int count =qr.update(sql,music.getName(),music.getAuthor(),music.getSrc(),music.getMid());
            return count>0;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Music> musicAuthorList(String name) {
        List<Music> m =new ArrayList<>();
        String sql ="select * from music where name=?";

        try{
            m=qr.query(sql,new BeanListHandler<>(Music.class),name);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m.isEmpty()?null:m;
    }


    @Override
    public Music musicIDlist(String id) {
        Music m=null;
        String sql ="select * from music where mid=?";
        try{
            m=qr.query(sql,new BeanHandler<>(Music.class),id);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public Music musicList(String name, String author) {
        Music m=null;
        String sql ="select * from music where name=? and author=?";
        try{
            m=qr.query(sql,new BeanHandler<>(Music.class),name,author);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m;
    }
}
