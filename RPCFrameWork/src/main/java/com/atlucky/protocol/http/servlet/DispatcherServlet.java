package com.atlucky.protocol.http.servlet;

import com.atlucky.protocol.http.servlet.handler.HttpServerHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date 2023/8/30 14:06
 * @Author XiaoHu
 * @Description
 **/
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        new HttpServerHandler().handler(req,resp);
        //super.service(req, resp);
    }
}
