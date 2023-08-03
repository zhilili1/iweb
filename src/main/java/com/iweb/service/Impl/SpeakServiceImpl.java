package com.iweb.service.Impl;

import com.iweb.DAO.Impl.SpeakDAOImpl;
import com.iweb.DAO.SpeakDAO;
import com.iweb.pojo.Speak;
import com.iweb.service.SpeakService;
import com.iweb.util.UUIDUtil;

import java.util.List;

public class SpeakServiceImpl implements SpeakService {
    private SpeakDAO speakDAO =new SpeakDAOImpl();
    @Override
    public List<Speak> list(String mid) {
        return speakDAO.list(mid);
    }
    @Override
    public boolean add(Speak speak) {
        speak.setSpid(UUIDUtil.uuid());
        return speakDAO.add(speak);
    }

    @Override
    public List<Speak> select(String uid, String mid) {
        return speakDAO.select(uid, mid);
    }

    @Override
    public boolean delete(String spid) {
        return speakDAO.delete(spid);
    }
}
