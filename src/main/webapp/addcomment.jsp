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
            <div class="layui-inline">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">
                    <label>
                        <input id="comment" type="text" required name="comment" class="layui-input" >
                    </label>
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
