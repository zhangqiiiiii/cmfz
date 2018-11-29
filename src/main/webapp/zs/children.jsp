<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>

<script type="text/javascript">
    $(function () {

        //添加窗口
        $("#adds").dialog({
            title: "添加轮播图",
            width: 400,
            height: 200,
            closed: true,
        });
        //结束的添加窗口
    })


    //执行添加操作
    function clickAdd() {
        $("#addForms").form("submit", {
            url: "${pageContext.request.contextPath}/add",
            success: function (data) {
                if (data == "true") {
                    //关闭修改窗口
                    $("#add").dialog("close");
                    //刷新当前页
                    $("#dg").datagrid("load");
                } else {
                    alert("添加失败");
                }
            }
        });
    }

    //结束的添加操作
    var toolbar = [{
        iconCls: 'icon-edit',
        text: "专辑详情",
        handler: function () {
            $("#adds").dialog("open");

        }
    }, '-', {
        iconCls: 'icon-help',
        text: "添加专辑",
        handler: function () {

        }
    }, '-', {
        text: "添加章节",
        iconCls: 'icon-help',
        handler: function () {

        }
    }, '-', {
        text: "下载",
        iconCls: 'icon-help',
        handler: function () {

        }
    }];

    /*构建数据表格*/
    $(function () {
        $('#tt1').treegrid({
            url: "${pageContext.request.contextPath}/Album_selectAll",
            method: "get",
            idField: 'id',
            treeField: 'title',
            toolbar: toolbar,
            //自适应
            fit: true,
            fitColumns: true,
            pagination: true,
            columns: [[
                {title: '标题', field: 'title', width: 180},
                {field: 'size', title: '大小', width: 60, align: 'right'},
                {field: 'duration', title: '时长', width: 80},
                {field: 'downPath', title: '下载路径', width: 80},
                {field: 'uploadDate', title: '时间', width: 80}
            ]]
        });
    });
</script>
<table id="tt1" style="width:600px;height:400px"></table>
<div id="adds">
    <form id="addForms" method="post" enctype="multipart/form-data">
        标题：<input type="text" id="title" name="title"/><br/>
        图片：<input name="wenjian" type="file"/><br/>
        描述：<input type="text" id="status" name="desc"/><br/>
        <input type="button" onclick="clickAdd()" value="添加"/><br/>
    </form>
</div>
