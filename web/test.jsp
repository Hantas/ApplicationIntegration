<%--
  Created by IntelliJ IDEA.
  User: 10742
  Date: 2017/12/10
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
</head>
<script language="javascript">
    document.onclick=click();
    function click()
    {
        //return null;
        alert('document event');
    }
</script>
<body>
<form name="MyForm" onclick="alert('form event');">
    <input type="button" value="click me" onclick=" alert('button event');"/>
</form>
</body>
</html>
