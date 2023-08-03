package com.iweb.DAO;

import com.iweb.pojo.Speak;

import java.util.List;

public interface SpeakDAO {
    List<Speak> list(String mid);
    boolean add(Speak speak);
    boolean delete(String spid);
    List<Speak> select(String uid,String mid);

}
