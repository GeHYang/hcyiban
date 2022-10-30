<%--
  Created by IntelliJ IDEA.
  User: Yang
  Date: 2022/6/7
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程表上传</title>
    <link rel="stylesheet" href="/page/css/normalize.css">
    <link rel="stylesheet" href="/page/css/iconfont.css">
    <link rel="stylesheet" href="/page/css/public.css">
    <script src="/page/js/jquery.js"></script>
    <script src="/page/js/public.js"></script>
    <script src="/page/js/E.js"></script>
</head>
<body>
<div class="view">
    <!-- 头部 -->
    <div class="header1">
        <a id="back">
            <a id="backBtn" onclick="window.history.back();"></a>
        </a>
        <a id="h_text">课程表上传</a>
        <a id=""></a>
    </div>
    <!-- 主体 -->
    <div class="main">
        <h2 style="text-align: center;">请填写学号与姓名<br>并在有课的时间段填上对应信息<br>东区有课填<span style="color: red;"> 1 </span>西区填<span style="color: red;"> 2
    </span><br>无课不用填</h2>
        <div class="tb">
            <table cellspacing="0" cellspadding="0">
                <tr>
                    <td>学号</td>
                    <td colspan="6"><input id="sid" type="text" pattern="20[0-9]{8}" required></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td colspan="6"><input id="sname" type="text"></td>
                </tr>
                <tr>
                    <th>时间</th>
                    <th>星期一</th>
                    <th>星期二</th>
                    <th>星期三</th>
                    <th>星期四</th>
                    <th>星期五</th>
                    <th>星期六</th>
                </tr>
                <tr>
                    <td rowspan="2">8:00-9:25</td>
                    <td rowspan="2"><input class="mon" type="number"></td>
                    <td rowspan="2"><input class="tue" type="number"></td>
                    <td rowspan="2"><input class="wed" type="number"></td>
                    <td rowspan="2"><input class="thurs" type="number"></td>
                    <td rowspan="2"><input class="fri" type="number"></td>
                    <td rowspan="2"><input class="sat" type="number"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td rowspan="3">9:50-12:00</td>
                    <td rowspan="3"><input class="mon" type="number"></td>
                    <td rowspan="3"><input class="tue" type="number"></td>
                    <td rowspan="3"><input class="wed" type="number"></td>
                    <td rowspan="3"><input class="thurs" type="number"></td>
                    <td rowspan="3"><input class="fri" type="number"></td>
                    <td rowspan="3"><input class="sat" type="number"></td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td rowspan="2">14:40-16:05</td>
                    <td rowspan="2"><input class="mon" type="number"></td>
                    <td rowspan="2"><input class="tue" type="number"></td>
                    <td rowspan="2"><input class="wed" type="number"></td>
                    <td rowspan="2"><input class="thurs" type="number"></td>
                    <td rowspan="2"><input class="fri" type="number"></td>
                    <td rowspan="2"><input class="sat" type="number"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td rowspan="2">16:30-17:55</td>
                    <td rowspan="2"><input class="mon" type="number"></td>
                    <td rowspan="2"><input class="tue" type="number"></td>
                    <td rowspan="2"><input class="wed" type="number"></td>
                    <td rowspan="2"><input class="thurs" type="number"></td>
                    <td rowspan="2"><input class="fri" type="number"></td>
                    <td rowspan="2"><input class="sat" type="number"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td rowspan="2">19:40-21:05</td>
                    <td rowspan="2"><input class="mon" type="number"></td>
                    <td rowspan="2"><input class="tue" type="number"></td>
                    <td rowspan="2"><input class="wed" type="number"></td>
                    <td rowspan="2"><input class="thurs" type="number"></td>
                    <td rowspan="2"><input class="fri" type="number"></td>
                    <td rowspan="2"><input class="sat" type="number"></td>
                </tr>
            </table>
            <a onclick="sendFreeTime()">提交</a>
        </div>
        <style>
            input {
                outline: none;
                box-sizing: border-box;
                border: none;
                font-size: 20px;
            }
            .tb{
                box-sizing: border-box;
            }
            .tb table{
                box-sizing: border-box;
            }
            .tb td{
                border: 1px solid;
            }
            .tb table input,
            .tb table td {
                box-sizing: border-box;
                width: 48px;
                height: 60px;
                text-align: center;
            }

            #sid,
            #sname {
                width: 99%;
                height: 99%;
                color: green;
            }
            #sid:invalid{
                color: red;
            }
            .tb a{
                display: block;
                margin: 10px auto;
                padding: 5px;
                width: 100px;
                border: 1px solid;
                font-size: 20px;
                text-align: center;
                background-color: rgb(243, 168, 69);
                color: white;
            }
            .tb a:active{
                background-color: white;
                color: black;
                border: 1px solid rgb(243, 168, 69);
            }
        </style>
    </div>
</div>
<script>
    function sendFreeTime(){
        E.loading("加载中");
        const tb = document.querySelector(".tb");
        const sid = document.getElementById("sid").value;
        const sname = document.getElementById("sname").value;
        const sidReg = /20[0-9]{8}/
        let data = {
            info:{sid:"",sname:""},
            times:{
                mon:"",
                tue:"",
                wed:"",
                thurs:"",
                fri:"",
                sat:"",
            }
        }
        let cs = {id:1,name:'zhangsan'}
        if(!sidReg.test(sid)){
            E.close();
            alert("学号格式错误");
            return;
        }
        data.info.sid = sid;
        if(!sname){
            E.close();
            alert("请填写姓名");
            return;
        }
        data.info.sname = sname;
        let monValue = [];
        for(let key in data.times){
            let mon = document.querySelectorAll("." + key);
            for(let value of mon){
                if(value.value != 0)
                    data.times[key] += (value.value + ",");
                else
                    data.times[key] += (0 + ",");
            }
            data.times[key] = data.times[key].substring(0,data.times[key].length-1);
        }
        $.ajax({
            url: prefix + "/duty/freetime",
            method:"POST",
            headers: {
                'Authorization': localStorage.getItem("token"),
            },
            data:{
                data: JSON.stringify(data)
            },
            dataType: 'json',
            success:function(res){
                E.close();
                alert(res.msg);
                if(res.code == 200){
                    window.history.back();
                }
            },
            error: function (err) {
                E.close();
                alert("出错啦");
            }
        })
    }
</script>
</body>
</html>
