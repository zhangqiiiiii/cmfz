<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>

<script type="text/javascript">
    $(function () {

        //添加窗口
        $("#add").dialog({
            title: "添加轮播图",
            width: 400,
            height: 200,
            closed: true,
        });
        //结束的添加窗口
    })


    //执行添加操作
    function clickAdd() {
        $("#addForm").form("submit", {
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
        text: "添加",
        handler: function () {
            $("#add").dialog("open");

        }
    }, '-', {
        iconCls: 'icon-help',
        text: "删除",
        handler: function () {
            var row = $("#dg").edatagrid("getSelected");
            console.log(row)
            if (row == null) {
                $.messager.show({
                    title: '警告',
                    msg: '请选中修改行。',
                    showType: 'show',
                    style: {
                        right: '',
                        top: document.body.scrollTop + document.documentElement.scrollTop,
                        bottom: ''
                    }
                });
            } else {
                $('#dg').edatagrid('destroyRow');
                // $("#dg").edatagrid("load");

            }
        }
    }, '-', {
        text: "修改",
        iconCls: 'icon-help',
        handler: function () {
            /*获取选中行*/
            var row = $("#dg").edatagrid("getSelected")
            if (row == null) {
                $.messager.show({
                    title: '警告',
                    msg: '请选中修改行。',
                    showType: 'show',
                    style: {
                        right: '',
                        top: document.body.scrollTop + document.documentElement.scrollTop,
                        bottom: ''
                    }
                });
            } else {
                /*将当前行变成可编辑模式*/
                var index = $("#dg").edatagrid("getRowIndex", row);
                $("#dg").edatagrid("editRow", index);
            }

        }
    }, '-', {
        text: "保存",
        iconCls: 'icon-help',
        handler: function () {
            $("#dg").edatagrid("saveRow");
        }
    }];

    /*构建数据表格*/
    $(function () {
        $('#dg').edatagrid({
            //修改的地址
            updateUrl: "${pageContext.request.contextPath}/update",
            //删除的地址
            destroyUrl: "${pageContext.request.contextPath}/delete",

            toolbar: toolbar,
            url: '${pageContext.request.contextPath}/Bselect',
            columns: [[
                {field: 'title', title: '名字', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field: 'desc', title: '描述', width: 100, align: 'right'}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            /*pageSize: 3,
            pageList: [3, 6, 9],*/
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>时间: ' + rowData.dates + '</p>' +
                    '<p>:描述:' + rowData.desc + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
            destroyMsg: {
                norecord: {    // 在没有记录选择的时候执行
                    title: 'Warning',
                    msg: 'No record is selected.'
                },
                confirm: {       // 在选择一行的时候执行		title:'Confirm',
                    msg: 'Are you sure you want to delete?'
                }
            }

        });

    })
</script>
<table id="dg"></table>
<div id="add">
    <form id="addForm" method="post" enctype="multipart/form-data">
        标题：<input type="text" id="title" name="title"/><br/>
        图片：<input name="wenjian" type="file"/><br/>
        描述：<input type="text" id="status" name="desc"/><br/>
        <input type="button" onclick="clickAdd()" value="添加"/><br/>
    </form>
</div>