const thead = [
  '编号',
  '学号',
  '姓名',
  '部门',
  '职称',
  '发起时间',
  '时间段',
  '操作'
];
let tbody = [];

onload = function(){
  queryRepair();
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
      td += `<td>${val}</td>`;
    }
    // 添加操作
    td += `<td>
      <button class="editBtn" onclick='agree(${tbody[i].did})'>同意</button>
      <button class="delBtn" onclick='oppose(${i})'>反对</button>
    </td></tr>`;
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
  queryRepair();
}
// 同意
function agree(index){
  confirm("同意补签?") && $.ajax({
    url: preUrl + "/duty/agreeRepair",
    method: 'get',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    dataType: 'json',
    data: {
      did: index
    },
    success(res){
      alert(res.msg);
      res.code == 200 && queryRepair();
    }
  })
}
// 反对
function oppose(index){
  
}

// 查询值班补签
function queryRepair(queryString){
  queryString || (queryString = "")
  queryPage.queryString = `(sid like '%${queryString}%' or sname like '%${queryString}%')`;
  $.ajax({
    url: preUrl + "/duty/queryDutyRepair",
    method: 'post',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    data: queryPage,
    //processData: false,
    dataType: 'json',
    success(res){
      render(res)
    }
  })
}
