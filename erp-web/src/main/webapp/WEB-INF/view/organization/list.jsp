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
    <script src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/common/eqianyuan.page.js"></script>
    <script type="text/javascript" src="/js/common/common_utils.js"></script>
</head>

<body>
<div id="wrapper">

    <!-- Navigation -->
    <%--<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">--%>
        <%--<div class="navbar-header">--%>
            <%--<a class="navbar-brand" href="${pageContext.request.contextPath}">驿乾元ERP管理系统</a>--%>
        <%--</div>--%>

        <%--<ul class="nav navbar-top-links navbar-right">--%>
            <%--<li class="dropdown">--%>
                <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
                    <%--<i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>--%>
                <%--</a>--%>
                <%--<ul class="dropdown-menu dropdown-user">--%>
                    <%--<li><a href="/system-manage/logout"><i class="fa fa-sign-out fa-fw"></i> 退出登录</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
        <%--</ul>--%>

        <%--<div class="navbar-default sidebar" role="navigation">--%>
            <%--<div class="sidebar-nav navbar-collapse">--%>
                <%--<ul class="nav" id="side-menu">--%>
                    <%--<li class="sidebar-search">--%>
                        <%--<div class="input-group custom-search-form">--%>
                            <%--<input type="text" class="form-control" placeholder="菜单查找...">--%>
                                <%--<span class="input-group-btn">--%>
                                <%--<button class="btn btn-default" type="button">--%>
                                    <%--<i class="fa fa-search"></i>--%>
                                <%--</button>--%>
                            <%--</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>

                    <%--<li>--%>
                        <%--<a href="/system-manage/fiscal-detail/list"><i class="fa fa-dashboard fa-cny"></i> 财政收支</a>--%>
                    <%--</li>--%>

                    <%--<li>--%>
                        <%--<a href="/gotoPage?url=organization/list"><i class="fa fa-dashboard fa-cny"></i> 公司信息</a>--%>
                    <%--</li>--%>

                    <%--<li>--%>
                        <%--<a href="/gotoPage?url=inforcontent/inforcontentlist"><i class="fa fa-dashboard fa-cny"></i> 访问接口地址管理</a>--%>
                    <%--</li>--%>

                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<!-- /.navbar-static-side -->--%>
    <%--</nav>--%>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">公司信息管理列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">


                    <div class="panel-heading">
                        <a href="/system_organzation/gotoAddByFiscalDetail">
                            <button type="button" class="btn btn-default btn-lg">添加公司信息</button>
                        </a>
                        <button type="button" class="btn btn-danger btn-lg delete">删除</button>
                    </div>
                    <div class="alert alert-warning alert-dismissable hidden operatorTip">
                        <button type="button" class="close" data-dismiss="operatorTip"
                                aria-hidden="true">
                            &times;
                        </button>
                        <span></span>
                    </div>
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table width="100%" class="table table-striped table-bordered table-hover"
                                   id="dataTables-fiscalDetail">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="checkAll"/></th>
                                    <th>项目名称</th>
                                    <th>描述</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                    <ul class="pagination pagination-right"></ul>
                </div>
            </div>
        </div>
    </div>


    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        公司信息管理操作确认
                    </h4>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary confirm">
                        确定
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

</div>


<script>

    $(document).ready(function () {
        var pagination = {
            initStatus: false,      //分页插件初始化状态-true:已经构建了分页插件、false:还未构建
            data: {},
            page: {
                pageNo: 1,
                pageSize: 10
            },
            init: function () {
                //初始化分页
                $(".pagination").createPage({
                    pageCount: pagination.page.pageCount,
                    current: pagination.page.pageNo,
                    initStatus: pagination.initStatus,
                    backFn: function (pageNo) {
                        pagination.page.pageNo = pageNo;
                        pagination.list();
                    }
                });

                pagination.initStatus = true;
            },


            list: function () {
                $("#dataTables-fiscalDetail tbody").html("");
                $(".checkAll").prop("checked", false);

                $.ajax({
                    type: "POST",
                    url: "/system_organzation/getListByOrganization",
                    data: $.extend({}, pagination.data, pagination.page),
                    success: function (response) {
                        //设置分页
                        pagination.page.pageNo = response.data.pageNo;
                        pagination.page.pageCount = response.data.pageCount;
                        pagination.init();
                        if (response.data.totalCount > 0) {
                            var tableBody = "";
                            $(response.data.list).each(function () {
                                var subDetails = this.details;

                                if (subDetails != null && subDetails.length > 20) {
                                    subDetails = subDetails.substring(0, 20) + " ... ";
                                }

                                tableBody += '<tr>'
                                        + '<td><input type="checkbox" class="itemCheck" value="' + this.id + '"/></td>'
                                        + '<td>' + this.name + '</td>'
                                        + '<td title="' + this.details + '">' + subDetails + '</td>'
                                        + '<td>' +
                                        '<button type="button" class="btn btn-outline btn-info detail">详情</button>&nbsp;' +

//                                        '<button type="button" class="btn btn-outline btn-info APIsel">API查询</button>&nbsp;' +

                                        ' <button type="button" class="btn btn-outline btn-warning update">修改</button>&nbsp;' +
                                        '<button type="button" class="btn btn-outline btn-danger singleDelete">注销</button></td>' + +'</tr>';
                            });

                            $("#dataTables-fiscalDetail tbody").html(tableBody);
                        } else {
                            $("#dataTables-fiscalDetail tbody").html('<tr class="text-center"><td colspan="4">查无数据</td></tr>');
                        }
                    }
                });
            }
        };

        //获取列表数据
        pagination.list();

        $("#search").click(function () {
            pagination.page.pageNo = 1;
            pagination.list();
        });

        $(".delete").click(function () {
            //清空ids集合
            ids.length = 0;
            hideOperatorTip();
            //获取当前数据表中，被选中的数据
            $("input[type='checkbox']:checked").each(function () {
                if (!$(this).hasClass("checkAll")) {
                    ids.push($(this).val());
                }
            });

            if (ids.length == 0) {
                $(".operatorTip").removeClass("hidden").find("span").text('请选择需要删除的数据');
            } else {
                $('#myModal').modal();
                $(".modal-body").text("是否删除所选数据，被删除数据无法还原！");
            }
        });

        $(".operatorTip .close").click(function () {
            hideOperatorTip();
            return false;
        });

        var ids = new Array();
        $(".confirm").click(function () {
            $.ajax({
                type: "post",
                url: "/system_organzation/delete",
                traditional: true,
                data: {"id": ids},
                success: function (response) {
                    $("#myModal").modal('hide')
                    $(".operatorTip").removeClass("hidden").find("span").text(response.message);
                    if (response.code != "200") {
                        $("#myModal").modal('hide')
                        $(".operatorTip").removeClass("hidden").find("span").text(response.message);
                    } else {
                        pagination.page.pageNo = 1;
                        pagination.list();

                        //3秒后自动关闭警告框
                        setTimeout("hideOperatorTip()", 3000);
                    }
                }
            });
        });

        //单个数据内容删除
        $("#dataTables-fiscalDetail tbody").on("click", ".singleDelete", function () {
            $('#myModal').modal();
            $(".modal-body").text("是否删除所选数据，被删除数据无法还原！");

            //清空ids集合
            ids.length = 0;
            hideOperatorTip();
            ids.push($(this).parents("tr").find("input[type='checkbox']").val());
        })

        //数据修改
        $("#dataTables-fiscalDetail tbody").on("click", ".update", function () {
            window.location.href = "/system-manage/gotoPage?url=organization/organupdate&id=" + $(this).parents("tr").find("input[type='checkbox']").val();
        })

        //数据详细信息
        $("#dataTables-fiscalDetail tbody").on("click", ".detail", function () {
            window.location.href = "/system-manage/gotoPage?url=organization/detail&id=" + $(this).parents("tr").find("input[type='checkbox']").val();
        })

        //API信息查询
        $("#dataTables-fiscalDetail tbody").on("click", ".APIsel", function () {
            window.location.href = "/system-manage/gotoPage?url=organization/selectAPI&id=" + $(this).parents("tr").find("input[type='checkbox']").val();
        })
    });

    /**
     * 隐藏警告框
     */
    function hideOperatorTip() {
        $(".operatorTip").addClass("hidden")
    }


</script>

</body>

</html>
