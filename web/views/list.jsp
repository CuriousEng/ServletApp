<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/6/19
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<ul>
    <%
        List<String> names = (List<String>) request.getAttribute("userNames");

        if (names != null && !names.isEmpty()) {
            out.println("<ui>");
            for (String s : names) {
                out.println("<li>" + s + "</li>");
            }
            out.println("</ui>");
        } else out.println("<p>There are no users yet!</p>");
    %>
</ul>
</body>
</html>
