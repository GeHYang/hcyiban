const preUrl = "http://10.110.133.16:8081/ht";
let queryPage = {
  currentPage: 0,
  pageSize: 10,
  queryString: ''
}
let user_info = {
  uid: 3
}
let token = "";
/**
 * 公共方法
 */
var popup_doc = `
<div class="showMaxImg" onclick="closeMaxImg(event)">
  <img src="" id="max-img-id">
</div>
<style>
  .popup{
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
  }
  .showMaxImg{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: rgba(192, 192, 192, .3);
  }
  .showMaxImg img{
    height: 80%;
    box-shadow: 0 0 30px #ccc;
  }
</style>
`;
// 校验token
(function(){
  // 获取个人信息
  user_info = JSON.parse(sessionStorage.getItem("info"));
  token = localStorage.getItem("token");
  $.ajax({
    url: preUrl + "/user/verify",
    method: 'post',
    dataType: 'json',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    async: true,
    success(res){
      if(res.code != 200){
        alert(res.msg);
        localStorage.clear();
        sessionStorage.clear();
        window.location.href = "/pages/login.html";
      } else {
        token = res.msg;
        // 显示头像
        _(".headIcon")[0].src = user_info.headIcon
        _(".headIcon")[1] && (_(".headIcon")[1].src = user_info.headIcon);
        //显示姓名
        _("#h-right-name").innerText = user_info.sname
      }
    }
  })
}());
// 退出
function exit(){
  localStorage.clear();
  sessionStorage.clear();
  window.location.href = "/pages/login.html";
}
// 显示大图
function showMaxImg(url){
  let popup = _(".popup")[0];
  let img = _("#max-img-id");
  popup.style.display = '';
  img.src = url;
}
// 关闭大图
function closeMaxImg(e){
  if(e.target != _("#max-img-id")){
    _(".popup")[0].style.display = 'none'
  }
}
/**
 * 渲染表格
 * @param {HTMLElement} el 表格对象
 * @param {Array} thead 表格头
 * @param {Array} tbody 表格体
 */
function renderTable(el, thead, tbody, maxCount){
  if(!el || !thead){// 校验参数是否存在
    console.error('args is not undefined');
    return;
  }
  if(!thead instanceof Array || tbody && !tbody instanceof Array){// 不是数组
    console.error('thead or tbody is not Array');
    return;
  }
  let tb = el;
  tb.innerHTML = '';
  // 表格头
  let th = `<tr>`;
  // 表格体
  let td = ``;
  // 添加表格头
  for(const val of thead){
    th += `<th>${val}</th>`
  }
  th += '</th>';
  // 添加表格体
  for(let i = 0; i < tbody.length; i++){
    td += `<tr>`;
    for(const key in tbody[i]){
      let val = tbody[i][key]
      if(key != 'lurl' && key != 'mcode' && key != 'img')
        td += `<td>${val}</td>`;
      else{
        td += `<td><img class="limg" onclick="showMaxImg('${val}')" src="${val}" /></td>`;
      }
    }
    td += `</tr>`;
  }
  if(maxCount == 0){
    td = `<tr><td colspan="${thead.length}">暂无数据</td></tr>`
  }
  // 创建tbody与thead
  let tbody1 = document.createElement('tbody');
  let thead1 = document.createElement('thead');
  // 插入内容
  tbody1.innerHTML = td;
  thead1.innerHTML = th;
  tb.appendChild(thead1);
  tb.appendChild(tbody1);
  // 总数
  let maxCount1 = document.getElementById('count');
  maxCount1.innerText = `共${maxCount}条`;
}
// 获取dom
function _(name){
  if(name.indexOf('#') == 0){
    return document.querySelector(name);
  } else {
    return document.querySelectorAll(name)
  }
}