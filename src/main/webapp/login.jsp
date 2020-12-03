<%@ page contentType="text/html;charset=UTF-8" autoFlush="true" pageEncoding="utf-8" %>
<html lang="java">
<head>
    <meta charset="utf-8">
    <title>登入</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<title>zyzForum-登录</title>
<body>
<script src="layui/layui.js"></script>
<div class="login-main">
    <header class="layui-elip">登录</header>
    <form class="layui-form" action="login" method="post">
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
            <button class="layui-btn">登录</button>
        </div>
        <hr/>
        <p><a href="adduser.jsp" class="fl">立即注册</a><a href="updateuser.jsp" class="fr">忘记密码？</a></p>
    </form>
</div>

<script src="layui/layui.js"></script>
</body>
</html>
