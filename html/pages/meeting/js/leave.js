const thead = ['请假编号', '会议编号', '姓名', '请假内容', '请假图片', '请假时间', '审核人', '操作'];
let tbody = []
onload = function(){
  // 添加弹窗
  _(".popup")[0].innerHTML = popup_doc;
  queryLeave();
}
  // 渲染
function render(data){
  tbody = data.rows;
  let tb = _('#r-b-tb');// 获取表格对象
  let thead1 = document.createElement('thead');
  let tbody1 = document.createElement('tbody');
  let th = `<tr>`;
  let td = ``;

  for(const val of thead){
    th += `<th>${val}</th>`
  }
  th += '</tr>';
  // 主体
  for(let i = 0; i < tbody.length; i++){
    td += `<tr>`;
    for(const key in tbody[i]){
      let val = tbody[i][key];
      if(key == 'state') continue;
      else if(key != 'ml_img')
        td += `<td>${val}</td>`;
      else{
        td += `<td><img class="limg" onclick="showMaxImg('${val}')" src="${val}" /></td>`;
      }
    }
    if(tbody[i].state != 1)
      // 添加操作
      td += `<td></td><td>
        <button class="editBtn" onclick='agree(${tbody[i].ml_id})'>同意</button>
        <button class="delBtn" onclick='oppose(${i})'>反对</button>
      </td></tr>`;
    else{
      td += `<td></td></tr>`;
    }
  }
  if(tbody.length == 0){
    td += `<tr><td colspan="${thead.length}">暂无数据</td></tr>`
  }
  tb.innerHTML = '';
  thead1.innerHTML = th;
  tbody1.innerHTML = td;
  tb.appendChild(thead1);
  tb.appendChild(tbody1);
  // 总数
  let maxCount = document.getElementById('count');
  maxCount.innerText = `共${data.count}条`;
  createPager(data.count % 10 == 0 ? data.count / 10 : data.count / 10 + 1);
}
function getPages(){
  queryLeave();
}
// 同意
function agree(index){
  confirm("同意请假?") && $.ajax({
    url: preUrl + "/meeting/agree",
    method: 'post',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    data: {
      ml_id: index,
      uid: user_info.uid
    },
    dataType: 'json',
    success(res){
      alert(res.msg);
      res.code == 200 && queryLeave();
    }
  })
}
// 反对
function oppose(index){
  
}
// 查询所有会议请假
function queryLeave(queryString){
  queryString || (queryString = "")
  queryPage.queryString = `(sname like '%${queryString}%')`;  
//queryString && (queryPage.queryString = queryString);
  $.ajax({
    url: preUrl + '/meeting/queryLeave',
    method: 'post',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    dataType: 'json',
    headers: {
      'Content-Type': 'application/json'
    },
    processData: false,
    data: JSON.stringify(queryPage),
    success(res){
      render(res)
    }
  })
}
