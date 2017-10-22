<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=" zh-cn">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link href="/WEB-RES/plugins/bootstrap3/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="/WEB-RES/plugins/layer3/theme/default/layer.css" type="text/css" rel="stylesheet">
    <link href="/WEB-RES/plugins/icheck/skins/square/red.css" rel="stylesheet">
    <style>
        .panel {
            border: 0px solid #1b6d85;
        !important;
        }
    </style>
</head>
<body>

<div class="container">
    <%--记录异常信息--%>
    <input id="error_message" type="hidden" value="${authenticationError}"/>
    <%--form start ---------------------------------------------------%>
    <div class="panel panel-primary"
         style="width: 500px;margin:30px auto;box-shadow: #1b6d85 5px 5px 10px ">
        <div class="panel-heading">
            <h3 class="panel-title">登录</h3>
        </div>
        <div class="panel-body">
            <form class="form-horizontal " action="/login"
                  method="post" onsubmit="return $.onLogin(this)">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input class="form-control" placeholder="请输入用户名" id="username"
                               name="username" value="Ternence" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密&nbsp;&nbsp;&nbsp;码</label>
                    <div class="col-sm-10">
                        <input class="form-control" value="a123456"
                               placeholder="请输入密码" id="password" name="password" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-7">
                        <input class="form-control" placeholder="请输入验证码" id="captcha"
                               name="captcha" required>
                    </div>
                    <div class="col-sm-3 pull-left" style="padding: 0 !important;">
                        <img src="/auth/captcha" alt="点击切换验证码"
                             style="width: 100px; height: 35px;margin-left:10px"
                             id="validateCodeImage">
                    </div>
                </div>

                <div class="form-group">
                    <div class="pull-right" style="margin-right:15px">
                        <label for="remember-me">记住我</label>
                        <input id="remember-me" type="checkbox" name="rememberMe">
                    </div>
                </div>


                <input class="btn btn-primary col-sm-3"
                       type="submit" style="width: 50%;float: right"
                       value="登 录"/>
            </form>
        </div>
    </div>
    <%--form-horizontal 是指水平表单样式--%>
    <%--form end -----------------------------------------------------%>
</div>

<script src="WEB-RES/js/jquery-1.9.1.js"></script>
<script src="WEB-RES/js/jquery.form.js"></script>
<script src="WEB-RES/plugins/jquery-validation-1.14.0/jquery.validate.js"></script>
<script src="WEB-RES/plugins/jquery-validation-1.14.0/localization/messages_zh.js"></script>
<script src="WEB-RES/plugins/layer3/layer.js"></script>
<script src="WEB-RES/plugins/bootstrap3/js/bootstrap.js"></script>
<script src="WEB-RES/plugins/icheck/icheck.js"></script>
<script>
    $(function () {

        var $validateCodeImage = $("#validateCodeImage");
        $validateCodeImage.tooltip({
            title: '点击图片刷新验证码',
            trigger: 'hover focus'
        });
        $validateCodeImage.click(function () {
            console.log("刷新图片");
            /*这个随机数只是为了让每次src的指向都不一样，避免浏览器一直读缓存而不刷新图片*/
            this.src = '/auth/captcha' + "?" + Math.random();
        });

        $("#remember-me").iCheck({
            checkboxClass: 'icheckbox_square-red',
            radioClass: 'iradio_square-red',
            ifClicked: function () {
                alert("--")
            }
        });

        var message = $("#error_message").val();
        //长度大于0表示有错误信息
        if (message.length > 0) {
            layer.alert(message, {
                icon: 5,
                zIndex: 10000
            });
        }

        //执行表单的登录逻辑
        function onLogin(form) {
            var $form = $(form);
            //执行很简单的表单验证
            $form.validate({
                submitHandler: function (form) {
                    //校验通过执行这个方法
                    $(form).ajaxSubmit();
                    //阻止默认的表单提交行为
                    return false;
                }
            });
        }
    });

</script>
</body>
</html>
