<%--
  Created by IntelliJ IDEA.
  User: 10742
  Date: 2017/11/24
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utils.*" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/amazeui.min.css"/>
</head>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    request.setCharacterEncoding("utf-8");

    String Action = request.getParameter("Action");

    if (Action != null && Action.equals("Login")) {
        String method = request.getMethod();
        if (method.equals("POST")) {
            try {
                Login login = new Login();
                String ID = request.getParameter("ID");                //得到登录用户名
                String Pwd = request.getParameter("Pwd");       //得到登录密码

                if (login.checkLogin(ID, Pwd)) {
                    session.setAttribute("Login", "true");//设置登录标记,防止未登录进入后台
                    session.setAttribute("AdminID", ID);
                    out.println("<SCRIPT LANGUAGE='JavaScript'>alert('登录成功！');location.href='index.jsp';</SCRIPT>");
                    return;
                } else {
                    out.println("<SCRIPT LANGUAGE='JavaScript'>alert('用户名或密码不正确！');location.href='login.jsp';</SCRIPT>");
                    return;
                }
            } catch (Exception e) {
                out.println("<SCRIPT LANGUAGE='JavaScript'>alert('服务器异常！!');location.href='login.jsp';</SCRIPT>");
                return;
            }
        } else {
            response.sendError(403, "禁止访问");
            return;
        }
    }
%>
<body>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <form action="login.jsp" method="post" class="am-form login-form" onSubmit="return LoginCheck()">
            <label>
                用户名:
            </label>
            <input type="text" name="ID" id="ID" value="">
            <br>
            <label>
                密码:
            </label>
            <input type="password" name="Pwd" id="Pwd" value="">
            <br>
            <br/>
            <div class="am-cf">
                <input name="Action" type="hidden" value="Login">
                <input type="submit" value="登 录" id="save" class="am-btn am-btn-primary am-btn-sm am-fl">
            </div>
        </form>

    </div>
</div>

</body>

<script src="js/jquery.min.js"></script>
<script src="js/amazeui.min.js"></script>
<script>
    function LoginCheck() {

        var LoginCheck = false;
        if ($("#User").val() === "" || $("#Pwd").val() === "") {
            alert("用户名或密码不能为空!");
            return false;
        }

        return true;
    }
</script>
</html>
