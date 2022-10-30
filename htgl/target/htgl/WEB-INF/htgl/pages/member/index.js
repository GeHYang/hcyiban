
let members = [
  {
    sid: 2019120071,
    sname: '欧阳林',
    dname: '技术研发部',
    tname: '部长',
    cname: '大数据与计算机学院',
    qq: '3235997717',
    phone: '13132700696'
  },{
    sid: 20000,
    sname: '张三',
    dname: '技术研发部',
    tname: '干事',
    cname: '大数据与计算机学院',
    qq: '3235997717',
    phone: '13132700696'
  }
];
onload = function(){
  queryMembers()
}
// 渲染
function render(data){
  // tbody
  let tb = document.getElementById('r-b-tb').getElementsByTagName('tbody')[0];
  // 总数
  let maxCount = document.getElementById('count');
  members = data.rows;// 赋值数据
  let doc = ``;
  for(let i = 0; i < members.length; i++){
    const obj = members[i];
    doc += `<tr>
              <td>${obj.sid}</td>
              <td>${obj.sname}</td>
              <td>${obj.dname}</td>
              <td>${obj.tname}</td>
              <td>${obj.cname ? obj.cname : ""}</td>
              <td>${obj.qq ? obj.qq : ""}</td>
              <td>${obj.phone ? obj.phone : ""}</td>
              <td>
                <button class="delBtn" onclick='delMember(${obj.uid})'>删除</button>
              </td>
            </tr>`;
  }
  if(members.length <= 0){
    doc += `<tr>
              <td colspan="8">暂无数据</td>
            </tr>`;
  }
  tb.innerHTML = doc;
  maxCount.innerText = `共${data.count}条`;
  
}
// 显示添加成员
function showAddMember(popupName){
  document.getElementsByClassName('popup')[0].style = 'height: 100vh;'
  document.getElementsByClassName(popupName)[0].style = 'visibility: visible;max-height: 500px;'
}
// 关闭添加成员
function closeMember(popupName){
  document.getElementsByClassName(popupName)[0].style = 'max-height: 0;visibility: hidden;'
  document.getElementsByClassName('popup')[0].style = 'height: 0;'
  document.getElementById('filename').innerText = '';
  success_text.innerText = "";
  exists_text.innerText = "";
  _("form")[0].reset();
  _("form")[1].reset();
}
// 添加成员
function confirmBtn(){
  // 请求数据
  let data = {
    sid: _("#sid").value,
    sname: _("#sname").value,
    did: _("#dname").value,
    tid: _("#tname").value,
    cname: _("#cname").value,
    qq: _("#qq").value,
    phone: _("#phone").value
  }
  if(!data.sid || !data.sname || !data.did || !data.tid){
    alert("请填写完整信息");
    return;
  }
  $.ajax({
    url: preUrl + "/users/add",
    method: 'post',
    headers: {
      "Content-Type": 'application/json',
    },
    dataType: 'json',
    data: JSON.stringify(data),
    success(res){
      alert(res.msg);
      if(res.code == 200){
        _("#add-member").reset();
        closeMember('add-member');
        queryMembers();
      }
    }
  })

}
// 显示文件名
function showFileName(el){
  // 获取文件名标签
  var filenameTag = document.getElementById("filename");
  // 获取文件名
  var filename = el.files[0].name
  filenameTag.innerText = filename;
}
// 删除
function delMember(index){
  confirm('确定修改?') && $.ajax({
    url: preUrl + "/users/" + index,
    method: "delete",
    dataType: 'json',
    success(res){
      alert(res.msg);
      res.code == 200 && queryMembers();
    }
  });
}
// 查询成员
function queryMembers(queryString){
  if(queryString){
    queryPage.queryString = `(sid like '%${queryString}%' or sname like '%${queryString}%')`;
  }
  $.ajax({
    url: preUrl + "/users",
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    data: JSON.stringify(queryPage),
    dataType: 'json',// 返回格式json
    success(res){
      render(res);
    }
  })
}

// 批量导入
function import_excel(){
  let formData = new FormData(_("#import-member-excel"));
  $.ajax({
    url: preUrl + "/users/addMembersForExcel",
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
        let success_text = _("#success_text");// 导入成功成员学号
        let exists_text = _("#exists_text");// 导入失败成员学号
        success_text.innerText = "导入成功学号：" + res.data.success;
        exists_text.innerText = "已存在学号：" + res.data.exists;
      }
    }
  })
}
// 下载
function downloadExcel(){
  window.open(preUrl + "/static/muban.xlsx")
}