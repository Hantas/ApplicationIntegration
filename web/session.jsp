<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	boolean isLogin = Boolean.valueOf((String) session.getAttribute("Login"));
	if(!isLogin) 
	{
		out.print("<script>alert('请重新登录!');location.href='login.jsp';</script>");
	}
%>