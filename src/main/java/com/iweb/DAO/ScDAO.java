package com.iweb.DAO;

import com.iweb.pojo.Sc;

import java.util.List;

public interface ScDAO {
    boolean addSc(Sc sc);
    boolean delete(String uid,String mid);
    boolean deleteId(String mid);
    boolean verifyUidAndMid(String uid,String mid);
    List<Sc> list(String uid);
}
