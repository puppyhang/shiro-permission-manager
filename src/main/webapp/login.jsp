<%--suppress JspAbsolutePathInspection --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=" zh-cn">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link href="/WEB-RES/plugins/bootstrap3/css/bootstrap.css" type="text/css" rel="stylesheet">
</head>
<body>

<div class="container">
    <%--form start ---------------------------------------------------%>
    <div class="col-sm-3"></div>
    <%--form-horizontal 是指水平表单样式--%>
    <form class="form-horizontal col-sm-6" action="/auth/login" method="post" onsubmit="return onLogin(this)">
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
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-10">
                <input class="form-control" type="submit" value="登录"/>
            </div>
        </div>
    </form>
    <div class="col-sm-3"></div>
    <%--form end -----------------------------------------------------%>
</div>

<script src="WEB-RES/js/jquery-1.9.1.js"></script>
<script src="WEB-RES/js/jquery.form.js"></script>
<script src="WEB-RES/plugins/jquery-validation-1.14.0/jquery.validate.js"></script>
<script src="WEB-RES/plugins/jquery-validation-1.14.0/localization/messages_zh.js"></script>
<script src="WEB-RES/plugins/bootstrap3/js/bootstrap.js"></script>
<script>
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
</script>
</body>
</html>
