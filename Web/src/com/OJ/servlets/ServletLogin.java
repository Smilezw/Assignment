package com.OJ.servlets;

import com.OJ.entity.User;
import com.OJ.mySqlHelper.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")

public class ServletLogin extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login ....");
        String turn = request.getParameter("submit2");
        if(turn != null) {
            response.sendRedirect("register.jsp");            //注册界面
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username == null || password == null || username.equals("") || password.equals("")) {
                request.getRequestDispatcher("login.jsp").forward(request,response);
                request.getSession().setAttribute("errorInfo", "输入错误");
            }
            else {
                String sql = "select * from user " +
                        "where username = ? and password = ? ";
                DB db = new DB();
                int ok = db.Judge(sql, username, password);
                if(ok == -1) response.sendRedirect("error.jsp");
                else if(ok == 1){
                    request.getSession().setAttribute("host", new User(username, password));
                    request.getSession().setAttribute("okk", true);
                    response.sendRedirect("index");
                } else {
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                    request.getSession().setAttribute("errorInfo", "账号或密码错误");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
