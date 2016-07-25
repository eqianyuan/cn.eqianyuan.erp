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
    <link href="/css/view/login.css" rel="stylesheet">
    <script src="/js/jquery-1.10.1.min.js"></script>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">驿乾元ERP管理系统</h3>
                </div>
                <div class="panel-body">
                    <label class="login_fail_tip"></label>
                    <div class="form-group">
                        <input class="form-control" placeholder="用户名" id="user_name" type="text" autofocus>
                    </div>
                    <div class="form-group">
                        <input class="form-control" placeholder="密码" id="password" type="password" >
                    </div>
                    <div class="form-group">
                        <input class="form-control verification-code-ipt" type="text" placeholder="验证码" id="validate_code" maxlength="4">
                        <img class="verification-code-img" src="${pageContext.request.contextPath}/verifyCode" title="点击重新获取验证码"/>
                    </div>
                    <a href="javascript:;" class="btn btn-lg btn-success btn-block submit">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        /**
         * 如果当前窗口是嵌套在iframe中，则设置顶层页面为当前页面
         */
        if (window.top != window.self)
        {
            window.top.location.href = "${pageContext.request.contextPath}";
        }

        /**
         * 验证码重新载入
         */
        var verifyCodeReload = function(){
            var url = $(".verification-code-img").attr("src");
            if(url.indexOf("?") != -1 ){
                url = url.substr(0, url.indexOf("?"));
            }

            url += "?" + new Date().valueOf();

            $(".verification-code-img").attr("src", url);
        };

        /**
         * 登录提交
         */
        var submitByLogin = function(){
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/system-manage/login",
                data: {
                    user_name: $("#user_name").val(),
                    password: $("#password").val(),
                    verify_code: $("#validate_code").val()
                },
                success: function(resp){
                    if(resp.code == "200"){
                        //跳转到主页
                        window.location.href = "${pageContext.request.contextPath}/system-manage/index";
                        return ;
                    }
                    //重新载入验证码
                    verifyCodeReload();
                    //提示错误信息
                    $(".login_fail_tip").text(resp.message);
                }
            });
        }

        /**
         * 点击更换验证码
         */
        $(".verification-code-img").click(function(){
            verifyCodeReload();
        });

        /**
         * 回车键提交登录
         */
        $('.form-control').keypress(function (e) {
            if (e.which == 13) {
                submitByLogin();
            }
        });

        /**
         * 登录
         */
        $(".submit").click(function(){
            submitByLogin();
        });
    })

</script>
</html>
