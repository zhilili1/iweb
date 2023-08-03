package com.iweb.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.pojo.Music;
import com.iweb.pojo.ResultVO;
import com.iweb.pojo.Sc;
import com.iweb.pojo.User;
import com.iweb.service.Impl.MusicServiceImpl;
import com.iweb.service.Impl.ScServiceImpl;
import com.iweb.service.MusicService;
import com.iweb.service.ScService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sc")
public class ScServlet extends BaseServlet {
    private MusicService musicService =new MusicServiceImpl();
    private ScService scService =new ScServiceImpl();
    public void addlove(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        User user = (User) req.getSession().getAttribute("user");
        String  mid =req.getParameter("mid");
        Sc sc =new Sc();
        sc.setUid(user.getId());
        sc.setMid(mid);
        ResultVO vo =new ResultVO();
        Boolean isadd= scService.addSc(sc);
        if (isadd)
        {
            vo.setOk(true);
            vo.setMess("收藏成功");
        }
        else
        {
            vo.setMess("收藏失败,已存在收藏列表");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(vo));
    }
    public void listLove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Sc> scList = scService.list(user.getId());
        List<Music> musicList=new ArrayList<>();
        if (scList==null)
        {
            return;
        }
        else {
        for (Sc s:scList) {
                musicList.add(musicService.musicIDlist(s.getMid()));
        }
            if (musicList==null)
            {
                return;
            }
            resp.getWriter().write(JSONUtil.toJsonStr(musicList));}
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        String mid =req.getParameter("mid");
        ResultVO vo =new ResultVO();
        Boolean isdelete=scService.delete(user.getId(),mid);
        if (isdelete)
        {
            vo.setOk(true);
            vo.setMess("取消收藏成功");
        }
        else {
            vo.setMess("未能成功取消收藏");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(vo));
    }
}
