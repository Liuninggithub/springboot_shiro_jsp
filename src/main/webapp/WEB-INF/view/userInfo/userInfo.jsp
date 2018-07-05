<%--
  Created by IntelliJ IDEA.
  User: liuning
  Date: 2018/5/8
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userInfo</title>

</head>
<body>
    <h1>userInfo</h1>
</body>
<script src="https://cdn.bootcss.com/jquery/1.10.0/jquery.js"></script>
<script>
    $.ajax({
        url: "/userInfo/getData",
        method: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
        },
        error: function (err) {
            console.log("err");
            console.log(err);
        }
    });

</script>
</html>
