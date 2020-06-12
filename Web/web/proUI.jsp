<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.OJ.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<HashMap<String, String>> problem = null;
    if(session.getAttribute("problem") != null) {
        problem = (ArrayList<HashMap<String, String>>) session.getAttribute("problem");
    }
    HashMap<String, String> p = problem.get(0);

    User host = null;
    if(session.getAttribute("host") != null) {
        host = (User) session.getAttribute("host");
    }
%>

<html>
<head>
    <meta name="referrer" content="never">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"><!-- 兼容渲染 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="CSS/proUI.css">
    <title>
        1000 - A+B问题
    </title>
</head>

<body>
<div style="width:100%;height: auto; min-height:calc(100vh - 70px);margin:0 auto;">
    <div style="width:40%;margin:0 auto;">
        <div class="row justify-content">
            <div class="col-lg-5 bg-white" style="padding:6px;padding-top:8px;padding-right:1px;">
                <div style="height:89vh;padding: 0.6em;;overflow-x:hidden;">
                    <h1 class="mx-auto"><%=p.get("problem_name")%> </h1>
                    <h5 class="font-weight-bold">题目描述</h5>
                    <div class="bg-light p-2 px-3">
                        <span style="font-weight: 500;font-size: small"> Time Limit: 1000 ms</span><br>
                        <span style="font-weight: 500;font-size: small">Memory Limit: 256 mb</span>
                    </div>
                    <div class="OjInfo">
                        <p>
                            <%=p.get("index")%>
                        </p>
                    </div>　
                    <form action="proUI?name=<%=host != null ? host.getUsername() : null%>&ID=<%=p.get("problem_id")%>" method="POST" enctype="multipart/form-data">
                        <input type="file" name="source">
                        <button type="submit">提交代码</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>