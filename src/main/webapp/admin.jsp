<%--
  Created by IntelliJ IDEA.
  User: 13602
  Date: 2020/12/1
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body class="layui-layout-body">
<script src="layui/layui.all.js"></script>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <a class="layui-logo" href="home.jsp">ZYZforum</a>
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
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <%String name = (String)session.getAttribute("name");
                    %>
                    <%=name%>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="loginout">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-container">
            <blockquote class="layui-elem-quote layui-text">
                表格数据显示：
            </blockquote>

            <table id="demo" lay-filter="test"></table>

        </div>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="layui/layui.js"></script>
<script>
    layui.use(['table','layer'], function(){
        const table = layui.table;
        const $ = layui.$;

        //第一个实例
        const tableIns = table.render({
            elem: '#demo'
            ,url: 'article/person' //数据接口
            ,response: {
                statusCode: 200 //规定成功的状态码，默认：0
            }
            ,cols: [[ //表头
                {field: 'title', title: 'title', sort: true, fixed: 'left'}
                ,{field: 'text', title: 'text'}
                ,{fixed: 'right', width:250,  align:'center', title: '操作', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'detail'){ //查看

                layer.open({
                    type: 2,
                    content: 'detail.jsp',
                    area: ['700px', '500px'],
                    success: function(layero, index){
                        console.log(layero, index);
                        // 数据绑定
                        const body = layer.getChildFrame('body', index)
                        body.find('#title').val(data.title);
                        body.find('#text').val(data.text);
                    }
                });

            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){

                    // ajax
                    $.ajax({
                        type:'POST',
                        url: 'article/del',
                        data: { title: data.title },
                        success: function (res){
                            if (res.code === 200)
                            {
                                layer.msg('操作成功');
                                tableIns.reload();
                            }
                            else
                            {
                                layer.msg('操作失败');
                            }

                        },
                        error: function (error){
                            layer.msg(error);
                        }
                    });

                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                //编辑

                layer.open({
                    type: 2,
                    content: 'edit.jsp',
                    area: ['700px', '500px'],
                    success: function(layero, index){
                        console.log(layero, index);
                        // 数据绑定
                        const body = layer.getChildFrame('body', index)
                        body.find('#title').val(data.title);
                        body.find('#text').val(data.text);
                    },
                    cancel: function(index, layero){
                        tableIns.reload();
                        return true;
                    }
                });

            } else if(layEvent === 'LAYTABLE_TIPS'){
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

    });
</script>
</body>
</html>
