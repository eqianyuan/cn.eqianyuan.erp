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
        <h1 class="page-header">修改接口信息</h1>
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
                    <label>项目名称</label>
                    <select class="form-control province" id="infoId" name="organizationId">
                      <option value="0">-- 请选择 --</option>
                    </select>
                  </div>

                  <div class="form-group">
                    <label>URL地址</label>
                    <input class="form-control" name="urladdress">

                    <p class="help-block tip">tip.</p>
                  </div>

                  <div class="form-group">
                    <label>描述</label>
                    <textarea class="form-control" rows="3" name="instructions"></textarea>

                    <p class="help-block tip">tip.</p>
                  </div>

                  <div class="form-group">
                    <label>提交方式</label>
                    <input class="form-control" name="submission">

                    <p class="help-block tip">tip.</p>
                  </div>

                  <div class="form-group">
                    <label>参数</label>
                    <textarea class="form-control" rows="3" name="parameter"></textarea>

                    <p class="help-block tip">tip.</p>
                  </div>

                  <div class="form-group">
                    <label>响应内容</label>
                    <textarea class="form-control" rows="3" name="responsecontent"></textarea>

                    <p class="help-block tip">tip.</p>
                  </div>

                  <div class="form-group">
                    <label>错误报告</label>
                    <textarea class="form-control" rows="3" name="errorreporting"></textarea>

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
      url: "/sys_information/selectid",
      data: {id: "${param.id}"},
      success: function (response) {
        if (response.code == "200") {
          var _this = response.data;
          $("input[name='id']").val(_this.id);
          //根据test文本取值用input
          $("input[name='urladdress']").val(_this.urladdress);
          //根据标签testarea取值
          $("textarea[name='instructions']").val(_this.instructions);
          $("input[name='submission']").val(_this.submission);
          $("textarea[name='parameter']").val(_this.parameter);
          $("textarea[name='responsecontent']").val(_this.responsecontent);
          $("textarea[name='errorreporting']").val(_this.errorreporting);
          selectInfo(_this.organizationId);
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
        url: "/sys_information/update",
        data: $("form").serialize(),
        success: function (response) {
          if (response.code == "200") {
            $("#form-tip").removeClass("hidden alert-warning").addClass("alert-success").show().find("strong").text(response.message);

            setTimeout(function () {
              window.location.href = "/gotoPage?url=inforcontent/inforcontentlist";
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

  function selectInfo(organzationId) {
    $.post("/system_organzation/selectname",{
    },function(data){
      var options = "";
      for(var i=0;i<data.length;i++){
        var selected = "";
        if(organzationId == data[i].id){
          selected = "selected=selected";
        }
        options=options+"<option value='"+data[i].id+"' "+selected+">"+data[i].name + "</option>";
      }
      $("#infoId").html(options);
    });
  }

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
