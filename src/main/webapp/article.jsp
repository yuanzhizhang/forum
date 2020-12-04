<%@ page import="loginsystem.entity.Article" %>
<%@ page import="loginsystem.dao.ArticleDao" %><%--
  Created by IntelliJ IDEA.
  User: 13602
  Date: 2020/11/30
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<%
    String id = request.getParameter("id");
    Article article = ArticleDao.getOneArticle(id);
%>

<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        文章：
    </blockquote>

    <form class="layui-form" action="">

        <div class="layui-form-item">
            <div class="layui-inline">
                    <h1><%=article == null ? "null" : article.getTitle()%></h1>
                    <%session.setAttribute("artID",article.getId());%>
            </div>
            <br>
            <div class="layui-form-item layui-form-text">
                <div class="layui-input-block">
                    <textarea id="text" placeholder="请输入内容" readonly style="height: 60%" class="layui-textarea"><%=article == null ? "null" : article.getText()%></textarea>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        评论：
    </blockquote>

    <table class="layui-hide" id="comment" lay-filter="comment_filter"></table>

</div>
<script src="layui/layui.all.js"></script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<script>
    layui.use(['table', 'layer', 'form'], function () {
        const table = layui.table;
        const layer = layui.layer;
        const $ = layui.$;

        const tableIns = table.render({
            elem: '#comment'
            , url: 'comment/all'
            , cellMinWidth: 80
            , defaultToolbar: ['filter', 'print', 'exports']
            , toolbar: '#toolbarDemo'
            , response: {
                statusCode: 200
            }
            , cols: [[
                {field: 'user', title: 'username', width: 100,}
                , {field: 'comment', title: '评论', align: 'center'}
            ]]
        });
        table.on('toolbar(comment_filter)', function(obj){
            switch(obj.event){
                case 'add':
                    layer.open({
                        type: 2,
                        content: 'addcomment.jsp',
                        area: ['700px', '400px'],
                        title: false,
                        btn: ['确定', "取消"],
                        yes: function (index, layero) {
                            const iframeWindow = window['layui-layer-iframe' + index]
                                , submitID = 'user-add-save'
                                , submit = layero.find('iframe').contents().find('#' + submitID);

                            //监听提交
                            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                                const field = data.field; //获取提交的字段

                                $.ajax({
                                    url:'add/comment',
                                    type: 'POST',
                                    data: JSON.stringify(field),
                                    success: function (res){
                                        if (res.code === 200) {
                                            tableIns.reload();
                                            layer.close(index);
                                            layer.msg('添加成功');
                                        } else {
                                            layer.msg('添加失败');
                                        }
                                    },
                                    error: function (error){
                                        layer.msg('添加失败');
                                    }
                                });
                            });

                            submit.trigger('click');
                        }
                    });
                    break;

            }
        });


    });


</script>
</body>
</html>
