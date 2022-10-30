// 模拟数据
let account = {
  aid: 1001,
  name: '张三',
  did: 1007,
  tid: 1005,
  qq: '3235997717',
  phone: '13132700696',
  cname: '大数据与计算机学院'
}
// 部门
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
// 职称
const title = {
  1001:	'主席',
  1002:	'副主席',
  1003:	'秘书长',
  1004:	'副秘书长',
  1005:	'部长',
  1006:	'副部长',
  1007:	'干事'
}
// 院系
const cname = [
  '文学与传媒学院',
  '公共管理学院',
  '马克思主义学院',
  '数理学院',
  '人工智能与制造学院',
  '化学与生物工程学院',
  '体育学院',
  '音乐舞蹈学院',
  '美术与设计学院',
  '商学院',
  '大数据与计算机学院',
  '外国语学院',
  '教师教育学院'
]

onload = function(){
  // 初始化个人信息
  renderInfo()
}
// 渲染个人信息
function renderInfo(){
  let form = _("#form");
  // 获取所有input与select
  let inputs = form.getElementsByTagName("input");
  let selects = form.getElementsByTagName("select");
  // 补全input
  for(var input of inputs){
    input.value = user_info[input.name] ? user_info[input.name] : ""
    input.setAttribute('disabled', '')
  }
  // 补全select
  for(var select of selects){
    if(select.name == 'did'){
      for(const key in department){
        if(user_info.dname == department[key]){
          select.value = key;
          break;
        }
      }
    } else if(select.name == 'tid'){
      for(const key in title){
        if(user_info.tname == title[key]){
          select.value = key;
          break;
        }
      }
    } else {
      select.value = user_info[select.name] ? user_info[select.name] : ""
    }
    select.setAttribute('disabled', '')
  }
}
// 修改基本信息
function resetInfo(){
  alert("暂未开启");return;
  let form = _("#form");
  // 获取所有input与select
  let inputs = form.getElementsByTagName("input");
  let selects = form.getElementsByTagName("select");
  let count = 0
  // 允许修改
  for(var input of inputs){
    if(count == 0){
      count++;
      continue;
    }
    input.removeAttribute('disabled')
  }
  for(var select of selects){
    select.removeAttribute('disabled')
  }
  // 显示确定修改按钮
  _(".confirmBtn")[0].style.display = '';
  _(".cancelBtn")[0].style.display = '';
  // 隐藏修改基本信息按钮
  _(".resetInfo")[0].style.display = 'none';
}
// 取消修改
function cancelEdit(){
  let form = _("#form");
  // 获取所有input与select
  let inputs = form.getElementsByTagName("input");
  let selects = form.getElementsByTagName("select");
  // 显示确定修改按钮
  _(".confirmBtn")[0].style.display = 'none';
  _(".cancelBtn")[0].style.display = 'none';
  // 隐藏修改基本信息按钮
  _(".resetInfo")[0].style.display = '';
  renderInfo();
}
// 关闭修改密码
function closeAuth(name){
  document.querySelector("#" + name).style.display = 'none';
}
// 显示修改密码
function showAuth(name){
  _("#aid").value = user_info.aid;
  document.querySelector("#" + name).style.display = '';
  initId();
}
// 提交修改密码
function confirmAuth(){
  let data = {
    one_password: _("#pwd1").value.trim(),
    two_password: _("#pwd2").value.trim()
  }
  if(!data.one_password || !data.two_password){
    alert("请输入新密码");
    return;
  }
  if(data.one_password != data.two_password){
    alert("两次密码不一致");
    return;
  }
  // 对两次密码进行md5加密
  data.one_password = MD5(data.one_password);
  data.one_password = MD5(data.one_password);
  $.ajax({
    url: preUrl + "/user/resetPwd",
    method: 'post',
    dataType: 'json',
    headers: {
      "Authorization": token
    },
    data: data,
    success(res){
      alert(res.msg);
      res.code == 200 && closeAuth('editPassword');
    }
  })
}
// 提交修改头像
function confirmHead(){
  let formData = new FormData(_("#resetHeadIcon"));
  $.ajax({
    url: preUrl + "/user/resetHeadIcon",
    method: 'post',
    data: formData,
    dataType: 'json',
    headers: {
      "Authorization": token
    },
    processData: false,   //  告诉jquery不要处理发送的数据
		contentType: false,   // 告诉jquery不要设置content-Type请求头
    success(res){
      if(res.code != 200)
        alert(res.msg);
      else {
        alert("修改成功");
        user_info.headIcon = res.msg;
        closeAuth('editHeadIcon');
        window.location.reload();
      }
    }
  })
}
// 初始化ID
function initId (){
  
}
// 显示图片名
function showFileName(e){
  _("#fileName").value = e.files[0].name;
}