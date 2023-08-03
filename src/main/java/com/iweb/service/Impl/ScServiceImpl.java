package com.iweb.service.Impl;

import com.iweb.DAO.Impl.ScDAOImpl;
import com.iweb.DAO.ScDAO;
import com.iweb.pojo.Sc;
import com.iweb.service.ScService;
import com.iweb.util.UUIDUtil;

import java.util.List;

public class ScServiceImpl implements ScService {
    private ScDAO scDAO =new ScDAOImpl();
    @Override
    public boolean addSc(Sc sc) {
        sc.setSid(UUIDUtil.uuid());
        Boolean isHave = scDAO.verifyUidAndMid(sc.getUid(),sc.getMid());
        if (isHave)
        {
            return false;
        }
        return scDAO.addSc(sc);
    }

    @Override
    public boolean delete(String uid,String mid) {
        return scDAO.delete(uid,mid);
    }

    @Override
    public List<Sc> list(String uid) {
        return scDAO.list(uid);
    }

    @Override
    public boolean deleteId(String mid) {
        return scDAO.deleteId(mid);
    }
}
