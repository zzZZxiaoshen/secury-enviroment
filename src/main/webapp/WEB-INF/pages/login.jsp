<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${pageContext.request.contextPath}/static/css/plugins/flexible/flexible.css" type="text/css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/business/phone/phone-login.css" type="text/css"
          rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/static/js/plugins/flexible/flexible.js"></script>
</head>
<body class="hold-transition login-page">
<div class="ground">
    <div class="message">
        <div class="message-img">
            <img src="https://wxapp.marsfood.cn/Fppo1xVq8_KpOG14XBhAe24PnThL">
        </div>
        <div class="message-name">
            <span>admin</span>
        </div>
    </div>
    <div class="users">
        <div style="position: relative;font-size: 0;">
            <div class="username">账号</div>
            <input id="username" class="inputName" placeholder="请输入您的登录账号" type="text">
            <img id="usernameFalse" class="change" src="https://wxapp.marsfood.cn/FlMoXDEUbhYNeCw3ePM4HdhIbXTO" alt="">
            <img id="usernameTrue" class="change" src="https://wxapp.marsfood.cn/FkpBeWDmfocweerwDyK9XorotTJE" alt="">
        </div>
        <div style="position: relative;font-size: 0;" class="password">
            <div class="username">密码</div>
            <input id="password" class="inputName" placeholder="请输入您的登录密码" type="password">
            <img id="passwordFalse" class="change" src="https://wxapp.marsfood.cn/FlMoXDEUbhYNeCw3ePM4HdhIbXTO" alt="">
            <img id="passwordTrue" class="change" src="https://wxapp.marsfood.cn/FkpBeWDmfocweerwDyK9XorotTJE" alt="">
        </div>
        <div style="font-size: 0;" class="signIn">
            <button id="btnLogin">登录</button>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/plugins/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/plugins/toast/toast-loading.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/business/common.js"></script>
<script type="text/javascript">
    $(function () {
        let $username = $("#username");
        let $usernameTrue = $("#usernameTrue");
        let $password = $("#password");
        let $passwordTrue = $("#passwordTrue");
        let $usernameFalse = $("#usernameFalse");
        let $passwordFalse = $("#passwordFalse");
        // 如果有token跳首页
        if ($.cookie.get("token")) {
            location.href = "${pageContext.request.contextPath}/view/order";
        }
        // 用户名输入事件
        $username.on("input", function () {
            let username = $username.val().trim();
            if (username || username.length > 0) {
                $username.css("border-bottom", "0.04rem solid rgba(255,255,255,1)");
                $usernameTrue.css("display", "block");
            }
        });
        // 密码输入事件
        $password.on("input", function () {
            let password = $password.val().trim();
            if (password || password.length > 0) {
                $password.css("border-bottom", "0.04rem solid rgba(255,255,255,1)");
                $passwordTrue.css("display", "block");
            }
        });
        // 登录点击事件
        $("#btnLogin").on("click", function () {
            let username = $username.val().trim();
            let password = $password.val().trim();
            if (!username || username.length === 0) {
                $username.css("border-bottom", "0.04rem solid rgba(255,98,41,1)");
                $usernameTrue.css("display", "none");
                $usernameFalse.css("display", "block");
            } else if (!password || password.length === 0) {
                $password.css("border-bottom", "0.04rem solid rgba(255,98,41,1)");
                $passwordTrue.css("display", "none");
                $passwordFalse.css("display", "block");
            } else {
                $.ajax({
                    method: 'post',
                    dataType: 'json',
                    url: "${pageContext.request.contextPath}/user/login",
                    data: {
                        username: username,
                        password: password
                    },
                    success: function (res) {
                        if (res.code === 200) {
                            $.cookie.set("token", res.data, 1);
                            window.localStorage.setItem("userinfo", JSON.stringify(res.data.userinfo));
                            // 如果是登陆页面跳首页
                            if (location.href.indexOf("/view/phone/login") !== -1) {
                                location.href = "${pageContext.request.contextPath}/view/order";
                            } else {
                                // 不是登陆页面，原页面刷新
                                location.reload();
                            }
                        } else if (res.code === 209) {
                            $username.css("border-bottom", "0.04rem solid rgba(255,98,41,1)");
                            $usernameTrue.css("display", "none");
                            $usernameFalse.css("display", "block");
                        } else if (res.code === 215) {
                            location.href = "${pageContext.request.contextPath}/view/login";
                        } else {
                            $password.css("border-bottom", "0.04rem solid rgba(255,98,41,1)");
                            $passwordTrue.css("display", "none");
                            $passwordFalse.css("display", "block");
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>