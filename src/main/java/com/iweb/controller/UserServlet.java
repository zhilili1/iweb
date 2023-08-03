package com.iweb.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.pojo.ResultVO;
import com.iweb.pojo.User;
import com.iweb.service.Impl.UserServiceImpl;
import com.iweb.service.UserService;
import com.iweb.util.FormBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/user")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    private ResultVO vo =new ResultVO();

    public void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
    {
        User user = FormBeanUtil.formToBean(req.getParameterMap(),User.class);
        HttpSession session = req.getSession();
        user=userService.login(user);
        if (user==null)
        {
            vo.setOk(false);
            vo.setMess("未找到该用户，请重新输入");
        }
        else
        {
            vo.setOk(true);
            vo.setMess("登录成功，即将跳转到登录成功页面");
            session.setAttribute("user",user);
        }
        resp.getWriter().write(JSONUtil.toJsonStr(vo));
    }
    //异步校验用户名是否被注册过
    public void verifyUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isExists =
                userService.verifyUsername(req.getParameter("username"));
        ResultVO resultVo = new ResultVO();
        if(isExists){
            resultVo.setOk(true);
            resultVo.setMess("用户已被注册");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }

    //异步注册
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user =
                FormBeanUtil.formToBean(req.getParameterMap(), User.class);
        boolean addOK = userService.add(user);
        ResultVO resultVo = new ResultVO();
        if(addOK){
            resultVo.setOk(true);
            resultVo.setMess("注册成功^_^!!!");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
    //异步获取用户数据
    public void getInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        ResultVO<User> resultVo = new ResultVO<>();
        resultVo.setT(user);
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
}
