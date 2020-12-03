<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%if(session.getAttribute("name") ==null){
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }%>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        文章：
    </blockquote>

    <form class="layui-form" action="addarticle" method="post">

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input id="title" name="title"style="width:300%" class="layui-text">
                </div>
            </div>
            <br>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">正文</label>
                <div class="layui-input-block">
                    <textarea id="text" name="text" placeholder="请输入内容"  style="height: 300%" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" >提交</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="layui/layui.all.js"></script>
</body>
</html>
