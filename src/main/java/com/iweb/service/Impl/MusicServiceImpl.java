package com.iweb.service.Impl;

import com.iweb.DAO.Impl.MusicDAOImpl;
import com.iweb.DAO.Impl.ScDAOImpl;
import com.iweb.DAO.MusicDAO;
import com.iweb.DAO.ScDAO;
import com.iweb.pojo.Music;
import com.iweb.pojo.Sc;
import com.iweb.service.MusicService;
import com.iweb.service.ScService;
import com.iweb.util.MD5Util;
import com.iweb.util.UUIDUtil;

import java.util.List;

public class MusicServiceImpl implements MusicService {
    private MusicDAO musicDAO =new MusicDAOImpl();
    private ScDAO scDAO =new ScDAOImpl();

    @Override
    public List<Music> musicList() {
        return musicDAO.musicList();
    }

    @Override
    public boolean add(Music music) {
        Music m =musicDAO.musicList(music.getName(),music.getAuthor());
        {
            if (m!=null)
            {
                return false;
            }
        }
        music.setMid(UUIDUtil.uuid());
        return musicDAO.add(music);
    }

    @Override
    public boolean delete(String mid) {
        scDAO.deleteId(mid);
        return musicDAO.delete(mid);
    }

    @Override
    public boolean update(Music music) {
        return musicDAO.update(music);
    }

    @Override
    public List<Music> musicAuthorList(String name) {
        return musicDAO.musicAuthorList(name);
    }

    @Override
    public Music musicIDlist(String id) {
        return musicDAO.musicIDlist(id);
    }
}
