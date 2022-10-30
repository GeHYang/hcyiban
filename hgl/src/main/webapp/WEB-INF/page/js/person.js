// 基本信息
let info = {
  yb_uid: '33331244',
  sid: '2019120071',
  sname: '欧阳林13',
  dname: '技术研发部',
  tname: '部长',
  cname: '大数据与计算机学院',
  QQ: '3235997717',
  phone: '13132700696'
}
const department = {
  1001:	'主席团',
  1002:	'行政办公室',
  1003:	'新闻采编部',
  1004:	'视觉传达部',
  1005:	'运营推广部',
  1006:	'优课开发部',
  1007:	'技术研发部',
  1008:	'新媒体部',
  1009:	'思政编辑部',
  1010:	'无'
}
const title = {
  1001:	'主席',
  1002:	'副主席',
  1003:	'秘书长',
  1004:	'副秘书长',
  1005:	'部长',
  1006:	'副部长',
  1007:	'干事'
}

onload = function (){
  let inf = JSON.parse(sessionStorage.getItem("hgl_info"));
  let {ybUid, sid, sname, did, tid, cname, qq, phone} = inf
  info = {ybUid, sid, sname, dname: department[did], tname: title[tid], cname, qq, phone}// 初始化个人信息
  // 获取头像
  let yb_userhead = JSON.parse(sessionStorage.getItem("yb_info")).yb_userhead;
  // 显示头像
  document.querySelector("#headIcon").src = yb_userhead;
  render();
}
// 修改信息
function updateInfo(index) {
  let items = document.getElementsByClassName("i-item-text");// 所有个人信息栏
  let edit = document.getElementsByClassName('i-item-right'); // 所有标签
  for (var i = 0; i < items.length; i++) {
    if(i >= 3 && i < 5) continue;
    items[i].setAttribute('contenteditable', false);// 关闭所有编辑功能
  }
  if(index > 1){
    // 开启某项
    items[index + 3].setAttribute('contenteditable', true);
    items[index + 3].focus();
  } else {
    // 获取列表选项
    let list = document.getElementsByClassName("selectBox")[index];
    list.style.display = '';// 开启
    // 获取子选项
    let list_child = list.children;
    if(index == 0){
      // 部门
      for(const key in department){
        if(items[index + 3].innerText == department[key]){
          list_child[(key - 1000) - 1].setAttribute('selected', '');
        }
      }
    } else{
      // 职称
      for(const key in title){
        if(items[index + 3].innerText == title[key]){
          list_child[(key - 1000) - 1].setAttribute('selected', '');
        }
      }
    }
  }

  // 修改标签文字
  edit[index].innerText = '完成';
  // 修改点击事件
  edit[index].setAttribute('onclick', 'editOK(' + index + ')');
}
// 完成修改
function editOK(index) {
  let items = document.getElementsByClassName("i-item-text");// 所有个人信息栏
  let edit = document.getElementsByClassName('i-item-right'); // 所有标签
  let i = 0;
  let is_list_value;
  // 获取列表内容
  if(index <= 1){
    // 获取列表选项
    let list = document.getElementsByClassName("selectBox")[index];
    // 获取子选项
    if(index == 0){
      items[index + 3].innerText = department[list.value];
      is_list_value = list.value;
    } else {
      items[index + 3].innerText = title[list.value];// 重新赋值
      is_list_value = list.value;
    }
    // 关闭下拉选项
    list.style.display = 'none';
  }

  for (const key in info) {
    if (i == index + 3) {
      // 判断内容是否有更改
      if (info[key] == items[i].innerText) {// 无更改，直接跳出循环
        break;
      }
      let yes = confirm("确定修改?");// 有更改，请求确认
      if (yes) {
        info[key] = items[i].innerText;// 修改个人信息
        submitEdit(key, is_list_value)
      }
      break;
    }
    i++;
  }
  render(); // 渲染

  // 禁用编辑
  items[index + 3].setAttribute('contenteditable', false)
  // 修改标签文字
  edit[index].innerText = '修改';
  // 修改点击事件
  edit[index].setAttribute('onclick', 'updateInfo(' + index + ')');
}
// 渲染个人信息
function render() {
  let items = document.getElementsByClassName("i-item-text");// 所有个人信息栏
  for (let i = 0; i < items.length; i++) {
    items[i].innerText = info[Object.keys(info)[i]]
  }
  // 渲染顶部姓名
  document.getElementById('v-top-sname').innerText = info.sname;
}
// 提交修改
function submitEdit(columnName, list_value){
  let columnValue;
  if(columnName == 'dname'){
    columnName = 'did';
    columnValue = list_value
  } else if(columnName == 'tname'){
    columnName = 'tid'
    columnValue = list_value
  } else {
    columnValue = info[columnName];
  }
  $.ajax({
    url: prefix + "/user/editUser",
    method: 'post',
    async: false,
    headers: {
      'Authorization': localStorage.getItem("token")
    },
    data: {
      sid: info['sid'],
      sname: info['sname'],
      yb_uid: info['ybUid'],
      columnName: columnName,
      columnValue: columnValue
    },
    dataType: 'json',
    success(res){
      if(res.code == 200){
        E.showModule({
          content: res.msg
        })
      } else {
        E.showModule({
          content: res.msg,
          success() {
            navToURL("/view/person", "person")
          }
        })
      }
    },
    error(){
      E.showModule({
        content: '出错啦'
      })
    }
  })
}