<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>请假详情</title>
		<link rel="shortcut icon" href="/page/font/favicon.ico"/>
		<link rel="stylesheet" href="/page/css/public.css">
		<script src="/page/js/public.js"></script>
		<script src="/page/js/nav.js"></script>
		<script src="/page/js/E.js"></script>
		<script src="/page/js/leaveList.js"></script>
	</head>
	<body>
		<div class="header1">
            <a id="back">
                <a id="backBtn" onclick="window.history.back();"></a>
            </a>
            <a id="h_text">我的请假</a>
            <a id="h_right" onclick="navToURL_out('/view/leave')">请假</a>
        </div>
		<div class="leaveList-main">
			<div class="leaveList">
				<section class="btn-box">
					<button class="btn btn-on" onclick="showDutyLeaveList(0)">值班请假</button>
					<button class="btn" onclick="showMeetingLeaveList(1)">会议请假</button>
				</section>
				<ul class="leaveListUl">
					<!-- <li>
						<div class="leaveList-item">
							<div class="limg">
								<img src="./imgs/cs.png" alt="">
							</div>
							<div class="lintro">
								<a class="sname">姓名：张三</a>
								<a class="ltype">请假类型：值班请假</a>
								<a class="lsend_time">请假时间：2022-4-11 15:06:00</a>
								<a class="ltime">时间段：2022-4-11 14:40-16:05</a>
								<a class="lcontent">内容：开班会开班会开班会开班会开班会开班会开班会开班会开班会</a>
							</div>
							<div class="lstatus">
								<a>审核中</a>
							</div>
						</div>
					</li> -->
				</ul>
			</div>
		</div>
	</body>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		li{
			list-style: none;
		}
		.leaveList ul{
			display: flex;
			flex-direction: column;
		}
		.leaveList-item{
			display: flex;
		}
		.limg img{
			width: 90px;
			height: 90px;
		}
		.lintro{
			overflow: hidden;
			display: flex;
			flex-direction: column;
			height: 90px;
			/* width: 215px; */
			background-color: #00FFFF;
		}
		.lintro a{
			font-size: 12px;
		}
		.lcontent{
			display: block;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		.lstatus{
			width: 80px;
			height: 90px;
			text-align: center;
			background-color: #7FFFD4;
		}
		.lstatus a{
			line-height: 90px;
		}
		.btn-box{
			display: flex;
			justify-content: center;
		}
		.btn{
			width: 40%;
			font-size: 16px;
			border: 1px solid green;
			background-color: thistle;
		}
		.btn-on{
			background-color: #7cd881;
			color: white;
		}
		.btn:active{
			background-color: #00FFFF;
		}
	</style>
	<script type="text/javascript">
		
	</script>
</html>
