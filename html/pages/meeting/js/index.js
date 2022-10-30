const thead = ['编号', '发布者学号', '发布者姓名', '标题', '会议内容', '开始时间', '结束时间', '二维码', '操作'];
let tbody = []
onload = function(){
  // 添加弹窗
  _(".popup")[0].innerHTML = popup_doc;
  queryMeeting()
}
// 渲染
function render(data){
  renderTable(_('#r-b-tb'), thead, data.rows, data.count);
  createPager(data.count % 5 == 0 ? data.count / 5 : data.count / 5 + 1);
}
// 删除会议
function delMeeting(id){
  confirm("删除会议?") && $.ajax({
    url: preUrl + "/meeting/" + id,
    method: 'delete',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    dataType: 'json',
    success(res){
      alert(res.msg);
      res.code == 200 && queryMeeting();
    }
  })
}
function getPages(){
  queryMeeting();
}
// 显示添加成员
function showAddMeeting(popupName){
  document.getElementById("send-sid").value = user_info.sid;
  document.getElementById("send-sname").value = user_info.sname;
  document.getElementsByClassName('addMeeting-box')[0].style = 'height: 100vh;'
  document.getElementsByClassName(popupName)[0].style = 'visibility: visible;max-height: 500px;'
}
// 关闭添加成员
function closeMeeting(popupName){
  document.getElementsByClassName(popupName)[0].style = 'max-height: 0;visibility: hidden;'
  document.getElementsByClassName('addMeeting-box')[0].style = 'height: 0;'
}
// 查询会议
function queryMeeting(queryString, flag){
  if(!flag && queryString){
    queryPage.queryString = `(users.sid like '%${queryString}%' or sname like '%${queryString}%')`
  }
  queryPage.pageSize = 5;
  $.ajax({
    url: preUrl + "/meeting/query",
    method: 'post',
    dataType: 'json',
    headers: {
      "Content-Type" : 'application/json',
      "Authorization": localStorage.getItem("token")
    },
    processData: false,
    data: JSON.stringify(queryPage),
    success(res){
      for(let i = 0; i < res.rows.length; i++){
        // <button class="delBtn" onclick='delMeeting(1)'>删除</button>
        res.rows[i].cz = `<button class="delBtn" onclick='delMeeting(${res.rows[i].mid})'>删除</button>`
      }
      render(res)
    }
  })
}
// 添加
function confirmAdd(){
  let data = {
    uid: user_info.uid,
    mtitle: _("#mtitle").value,
    mcontent: _("#meeting-content").value,
    start_time: _("#start_time").value.replace("T", " ") + ":00",
    end_time: _("#end_time").value.replace("T", " ") + ":00"
  }
  if(!data.mtitle || !data.mcontent || !data.start_time || !data.end_time){
    alert("请填写完整参数");
    return;
  }
  $.ajax({
    url: preUrl + "/meeting/add",
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
      "Authorization": localStorage.getItem("token")
    },
    dataType: 'json',
    //processData: false,
    data: JSON.stringify(data),
    success(res){
      alert(res.msg);
      res.code == 200 && queryMeeting();
      closeMeeting("add-meeting");
      _("#add-meeting-form").reset();
    }
  })
}
