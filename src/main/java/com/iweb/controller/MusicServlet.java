package com.iweb.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.pojo.Music;
import com.iweb.pojo.ResultVO;
import com.iweb.pojo.Sc;
import com.iweb.pojo.User;
import com.iweb.service.Impl.MusicServiceImpl;
import com.iweb.service.Impl.UserServiceImpl;
import com.iweb.service.MusicService;
import com.iweb.util.FormBeanUtil;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/music")
public class MusicServlet extends BaseServlet{
    private MusicService musicService = new MusicServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Music> musicList =musicService.musicList();
        if (musicList==null)
        {
            return;
        }
        resp.getWriter().write(JSONUtil.toJsonStr(musicList));
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data =req.getParameter("data");
        Music music =JSONUtil.toBean(data,Music.class);
        ResultVO vo =new ResultVO();
        Boolean isadd= musicService.add(music);
        if (isadd)
        {
            vo.setOk(true);
            vo.setMess("添加成功");
        }
        else
        {
            vo.setMess("添加失败,歌曲已存在,请重新输入");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(vo));
    }
    public void musicAuthor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name =req.getParameter("name");
        List<Music>musicList=musicService.musicAuthorList(name);
        resp.getWriter().write(JSONUtil.toJsonStr(musicList));
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ResultVO vo =new ResultVO();
        String mid =req.getParameter("mid");
        boolean isdelete=musicService.delete(mid);
        {
            if(isdelete)
            {
                vo.setOk(true);
                vo.setMess("删除成功");
            }
            else
            {
                vo.setMess("删除失败");
            }
        }
        resp.getWriter().write(JSONUtil.toJsonStr(vo));
    }

}
