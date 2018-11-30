<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>

<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-edit',
        text: "添加专辑",
        handler: function () {
            $("#div_add").dialog("open")
        }
    }, '-', {
        iconCls: 'icon-help',
        text: "添加章节",
        handler: function () {
            var row = $("#chapter_tg").treegrid("getSelected");
            if (row == null) {
                alert("请先选中专辑")
            } else {
                if (row.author != null) {
                    $("#chapter_dd").dialog("open")
                    $("#p_id").val(row.id)
                } else {
                    alert("请先选中专辑")
                }
            }

        }
    }, '-', {
        text: "专辑详情",
        iconCls: 'icon-help',
        handler: function () {
            /*展示专辑相关的信息*/
            $('#album').dialog("open")
            /*把专辑信息填充到dialog*/
            var row = $("#chapter_tg").treegrid("getSelected");
            if (row == null) {
                alert("请先选中专辑")
            } else {
                if (row.author != null) {
                    $("#album_ff").form("load", row);
                    $("#album_img").prop("src", row.img)
                } else {
                    alert("请先选中专辑")
                }
            }
        }
    }, '-', {
        text: "下载音频",
        iconCls: 'icon-help',
        handler: function () {
            var row = $("#chapter_tg").treegrid("getSelected");
            if (row != null) {
                if (row.size != null) {
                    location.href = "${pageContext.request.contextPath}/download?downPath=" + row.downPath + "&title=" + row.title
                }
            }
        }
    }];

    /*构建数据表格*/
    $(function () {
        $('#chapter_tg').treegrid({
            onDblClickRow: function (row) {
                $("#audio").dialog("open")
                $("#audio_id").prop("src", "${pageContext.request.contextPath}/upload/" + row.downPath)
            },
            url: "${pageContext.request.contextPath}/Album_selectAll",
            // method: "get",
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
        $("#div_add").dialog({
            title: '添加专辑',
            width: 500,
            height: 400,
            closed: true,
        })
        $('#album').dialog({
            title: '专辑详情',
            width: 400,
            height: 200,
            closed: true,
        });

        $('#audio').dialog({
            title: '播放',
            width: 400,
            height: 200,
            closed: true,
        });
        $('#chapter_dd').dialog({
            title: '添加章节',
            width: 400,
            height: 200,
            closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    addChapter();


                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#chapter_dd").dialog("close")
                }
            }],
        });
    })

    //添加章节
    function addChapter() {
        $('#chapter_ff').form('submit', {
            url: "${pageContext.request.contextPath}/Chapter_add",
            success: function (data) {
                if (data == "true") {
                    //关闭修改窗口
                    $("#chapter_dd").dialog("close")
                    //刷新当前页
                    $("#chapter_tg").treegrid("load");
                } else {
                    alert("添加失败");
                }

            }
        })
    }

    //添加专辑
    function button_add() {
        $("#form_add").form("submit", {

            url: "${pageContext.request.contextPath}/Album_add",
            success: function (data) {
                if (data) {
                    alert("添加成功");
                    $("#div_add").dialog("close");
                    $("#chapter_tg").treegrid("reload");
                } else {
                    alert("添加失败");
                }

            }
        })
    }


</script>

<table id="chapter_tg"></table>

<div id="album">

    <form id="album_ff" method="post">
        <div>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            <input class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            <input class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <div>
            <img src="" id="album_img">
        </div>
    </form>
</div>


<div id="chapter_dd">

    <form id="chapter_ff" method="post" ENCTYPE="multipart/form-data">
        <div>
            标题:<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            请选择:<input type="file" name="wenjian" style="width:300px">
        </div>
        <div>
            <input type="hidden" name="id" id="p_id" value="" style="width:300px">
        </div>
    </form>
</div>

<div id="audio">
    <audio id="audio_id" src="" autoplay="autoplay" controls="controls" loop="loop"></audio>
</div>


<div id="div_add">
    <form id="form_add" enctype="multipart/form-data" method="post">
        <div>
            专辑标题<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            专辑封面<input type="file" name="wenjian" data-options="required:true"/>
        </div>
        <div>
            章节数量<input type="text" name="count" data-options="required:true"/>
        </div>
        <div>
            专辑评分<input class="easyui-validatebox" type="text" name="score" data-options="required:true"/>
        </div>
        <div>
            专辑作者<input class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            播音<input class="easyui-validatebox" type="text" name="broadCast" data-options="required:true"/>
        </div>
        <div>
            专辑简介<input class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <div>
            <input class="easyui-validatebox" type="button" value="保存" onclick="button_add()"/>
        </div>
    </form>

</div>
