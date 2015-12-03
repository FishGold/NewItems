<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/29
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>最新项目-志愿襄阳</title>
  <meta charset="utf-8" />
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
    a:hover{
      text-decoration: none;
    }
  </style>
</head>
<body>
<div id="header">
  <span>欢迎访问志愿襄阳</span>
  <a>登录</a>
</div>
<div class="container">
  <form role="form">
    <div class="input-group">
      <span class="input-group-addon">项目名称</span>
      <input type="text"  class="form-control"/>
      <span class="input-group-addon">搜索</span>
    </div>
  </form>
  <ul class="list-group" id="ulonly">
    <c:forEach items="${list}" var="i">
      <li  class="list-group-item">
        <a href="item?id=${i.id}">${i.title}<span class="hidden">${i.id}</span></a>
      </li>
    </c:forEach>

  </ul>
</div>

</body>
<script>
  $(window).scroll(function(){
    var scrollTop = $(window).scrollTop();               //滚动条距离顶部的高度
    var scrollHeight = $(document.body).height();           //当前页面的总高度
    var windowHeight = $(window).height();  //当前可视的页面高度
    if((scrollTop+windowHeight) >= scrollHeight){
      var id = $("li:last .hidden").html();
      $.post("more",{"id":id},function(data){
        var obj = eval(data);
        for(var i=0;i<obj.length;i++){
           var liStr = '<li  class="list-group-item"><a href="item?id='+obj[i]["id"]+'">'+obj[i]["title"]+'<span class="hidden">'+obj[i]["id"]+'</span></a> </li>';
           $("#ulonly").append(liStr);
         }

      });


    }
    else;
  });
</script>
</html>
