<%@ page import="loginsystem.servlet.AddUserServlet" %>
<%@ page contentType="text/html;charset=UTF-8" autoFlush="true" pageEncoding="utf-8" %>
<html lang="java">
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<title>注册</title>
<body>
<script src="layui/layui.js"></script>

<div class="login-main">
    <header class="layui-elip">注册</header>
    <form class="layui-form" action="login/adduser" method="post">
        <div class="layui-input-inline">
            <label>
                <input type="text" name="username" required placeholder="用户名" class="layui-input">
            </label>
        </div>
        <div class="layui-input-inline">
            <label>
                <input type="password" name="password" required  placeholder="密码" class="layui-input">
            </label>
        </div>
        <div class="layui-input-inline login-btn">
            <button class="layui-btn" >提交</button>
        </div>
    </form>
</div>

</body>
</html>


