package com.iweb.service;

import com.iweb.pojo.Music;
import com.iweb.pojo.Sc;

import java.util.List;

public interface MusicService {
    List<Music> musicList();
    boolean add(Music music);
    boolean delete(String mid);
    boolean update(Music music);
    List<Music> musicAuthorList(String name);
    Music musicIDlist(String id);
}
