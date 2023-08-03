package com.iweb.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.pojo.ResultVO;
import com.iweb.pojo.Speak;
import com.iweb.pojo.User;
import com.iweb.service.Impl.SpeakServiceImpl;
import com.iweb.service.SpeakService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/speak")
public class SpeakServlet extends BaseServlet {
    private SpeakService speakService =new SpeakServiceImpl();
    public void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid =req.getParameter("mid");
        ResultVO vo =new ResultVO();
        if (mid!=null)
        {
            vo.setOk(true);
            vo.setMess("保存成功，即将跳转到评论界面");
            req.getSession().setAttribute("mid",mid);
            resp.getWriter().write(JSONUtil.toJsonStr(vo));
        }

    }
    public void listSpeak(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = (String) req.getSession().getAttribute("mid");
        List<Speak> speaks =speakService.list(mid);
        if (speaks==null)
        {
            return;
        }
        else {
            resp.getWriter().write(JSONUtil.toJsonStr(speaks));
        }
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = (String) req.getSession().getAttribute("mid");
        User user = (User) req.getSession().getAttribute("user");
        String contain =req.getParameter("contain");
        ResultVO vo =new ResultVO();
        Speak speak =new Speak();
        speak.setContain(contain);
        speak.setMid(mid);
        speak.setUid(user.getId());
        Boolean isadd=speakService.add(speak);
        if (isadd)
        {
            vo.setOk(true);
            vo.setMess("评论成功");
        }
        else {
            vo.setMess("评论失败");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(vo));

    }
    public void select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mid = (String) req.getSession().getAttribute("mid");
        User user = (User) req.getSession().getAttribute("user");
        List<Speak> speaks =speakService.select(user.getId(),mid);
        if (speaks==null)
        {
            return;
        }
        else {
            resp.getWriter().write(JSONUtil.toJsonStr(speaks));
        }

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String spid=req.getParameter("spid");
        Boolean isdelete =speakService.delete(spid);
        ResultVO vo =new ResultVO();
        if (isdelete)
        {
            vo.setOk(true);
            vo.setMess("删除成功");
        }
        else {
            vo.setMess("删除失败");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(vo));
    }
}
