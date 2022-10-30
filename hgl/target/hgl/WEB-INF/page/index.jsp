<!-- 
    顶部标题、底部导航栏
 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>惠管理</title>
    <link rel="stylesheet" href="/page/css/normalize.css">
    <link rel="stylesheet" href="/page/css/public.css">
    <link rel="stylesheet" href="/page/css/index.css">
    <link rel="stylesheet" href="/page/css/iconfont.css">
    <link rel="shortcut icon" href="/page/font/favicon.ico"/>
    <script src="/page/js/public.js"></script>
    <script src="/page/js/nav.js"></script>
    <script src="/page/js/jquery.js"></script>
    <script src="/page/js/aes_1.js"></script>
    <script src="/page/js/E.js"></script>
    <script src="/page/js/index.js"></script>
</head>
<body>
    <!-- 视口 -->
    <div class="view">
        <!-- 头部 -->
        <!-- <div class="header1">
            <a id="h_left"></a>
            <a id="h_text">首页</a>
            <a id="h_right"></a>
        </div> -->
        <!-- 主体 -->
        <div class="main">
            <iframe src="" id="iframe" frameborder="0"></iframe>
            <!-- 设置iframe高度 -->
            <script>
                let height = document.body.clientHeight - 50;
                document.querySelector("#iframe").style.height = height + "px";
            </script>
        </div>
        <!-- 底部 -->
        <div class="footer"></div>
        <!-- 导航栏 -->
        <div class="nav">
            <a  id="home"
                onclick="navToURL('/view/home','home')"
            >
                <span class="iconfont icon-shouye"></span>
                <span>首页</span>
            </a>
            <a  id="questions" 
                onclick="navToURL('/view/questions','questions')"
            >
                <span class="iconfont icon-lujing103"></span>
                <span>题库</span>
            </a>
            <a  id="person"
                onclick="navToURL('/view/person','person')"
            >
                <span class="iconfont icon-my"></span>
                <span>个人中心</span>
            </a>
        </div>
    </div>
</body>
</html>