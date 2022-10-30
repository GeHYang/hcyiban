<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>个人中心</title>
  <link rel="stylesheet" href="/page/css/person.css">
  <script src="/page/js/public.js"></script>
  <script src="/page/js/person.js"></script>
  <script src="/page/js/jquery.js"></script>
  <script src="/page/js/E.js"></script>
  <script src="/page/js/nav.js"></script>
</head>
<body>
  <div class="view">
    <div class="v-top">
      <img id="headIcon" src="">
      <section class="circle"></section>
      <section class="circle"></section>
      <section class="circle"></section>
      <section id="v-top-name">
        <span>姓名: <span id="v-top-sname">欧阳林</span></span>
      </section>
    </div>
    <div class="v-center">
      <ul>
        <li class="info_item">
          <div class="i-item">
            <a class="title">易班ID<i></i></a>
            <a class="i-item-text">33331244</a>
          </div>
        </li>
        <li class="info_item">
          <div class="i-item">
            <a class="title">学号<i></i></a>
            <a class="i-item-text">2019120071</a>
          </div>
        </li>
        <li class="info_item">
          <div class="i-item">
            <a class="title">姓名<i></i></a>
            <a class="i-item-text">欧阳林</a>
          </div>
        </li>
        <li class="info_item">
          <div class="i-item">
            <a class="title">部门<i></i></a>
            <div class="selBox">
              <a class="i-item-text">技术研发部</a>
              <select class="selectBox" style="display: none;">
                <option value="1001">主席团</option>
                <option value="1002">行政办公室</option>
                <option value="1003">新闻采编部</option>
                <option value="1004">视觉传达部</option>
                <option value="1005">运营推广部</option>
                <option value="1006">优课开发部</option>
                <option value="1007">技术研发部</option>
                <option value="1008">新媒体部</option>
                <option value="1009">思政编辑部</option>
                <option value="1010">无</option>
              </select>
            </div>
            <a class="i-item-right" onclick="updateInfo(0)">修改</a>
          </div>
        </li>
        <li class="info_item">
          <div class="i-item">
            <a class="title">职称<i></i></a>
            <div class="selBox">
              <a class="i-item-text">职称</a>
              <select class="selectBox" style="display: none;">
                <option value="1001">主席</option>
                <option value="1002">副主席</option>
                <option value="1003">秘书长</option>
                <option value="1004">副秘书长</option>
                <option value="1005">部长</option>
                <option value="1006">副部长</option>
                <option value="1007">干事</option>
              </select>
            </div>
            <a class="i-item-right" onclick="updateInfo(1)">修改</a>
          </div>
        </li>
        <li class="info_item">
          <div class="i-item">
            <a class="title">院系<i></i></a>
            <a class="i-item-text" contenteditable="false">大数据与计算机学院</a>
            <a class="i-item-right" onclick="updateInfo(2)">修改</a>
          </div>
        </li>
        <li class="info_item">
          <div class="i-item">
            <a class="title">Q Q<i></i></a>
            <a class="i-item-text" contenteditable="false">3235997717</a>
            <a class="i-item-right" onclick="updateInfo(3)">修改</a>
          </div>
        </li>
        <li class="info_item">
          <div class="i-item">
            <a class="title">手机<i></i></a>
            <a class="i-item-text" contenteditable="false">13132700696</a>
            <i class="i-item-right" onclick="updateInfo(4)">修改</i>
          </div>
        </li>
      </ul>
    </div>
  </div>
</body>
</html>