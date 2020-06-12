package com.OJ.servlets;

import com.OJ.entity.User;
import com.OJ.mySqlHelper.DB;
import com.sun.deploy.net.HttpRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/proUI")
public class ServletProUI extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String source = "";
        try {
            List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
            for (FileItem item : items) {
                source = source + item.getString() + "\n";
            }
        }catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("proUI.jsp");
        }
        String ID = request.getParameter("ID");
        String name = request.getParameter("name");
        if(ID == null || ID.equals("") || source.equals("") || null == name) {
            request.getRequestDispatcher("proUI.jsp").forward(request,response);
            request.getSession().setAttribute("errorInfo", "输入错误");
        }
        else {
            String sql = " INSERT INTO Issue_problemsubmit " +
                    "(problem_id, source, language, flag, result, author, submit_time) " +
                    "value(?, ?, 'c++', 0, 0, ?, now())";
            System.out.println("update sql  " + sql);
            DB db = new DB();
            db.Run(sql, ID, source, name);
            response.sendRedirect("proUI.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null) {
            String sql = "select * from Issue_problem where problem_id = ? ;";
            DB db = new DB();
            ArrayList list = db.executeQuery(sql, id);
            request.getSession().setAttribute("problem", list);
            response.sendRedirect("proUI.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
