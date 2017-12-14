<%--
  Created by IntelliJ IDEA.
  User: 10742
  Date: 2017/11/17
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="session.jsp" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <ul>
    <%=session.getAttribute("AdminID")%>，欢迎您
    <li><a href="/classMember">班级成员</a></li>
    <li><a href="/OpenedCourse">已开课程</a></li>
    <li><a href="/personalMsg">个人信息</a></li>
  </ul>
  </body>
</html>
