<%--
  Created by IntelliJ IDEA.
  User: ly
  Date: 2020-06-11
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%
    ArrayList <HashMap<String, String>> submit_table = null;
    if(session.getAttribute("submit_table") != null) {
        submit_table = (ArrayList<HashMap<String, String>>) session.getAttribute("submit_table");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">

        .hovertable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
            margin:0 auto;
        }
        table.hovertable th {
            background-color:#c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
        table.hovertable tr {
            background-color:#d4e3e5;
        }
        table.hovertable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
            background-color:#d4e3e5;
        }
        .hovertable tr:hover,.hovertable tr.hilite
        {
            background-color: yellow;
            color: white;
        }
    </style>
</head>
<body>
    <div class="main">
        <div class="sj">
            <table style="height: 100%;width: 100%" class="hovertable">
                <tr>
                    <td>RunId</td>
                    <td>Problem</td>
                    <td>JudgeStates</td>
                    <td>Time</td>
                    <td>Memory</td>
                    <td>Language</td>
                    <td>SubmitTime</td>
                    <td>Author</td>
                </tr>
                <%
                    if(submit_table != null)
                %>
                <% for(HashMap<String, String> u : Objects.requireNonNull(submit_table)){ %>
                <tr>
                    <th><%=u.get("run_id")%></th>
                    <th><%=u.get("problem_id")%></th>
                    <th><%=u.get("result")%></th>
                    <th><%=u.get("time")%></th>
                    <th><%=u.get("memory")%></th>
                    <th><%=u.get("language")%></th>
                    <th><%=u.get("submit_time")%></th>
                    <th><%=u.get("author")%></th>
                </tr>
                <%}%>
            </table>
        </div>
    </div>
</body>
</html>