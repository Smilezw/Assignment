package com.OJ.mySqlHelper;

import java.sql.*;
import java.util.*;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/OnlineJudge" +
            "?useSSL=false&serverTimezone=UTC" +
            "&useUnicode=true&characterEncoding=GBK";

    private static final String user= "root";
    private static final String password = "551700";

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public void Open() {
        try
        {
            // 2 获取数据库连接
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public int Judge(String sql, Object... param) {
        Open();
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < param.length; i++) {
                ps.setObject(i+1, param[i].toString());
            }
            rs = ps.executeQuery();
            if(rs.next()) return 1;
            else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            try{
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.getErrorCode();
            }
        }
    }

    public void Run(String sql, Object... param)  {
        Open();
        try{
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < param.length; i++) {
                ps.setObject(i+1, param[i].toString());
            }
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getErrorCode();
        }
        finally {
            try{
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.getErrorCode();
            }
        }
    }

    public ArrayList<Map<String, String>> executeQuery(String sql, Object... param) {
        ArrayList<Map<String, String>> result;
        try {
            Open();
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < param.length; i++) {
                ps.setObject(i+1, param[i].toString());
            }
            rs = ps.executeQuery();
            // 获取结果原始信息
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            result = new ArrayList<>();

            while (rs.next()) {
                // 对象数组，表示一行数据
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    // 获得列名
                    String columnName = resultSetMetaData.getColumnName(i);
                    map.put(columnName, rs.getObject(columnName).toString());
                }
                result.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try{
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}


