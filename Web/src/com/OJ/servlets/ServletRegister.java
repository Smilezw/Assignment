package com.OJ.servlets;

import com.OJ.mySqlHelper.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")

public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sql = "select * from user " +
                "where username = ?" ;
        if(username.equals(null) || password.equals(null) || username.equals("") || password.equals("")) {
            request.setAttribute("errorInfo","输入不合法!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        else {
            DB db = new DB();
            int ok = db.Judge(sql,username);
            if (ok == -1) response.sendRedirect("error.jsp");
            else if (ok == 1) {
                request.setAttribute("errorInfo", "用户名被注册");
                request.getRequestDispatcher("register.jsp").forward(request, response);//存在
            } else {
                sql = "insert into user VALUES(?, ?);";
                db.Run(sql, username, password);
                request.getSession().setAttribute("name", username);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
