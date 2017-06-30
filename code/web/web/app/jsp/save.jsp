<%--
  Created by IntelliJ IDEA.
  User: lxh
  Date: 2017/6/29
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Out</title>
</head>
    <body>
    <form action="./read.jsp" method="post">
        <textarea name="editor"><%=request.getParameter("editor1")%></textarea>
        <input type="submit" value="Submit">
    </form>
    </body>
</html>
