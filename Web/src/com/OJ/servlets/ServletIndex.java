package com.OJ.servlets;

import com.OJ.mySqlHelper.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/index")
public class ServletIndex extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        String sql = "select problem_name, problem_id from Issue_problem;";
        DB db = new DB();
        ArrayList list = db.executeQuery(sql);
        request.getSession().setAttribute("pro_table", list);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "select problem_name, problem_id from Issue_problem;";
        DB db = new DB();
        ArrayList list = db.executeQuery(sql);
        request.getSession().setAttribute("pro_table", list);
        System.out.println(list);
        response.sendRedirect("index.jsp");
    }
}
