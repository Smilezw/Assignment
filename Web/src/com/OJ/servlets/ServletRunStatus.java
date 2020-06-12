package com.OJ.servlets;

import com.OJ.mySqlHelper.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/runStatus")
public class ServletRunStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "select * from Issue_problemsubmit;";
        DB db = new DB();
        ArrayList list = db.executeQuery(sql);
        request.getSession().setAttribute("submit_table", list);
        //System.out.println("get problem  " + list);
        response.sendRedirect("runStatus.jsp");
    }
}
