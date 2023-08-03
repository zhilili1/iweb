package com.iweb.service;

import com.iweb.pojo.Sc;

import java.util.List;

public interface ScService {
    boolean addSc(Sc sc);
    boolean delete(String uid,String mid);
    List<Sc> list(String uid);
    boolean deleteId(String mid);
}
