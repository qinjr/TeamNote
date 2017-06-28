<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/27
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>title</title>
        <% String path = request.getContextPath();%>
        <link href="<%=path%>/app/css/bootstrap.min.css" rel="stylesheet" >

    </head>
    <body>
        <a href="<%=path%>/app/jsp/editor.jsp">editor</a>
        Begin
    </body>
</html>