<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13602
  Date: 2020/12/1
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>首页</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body style="overflow-y:scroll;">
<script src="layui/layui.all.js"></script>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <a href="admin.jsp"><div class="layui-logo">ZYZforum</div></a>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="login.jsp">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <%String name = (String)session.getAttribute("name");
                    %>
                    <%if(name!=null){
                        out.print(name);
                        out.print("<a href=\"loginout\">退了</a>");
                    }%>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
        </ul>
    </div>

<style type="text/css">

    a{text-decoration:none;}

</style>
    <h1>
<%String[] title = (String[]) session.getAttribute("title");
    String[] id = (String[]) session.getAttribute("id");
%>
<%for(int i=title.length;i>0;i--){
    out.print("<a href=\"article.jsp?id="+id[i-1]+"\">"+title[i-1]+"</a><br>");
}%>
</h1>
    <div id="dg" style="z-index: 9999; position: fixed ! important; right: 100px; top: 100px;">
        <table width=""100% style="position: absolute; width:260px; right: 100px; top: 100px;">
            <a href="artucle_add.jsp"><h1><button type="button" class="layui-btn">+</button></h1></a>
        </table>
    </div>
    <div>
    </div>

</div>
</body>
</html>
