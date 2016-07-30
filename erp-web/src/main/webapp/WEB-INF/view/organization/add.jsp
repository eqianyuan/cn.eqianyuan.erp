<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>驿乾元 - ERP管理系统</title>
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/view/common.css" rel="stylesheet">
    <link href="/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-1.10.1.min.js"></script>
</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">驿乾元ERP管理系统</a>
        </div>

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="/system-manage/logout"><i class="fa fa-sign-out fa-fw"></i> 退出登录</a>
                    </li>
                </ul>
            </li>
        </ul>

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="菜单查找...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </li>

                    <li>
                        <a href="/gotoPage?url=organization/list"><i class="fa fa-dashboard fa-cny"></i> 公司信息</a>
                    </li>

                </ul>
            </div>
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <div class="row">

            <div class="col-lg-12">
                <h1 class="page-header">公司信息添加</h1>
            </div>

        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <div id="form-tip" class="alert hidden">
                                    <strong></strong>
                                </div>
                                <form role="form">

                                    <div class="form-group">
                                        <label>公司名称</label>
                                        <input class="form-control" name="name">
                                        <p class="help-block tip">tip.</p>
                                    </div>

                                    <div class="form-group">
                                        <label>详细描述</label>
                                        <textarea class="form-control" rows="3" name="details"></textarea>

                                        <p class="help-block tip">tip.</p>
                                    </div>

                                    <input type="button" class="btn btn-outline btn-success submit" value="添加">
                                    <input type="button" class="btn btn-outline btn-info back" value="返回">

                                </form>
                            </div>
                        </div>
                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>

    </div>
</div>

<script>


    $(function(){

        $(".submit").click(function(){
            $(this).attr('disabled',"true")
            $.ajax({
                type: "POST",
                url: "/system_organzation/add",
                data: $("form").serialize(),
                success: function(response){
                    if(response.code == "200"){
                        $("#form-tip").removeClass("hidden alert-warning").addClass("alert-success").show().find("strong").text(response.message);

                        setTimeout(function(){
                            window.location.href = "/system-manage/gotoPage?url=organization/list";
                        }, 500);
                    }else{
                        $("#form-tip").removeClass("hidden alert-success").addClass("alert-warning").show().find("strong").text(response.message);
                        $(".submit").removeAttr("disabled");
                    }
                }
            });
        });

        $(".back").click(function(){
            window.history.go(-1);
        });
    });

</script>
</body>

</html>
