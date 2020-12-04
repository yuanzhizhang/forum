<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        发表您的评论
    </blockquote>

    <div class="layui-form" >

        <div class="layui-form-item">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">正文</label>
                <div class="layui-input-block">
                    <textarea id="text" name="comment" placeholder="请输入内容"  class="layui-textarea"></textarea>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-hide">
            <button type="button" lay-submit="" lay-filter="user-add-save" id="user-add-save" class="layui-btn">
                确认
            </button>
        </div>

    </div>

</div>
<script src="layui/layui.all.js"></script>
</body>
</html>
