
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/view/common.css" rel="stylesheet">
    <link href="/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-1.10.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>

<body>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">修改公司信息</h1>
            </div>
            <!-- /.col-lg-12 -->
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
                                    <!-- 数据序列编号 -->
                                    <input class="form-control hidden" name="id" value="${param.id }">

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

                                    <input type="button" class="btn btn-outline btn-success submit" value="保存">
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

    $(function () {

        $.ajax({
            type: "POST",
            url: "/system_organzation/selectid",
            data: {id: "${param.id}"},
            success: function (response) {
                if (response.code == "200") {
                    var _this = response.data;
                    $("input[name='id']").val(_this.id);
                    //根据test文本取值用input
                    $("input[name='name']").val(_this.name);
                    //根据标签testarea取值
                    $("textarea[name='details']").val(_this.details);
                } else {
                    $("#form-tip").removeClass("hidden alert-warning").addClass("alert-success").show().find("strong").text(response.message);
                    //3秒后自动关闭警告框
                    setTimeout("hideOperatorTip()", 3000);
                }
            }
        });

        $(".submit").click(function () {
            $(this).attr('disabled', "true")

            $.ajax({
                type: "POST",
                url: "/system_organzation/update",
                data: $("form").serialize(),
                success: function (response) {
                    if (response.code == "200") {
                        $("#form-tip").removeClass("hidden alert-warning").addClass("alert-success").show().find("strong").text(response.message);

                        setTimeout(function () {
                            window.location.href = "/gotoPage?url=organization/list";
                        }, 500);
                    } else {
                        $("#form-tip").removeClass("hidden alert-success").addClass("alert-warning").show().find("strong").text(response.message);
                        $(".submit").removeAttr("disabled");
                    }
                }
            });
        });

        $(".back").click(function () {
            window.history.go(-1);
        });
    });

    /**
     * 隐藏警告框
     */
    function hideOperatorTip() {
        $("#form-tip").addClass("hidden")
        window.history.go(-1);
    }

</script>

</body>
</html>
