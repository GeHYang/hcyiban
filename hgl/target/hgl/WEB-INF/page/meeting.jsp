<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="/page/css/normalize.css">
    <link rel="stylesheet" href="/page/css/public.css">
    <link rel="stylesheet" href="/page/css/meeting.css">
    <link rel="shortcut icon" href="/page/font/favicon.ico"/>
    <script src="/page/js/public.js"></script>
    <script src="/page/js/E.js"></script>
    <script src="/page/js/nav.js"></script>
    <script src="/page/js/meeting.js"></script>
    <script src="/page/js/vue.js"></script>
    <script src="/page/js/jquery.js"></script>
</head>
<body>
    <div class="view">
        <!-- 头部 -->
        <div class="header1">
            <a id="back">
                <a id="backBtn" onclick="window.history.back();"></a>
            </a>
            <a id="h_text">会议</a>
            <a id="h_right" onclick="navToURL_out('/view/leave')">请假</a>
        </div>
        <!-- 主体 -->
        <div class="main">
            <div class="m-box" v-for="(item, index) in meetings">
                <div class="m-box-left">
                    <div class="m-title">{{item.mtitle}}
                        <span style="position: absolute; right: 5px;top: 10px;font-size: 13px;font-weight: 700;">会议ID:{{item.mid}}</span></div>
                    <div class="m-abstract">{{item.mcontent}}</div>
                    <div class="m-footer">
                        主持人:<div id="m-admin">{{item.sname}}</div>
                        召开时间: <div id="m-time">{{item.start_time}}</div>
                    </div>
                </div>
                <div class="m-box-right" :class="item.state == 1 ? 'blu' : (item.state == 2 ? 'ye' : 're')" @click="meetingSign(index)">
                    <div id="m-tips">{{item.state == 1 ? '签到' : (item.state == 2 ? '未开始' : '已结束')}}</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>