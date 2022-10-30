<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="/page/css/normalize.css">
    <link rel="stylesheet" href="/page/css/iconfont.css">
    <link rel="stylesheet" href="/page/css/public.css">
    <link rel="stylesheet" href="/page/css/home.css">
    <script src="/page/js/public.js"></script>
    <script src="/page/js/nav.js"></script>
    <script src="/page/js/E.js"></script>
    <script>
        // 关闭成员认证弹窗
        function closeAuth(){
            document.querySelector(".auth-box").style.display = 'none';
        }
        // 显示成员认证
        function showAuth(){
            if(localStorage.getItem("token")){
                E.showModule({
                    content: '已认证',
                    success(){
                        closeAuth();
                    }
                })
            }
            else{
                document.querySelector(".auth-box").style.display = '';
                initYbUid();
            }
        }
        // 提交
        function confirmAuth(){
            let formData = new FormData(document.querySelector("#auth_form"));
            E.ajax({
                url: prefix + '/user',
                method: 'post',
                data: {
                    yb_uid: formData.get("yb_uid"),
                    sid: formData.get("sid"),
                    sname: formData.get("sname")
                },
                async: false,
                responseType: 'json'
            }).then((res) => {
                E.showModule({
                    content: res.msg,
                    success() {
                        if(res.code == 200){
                            closeAuth();
                            navToURL_out("/view/index")
                        }
                    }
                })
            }).catch(() => {
                E.showModule({
                    content: '出错',
                    success(){
                        closeAuth();
                    }
                })
            })
        }
        // 初始化易班ID
        function initYbUid (){
            if(localStorage.getItem("yb_uid"))
                document.querySelector("#yb_uid").value = localStorage.getItem("yb_uid")
            else{
                E.showModule({
                    content: '出错',
                    success(){
                        window.location.href = '/view/index'
                    }
                })
            }
        }
    </script>
</head>
<body>
    <div class="banner">

    </div>
    <!-- 接口入口 -->
    <div class="entrances">
        <a class="item" onclick="navToURL_out('/view/duty')">
            <span class="iconfont icon-qiandao"
                style="color: rgb(87, 226, 45);"
            ></span>
            <span>值班</span>
        </a>
        <a class="item" onclick="navToURL_out('/view/meeting')">
            <span class="iconfont icon-huiyi"
                style="color: rgb(233, 64, 177);"
            ></span>
            <span>会议</span>
        </a>
        <a onclick="navToURL1('/view/questions','questions')" class="item">
            <span class="iconfont icon-wodetiku" 
                style="color: rgb(178, 72, 240);"
            ></span>
            <span>题库</span>
        </a>
        <a class="item" onclick="navToURL_out('/view/leaveList')">
            <span class="iconfont icon-qita"
                style="color: rgb(58, 58, 223);"
            ></span>
            <span>请假情况</span>
        </a>
        <a class="item" onclick="showAuth()">
            <span class="iconfont icon-renzhengyonghu"
                  style="color: rgb(87, 226, 45);"
            ></span>
            <span>易班认证</span>
        </a>
        <%--下面这段为jsp数据，由Java提供 --%>
        <a class="item" onclick="navToURL_out('${requestScope.view.get("vpath")}')" style="display: ${requestScope.view == null ? 'none' : ''};">
            <span class="iconfont ${requestScope.view.get("vicon")}"
                  style="color: rgb(87, 226, 45);"
            ></span>
            <span>${requestScope.view.get("vname")}</span>
        </a>
    </div>
    <!-- 推送 -->
    <div class="push">

    </div>
    <!-- 成员认证 -->
    <!--
    学号、姓名、易班id
   -->
    <div class="auth-box" style="display: none;">
        <form id="auth_form" onsubmit="return false;">
            <h3 align="center">易班成员认证</h3>
            <section>
                <input type="text" name="yb_uid" id="yb_uid" onfocus="this.blur()" required>
                <label for="yb_uid">易班ID</label>
            </section>
            <section>
                <input type="text" name="sid" id="sid" required>
                <label for="sid">学号</label>
            </section>
            <section>
                <input type="text" name="sname" id="sname" required>
                <label for="sname">姓名</label>
            </section>
            <section>
                <button type="button" onclick="closeAuth()">取消</button>
                <button onclick="confirmAuth()">确定</button>
            </section>
        </form>
        <style scoped>
            *{
                margin: 0;
                padding: 0;
            }
            .auth-box{
                position: fixed;
                top: 0;
                left: 0;
                overflow: hidden;
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100vw;
                height: 100vh;
                background-color: rgba(161,163,161, .9);
            }
            .auth-box form{
                user-select: none;
                padding: 15px 20px 15px 20px;
                background-color: #ccc;
                box-shadow: 0 0 10px rgba(58, 53, 53, 0.6);
            }
            .auth-box section{
                position: relative;
                width: 200px;
                margin-top: 20px;
            }
            .auth-box section input{
                box-sizing: border-box;
                width: 200px;
                height: 30px;
                line-height: 30px;
                padding: 5px 3px;
                border: 2px solid #ccc;
                border-right: 3px solid green;
                border-radius: 5px 0% 0% 5px;
                font-size: 18px;
                outline: none;
                color: rgb(46, 125, 194);
            }
            .auth-box section label{
                display: block;
                position: absolute;
                top: 0;
                left: 8px;
                width: 200px;
                height: 30px;
                line-height: 30px;
                color: #777;
            }
            .auth-box section input:valid{
                border-right: 10px solid;
                border-color: green;
            }
            .auth-box section input:valid + label{
                top: -25px;
                color: green;
                font-size: 14px;
            }
            .auth-box section input:focus{
                border-right: 10px solid;
                border-color: green;
            }
            .auth-box section input:focus + label{
                top: -25px;
                color: green;
                font-size: 14px;
            }
            .auth-box section:nth-last-child(1){
                display: flex;
                justify-content: space-around;
            }
            .auth-box section button{
                display: block;
                box-sizing: border-box;
                padding: 2px 15px;
                font-size: 16px;
                border: 2px groove;
                box-shadow: 0 0 3px rgb(228, 228, 228);
            }
            .auth-box section button:nth-child(1){
                background-color: rgb(161, 157, 157);
                color: white;
            }
            .auth-box section button:nth-child(2){
                background-color: rgb(19, 163, 51);
                color: white;
            }
            .auth-box section button:active{
                background-color: rgb(122, 113, 113);
                color: azure;
            }
        </style>
    </div>
</body>
</html>