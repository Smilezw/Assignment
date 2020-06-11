<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: wu
  Date: 2020/5/18
  Time: 下午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
  ArrayList <HashMap<String, String>> pro_table = null;
  if(session.getAttribute("pro_table") != null) {
    pro_table = (ArrayList<HashMap<String, String>>) session.getAttribute("pro_table");
  }
%>

<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <table >
      <caption> problem </caption>
      <tr>
        <td>pro.id</td>
        <td>pro.name</td>
      </tr>

        <%
          if(pro_table != null) {
            for(HashMap<String, String> t:pro_table) {
        %>
          <tr>
            <td><%=t.get("problem_id")%></td>
            <td><a href="${pageContext.request.contextPath}/proUI?id=<%=t.get("problem_id")%>"> <%=t.get("problem_name")%></a></td>
          </tr>
        <%
            }
          }
        %>

    </table>
  </body>
</html>
