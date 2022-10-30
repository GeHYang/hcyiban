<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>请假</title>
  <link rel="shortcut icon" href="/page/font/favicon.ico"/>
  <style>
    *{
      margin: 0;
      padding: 0;
    }
    body{
      overflow: hidden;
      background: linear-gradient(to bottom,#5bcaca,#7cd881) fixed;
    }
    /* 顶部 */
    .header1{
        z-index: 100;
        display: flex;
        width: 100%;
        background-color: white;
        font-size: 20px;
        font-weight: bold;
    }
    .header1 a{
        width: 33.3%;
    }
    /* 顶部文字 */
    #h_text{
        line-height: 50px;
        text-align: center;
    }
    /* 返回 */
    #back{
        position: relative;
    }
    /* 返回按钮样式 */
    #backBtn{
        display: block;
        position: absolute;
        left: 2%;
        width: 15px;
        height: 15px;
        box-sizing: border-box;
        margin: 17.5px 0;
        border-top: 1px solid;
        border-left: 1px solid;
        transform: translate(50%,0) rotate(-45deg);
    }
    #h_right{
        line-height: 5rem;
        text-align: right;
        box-sizing: border-box;
        padding-right: 10px;
        font-size: 16px;
    }
    .leave-view{
      display: flex;
      justify-content: center;
      padding: 20px 0;
    }
    .leave-tb td{
      box-sizing: border-box;
      display: inline-block;
      width: 210px;
      height: 35px;
      padding: 0 5px;
      line-height: 35px;
      font-size: 18px;
    }
    .leave-tb tr td:nth-child(1){
      overflow: hidden;
      width: 90px;
      text-align: justify;
    }
    .leave-tb tr td:nth-child(1) i{
      display: inline-block;
      width: 100%;
    }
    .leave-tb input, .leave-tb select, .leave-tb textarea{
      box-sizing: border-box;
      width: 200px;
      padding: 0 2px;
      font-size: 18px;
      text-align: center;
      border: 1px solid #555;
      border-right: 5px groove rgb(214, 93, 13);
      outline: none;
      resize: none;
    }
    .leave-tb input:valid, 
    .leave-tb select:valid, 
    .leave-tb textarea:valid{
      border-color: green;
      border-right: 5px groove rgb(20, 240, 104);
    }
    .leave-tb input:nth-child(1),
    .leave-tb input:nth-child(2){
        border-color: green;
        border-right: 5px groove rgb(20, 240, 104);
    }
    .leave-tb tr:nth-last-child(3) td:nth-child(2), .leave-tb tr:nth-last-child(3) textarea{
      height: 100px;
      width: 200px;
      text-align: left;
    }
    .leave-tb tr:nth-last-child(2) input{
      border: none;
      font-size: 14px;
    }
    .leave-tb tr:nth-last-child(1) button{
      padding: 2px 15px;
      background-color: #5bcaca;
      color: white;
    }
    .leave-tb tr:nth-last-child(1) button:nth-child(2){
      background-color: #7cd881;
    }
  </style>
  <script src="/page/js/public.js"></script>
  <script src="/page/js/vue.js" type="text/javascript"></script>
  <script src="/page/js/leave.js"></script>
  <script src="/page/js/jquery.js" type="text/javascript"></script>
  <script src="/page/js/E.js" type="text/javascript"></script>
</head>
<body>
    <!-- 头部 -->
    <div class="header1">
        <a id="back">
            <a id="backBtn" onclick="window.history.back();"></a>
        </a>
        <a id="h_text">请假</a>
        <a id=""></a>
    </div>
  <div class="leave-view" id="app">
      <form onsubmit="return false;" ref="form" enctype="multipart/form-data">
    <table class="leave-tb" cellspacing="0" cellpadding="0">
        <tr>
          <th colspan="2"><h2>请假信息提交</h2></th>
        </tr>
        <tr>
          <td>学号<i></i></td>
          <td><input type="text" placeholder="请输入学号" v-model="leave.sid" disabled required></td>
        </tr>
        <tr>
          <td>姓名<i></i></td>
          <td><input type="text" placeholder="请输入姓名" v-model="leave.sname" disabled required></td>
        </tr>
        <tr>
          <td>请假类型<i></i></td>
          <td>
            <select v-model="leave.leaveType" required>
              <option value="0">--请选择--</option>
              <option value="1">值班请假</option>
              <option value="2">会议请假</option>
            </select>
          </td>
        </tr>
        <tr v-if="leave.leaveType == 1">
          <td>请假日期<i></i></td>
          <td><input type="date" v-model="leave.date" required></td>
        </tr>
        <tr v-if="leave.leaveType == 1">
          <td>请假时间<i></i></td>
          <td>
            <select required v-model="leave.time">
              <option value="">--请选择--</option>
              <option v-for="item in leave.leaveOptions[leave.leaveType - 1]" :value="item.value">{{item.value}}</option>
            </select>
          </td>
        </tr>
        <tr v-if="leave.leaveType == 2">
          <td>会议编号<i></i></td>
          <td>
            <select required v-model="leave.mid">
              <option value="">--请选择--</option>
              <option v-for="item in leave.leaveOptions[leave.leaveType - 1]" :value="item">{{item}}</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>请假事由<i></i></td>
          <td><textarea placeholder="请假事由" v-model="leave.content" required></textarea></td>
        </tr>
        <tr>
          <td>请假截图<i></i></td>
          <td>
            <input type="file" id="file" name="file">
          </td>
        </tr>
        <tr>
          <td></td>
          <td>
            <button type="reset">取消</button>
            <button @click="confirmLeave">确定</button>
          </td>
        </tr>
    </table>
      </form>
  </div>
</body>
</html>