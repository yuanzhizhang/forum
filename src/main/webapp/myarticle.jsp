<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div class="layui-container">
    <blockquote class="layui-elem-quote layui-text">
        表格数据显示：
    </blockquote>

    <table id="demo" lay-filter="test"></table>

</div>
<script src="layui/layui.all.js"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>
<script>
    layui.use(['table','layer'], function(){
        const table = layui.table;
        const $ = layui.$;

        //第一个实例
        const tableIns = table.render({
            elem: '#demo'
            ,url: 'article' //数据接口
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
                        body.find('#username').val(data.name);
                        body.find('#stu_id').val(data.id);
                        body.find('#sex').val(data.sex);
                        body.find('#major').val(data.major);
                        body.find('#email').val(data.email);
                        body.find('#school_date').val(data.schoolDate);
                    }
                });

            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){

                    // ajax
                    $.ajax({
                        type:'POST',
                        url: 'stu/del',
                        data: { id: data.id },
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
                    content: 'edit_plus.jsp',
                    area: ['700px', '500px'],
                    success: function(layero, index){
                        console.log(layero, index);
                        // 数据绑定
                        const body = layer.getChildFrame('body', index)
                        body.find('#username').val(data.name);
                        body.find('#stu_id').val(data.id);
                        body.find('#sex').val(data.sex);
                        body.find('#major').val(data.major);
                        body.find('#email').val(data.email);
                        body.find('#school_date').val(data.schoolDate);
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
