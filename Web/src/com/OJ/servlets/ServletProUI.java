package com.OJ.servlets;

import com.OJ.mySqlHelper.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/proUI")
public class ServletProUI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String source = request.getParameter("source");
        String ID = request.getParameter("ID");
        if(ID == null || source == null || ID.equals("") || source.equals("")) {
            request.getRequestDispatcher("proUI.jsp").forward(request,response);
            request.getSession().setAttribute("errorInfo", "输入错误");
        }
        else {
            String sql = " INSERT INTO Issue_problemsubmit " +
                    "(problem_id, source, language, flag, result) " +
                    "value(1, ?, 'c++', 0, 0)";
            DB db = new DB();
            db.Run(sql, source);
            request.getRequestDispatcher("proUI.jsp").forward(request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null) {
            String sql = "select * from Issue_problem where problem_id = ? ;";
            DB db = new DB();
            ArrayList list = db.executeQuery(sql, id);
            request.getSession().setAttribute("problem", list);
            System.out.println(list);
            response.sendRedirect("proUI.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
