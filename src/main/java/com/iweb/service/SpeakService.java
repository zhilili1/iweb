package com.iweb.service;

import com.iweb.pojo.Speak;

import java.util.List;

public interface SpeakService {
    List<Speak> list(String mid);
    boolean add(Speak speak);
    List<Speak> select(String uid,String mid);
    boolean delete(String spid);
}
