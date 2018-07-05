<%--
  Created by IntelliJ IDEA.
  User: liuning
  Date: 2018/5/8
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>login</h1>
错误信息：<h4>${msg}</h4>

<form action="/login" method="post">

    <p>账号：<input type="text" name="username" value="admin"/></p>

    <p>密码：<input type="text" name="password" value="123456"/></p>

    <p><input type="submit" value="登录"/></p>

</form>
</body>
</html>
