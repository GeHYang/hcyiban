<!-- 
    值班系统
 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>值班</title>
    <link rel="stylesheet" href="/page/css/normalize.css">
    <link rel="stylesheet" href="/page/css/public.css">
    <link rel="stylesheet" href="/page/css/duty.css">
	<link rel="shortcut icon" href="#" />
    <script src="/page/js/public.js"></script>
    <script src="/page/js/E.js"></script>
    <script src="/page/js/jquery.js"></script>
    <script src="/page/js/nav.js"></script>
    <script src="/page/js/duty.js"></script>
</head>
<body>
    
    <div class="view">
        <!-- 头部 -->
        <div class="header1">
            <a id="back">
                <a id="backBtn" onclick="window.history.back();"></a>
            </a>
            <a id="h_text">值班</a>
            <a id=""></a>
        </div>
        <!-- 主体 -->
        <div class="main">
            <!-- 日历牌 -->
            <div class="calendar-box">
                <div class="c-left">
                    <div class="c-top">
                        <a id="pre" class="bc" onclick="showMsg('暂未开启');">
                            <i class="bk fz1"></i>
                        </a>
                        <span id="year-month"></span>
                        <a id="next" class="bc" onclick="showMsg('暂未开启');">
                            <i class="bk"></i>
                        </a>
                    </div>
                    <div class="c-content">
                        <table id="calendar" cellspacing="0" cellpadding="0">
                            <tr>
                                <th>一</th>
                                <th>二</th>
                                <th>三</th>
                                <th>四</th>
                                <th>五</th>
                                <th>六</th>
                                <th>日</th>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="c-right">
                    <span id="r-month"></span>
                    <div class="day">
                        <span id="day_day"></span>
                    </div>
                    <div class="sign-in">
                        <a onclick="duty.sign()">签到</a>
                        <a onclick="navToURL_out('/view/leave')">请假</a>
                        <a onclick="E('.repair')[0].style.display = ''">补签</a>
                    </div>
                </div>
            </div>
            <!-- 当天签到 -->
            <div class="tody">
                <!-- 标签 -->
                <div class="tips">
                    <span>当天签到</span>
                    <div class="item-box"></div>
                </div>
                <div class="t-table-box">
                    <table class="t-table ">
                        <tr>
                            <th>时间段</th>
                            <th>状态</th>
                        </tr>
                    </table>
                </div>
            </div>
            <!-- 当月签到 -->
            <div class="same-month">
                <!-- 标签 -->
                <div class="tips">
                    <span>当月签到</span>
                    <div class="item-box"></div>
                </div>
                <div class="m-table-box">
                    <table class="t-table ">
                        <tr>
                            <th>时间</th>
                            <th>状态</th>
                            <th>节次</th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- 签到 -->
        <div class="sign-box" style="display: none;">
            <div class="s-box">
                <h2>签到成功</h2>
                <div class="s-content">
                    <div>学号:<span id="sid">2019120071</span></div>
                    <div>姓名:<span id="sname">欧阳林</span></div>
                    <div>签到时间:<span id="time">11-11 08:50:40</span></div>
                    <button id="s-ok" onclick="E('.sign-box')[0].style.display = 'none';">确定</button>
                </div>
            </div>
        </div>
        <!-- 补签 -->
        <div class="repair" style="display: none;">
            <div class="repair-box">
                <h4 align="center">请选择值班开始时间</h4>
                <input type="datetime-local" name="" id="repair_datetime">
                <div>
                    <button onclick="E('.repair')[0].style.display = 'none'">取消</button>
                    <button onclick="E('.repair')[0].style.display = 'none';duty.repair()">确定</button>
                </div>
            </div>
            <style scoped>
                .repair{
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    overflow: hidden;
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background-color: rgba(192,192,192,.4);
                }
                .repair-box{
                    display: flex;
                    flex-direction: column;
                    padding: 30px 10px;
                    background-color: #c8e7b0;
                }
                .repair-box input{
                    font-size: 18px;
                }
                .repair-box div{
                    display: flex;
                    justify-content: space-between;
                    padding: 10px;
                }
                .repair-box button{
                    border: 1px solid;
                    background-color: transparent;
                }
                .repair-box button:nth-child(1){
                    background-color: #ececec;
                }
                .repair-box button:nth-child(2){
                    background-color: green;
                    color: white;
                }
                .repair-box button:nth-child(1):active{
                    background-color: #ccc;
                    color: white;
                }
                .repair-box button:nth-child(2):active{
                    background-color: #fff;
                    color: black;
                }
            </style>
        </div>
    </div>
    
</body>
</html>