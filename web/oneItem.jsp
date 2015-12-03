<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/29
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head> <meta charset="utf-8" />
  <title>最新项目-志愿襄阳</title>

  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
  <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  <style>
    #header{
      background-color: brown;
      color: white;
      text-shadow: 0 -0.5px 0 black;
      width: 100%;
      margin-bottom: 4%;
    }
    #header span{

      display: inline-block;
      width: 40%;
      padding-left: 3%;
      padding-top: 4%;
      padding-bottom: 4%;
      margin-left: 2%;
    }
    #header a{
      color: white;
      display: inline-block;
      width: 45%;
      padding-top: 4%;
      padding-bottom: 4%;
      text-align:right;
      text-shadow: 0 -0.5px 0 black;
    }

    .list-group-item{
      margin-bottom: 6%;
      border-radius: 5px;

    }
    .list-group-item a{
      color: black;


    }
    ul{
      margin-top: 3%;
    }
    body{
      background-color:#EEEEEE ;
    }
    p,#service_location{
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<div id="header">
  <span>欢迎访问志愿襄阳</span>
  <a>登录</a>
</div>
<div class="container">
  <ul class="list-group">
    <li class="list-group-item">${list.title}</li>
    <li class="list-group-item">
      <p>联系人：${requestScope.list.uname}</p>
      <p>联系电话：${requestScope.list.tel}</p>
      <p>服务开始时间：${requestScope.list.start_time}</p>
      <p>服务结束时间：${requestScope.list.stop_time}</p>
      <span id="service_location">服务地点：湖北文理学院</span>
      <button class="btn btn-success btn-sm col-md-offset-2 col-lg-offset-2">我要报名</button>
    </li>
    <li class="list-group-item">
      <h4>服务内容</h4>
      <p>${requestScope.list.content}</p>
    </li>
  </ul>
</div>
</body>
</html>
