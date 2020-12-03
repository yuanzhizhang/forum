<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        文章：
    </blockquote>

    <form class="layui-form" action="article/update" method="post">

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input id="title" style="width:300%" class="layui-text" name="title">
                </div>
            </div>
            <br>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">正文</label>
                <div class="layui-input-block">
                    <textarea id="text" placeholder="请输入内容"  style="height: 200%" class="layui-textarea" name="text"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" >确认修改</button>
                </div>
            </div>
        </div>

    </form>

</div>
<script src="layui/layui.all.js"></script>
</body>
</html>
