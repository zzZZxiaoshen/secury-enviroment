<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>数据统计</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${pageContext.request.contextPath}/static/css/plugins/flexible/flexible.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/plugins/heightchats/highcharts.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/business/phone/data-list.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/static/js/plugins/flexible/flexible.js"></script>
    <script>
        let rootPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body class="hold-transition login-page">
<div id="head">
    <div id="customerNum">
        <div class="userNum"></div>
        <div class="todayNum"></div>
    </div>
    <div id="nav">
        <i class="fa-navicon fa nav" style="font-size:0.6rem;color:#FFFFFF"></i>
    </div>
</div>
<div id="navbar">
    <div class="user">
        <div class="profilePhoto"></div>
        <div class="userName"></div>
    </div>
    <ul>
        <li>订单统计</li>
        <li>小程序统计</li>
    </ul>
    <div class="button">退出</div>
</div>
<div id="body">
     <h1>今日数据</h1>
     <div id="container">

     </div>
</div>
<div id="footer">
    <div style="width: 0.6rem;"></div>
    <div class="year"></div>
    <div class="refresh">
        <img src="${pageContext.request.contextPath}/static/images/refresh.png">
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plugins/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/hchats/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/plugins/toast/toast-loading.js"></script>
<script src="${pageContext.request.contextPath}/static/js/business/order.js?ver=2018112803"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/business/common.js"></script>
</body>
</html>
