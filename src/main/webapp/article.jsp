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
            </div>
            <br>
            <div class="layui-form-item layui-form-text">
                <div class="layui-input-block">
                    <textarea id="text" placeholder="请输入内容" readonly style="height: 300%" class="layui-textarea"><%=article == null ? "null" : article.getText()%></textarea>

                </div>
            </div>
        </div>
    </form>
</div>
<script src="layui/layui.all.js"></script>
</body>
</html>
