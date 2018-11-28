<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
            //菜单
            $.ajax({
                url: "${pageContext.request.contextPath}/selectAll",
                type: "post",
                success: function (data) {

                    var data = data.list;
                    for (var i = 0; i < data.length; i++) {
                        var t = "";
                        for (var j = 0; j < data[i].menulist.length; j++) {
                            //alert(data[i])
                            t += "<p style='text-align: center'><a id=\"btn\" href=\"#\" class=\"easyui-linkbutton\" onclick=\"addTabs('" + data[i].menulist[j].title + "','" + data[i].menulist[j].url + "','" + data[i].menulist[j].iconCls + "')\" data-options=\"iconCls:'icon-search'\">" + data[i].menulist[j].title + "</a></p>";
                            t += "</br>";
                        }
                        //添加一个新面板。在默认情况下，新增的面板会变成当前面板。
                        // 如果要添加一个非选中面板，不要忘记将'selected'属性设置为false
                        // alert(11111)
                        $("#aa").accordion("add", {
                            title: data[i].title,
                            content: t,
                            selected: false
                        });
                    }
                }

            });

        function addTabs(title, url, iconCls) {
            var flag = $("#tt").tabs("exists", title);
            if (flag) {
                $("#tt").tabs("select", title);
            } else {
                /*添加选项卡*/
                $('#tt').tabs('add', {
                    title: title,
                    selected: true,
                    href: "${pageContext.request.contextPath}" + url,
                    iconCls: iconCls,
                    closable: true
                });
            }
        }
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${admin.name}
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>