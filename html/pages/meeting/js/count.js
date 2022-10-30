const thead = ['签到编号', '会议编号', '学号', '姓名', '签到时间'];
let tbody = [];
onload = function(){
  queryMid();
  queryCount()
}
function render(data){
  renderTable(_("#r-b-tb"), thead, data.rows, data.count);
  createPager(data.count % 10 == 0 ? data.count / 10 : data.count / 10 + 1);
}
// 查询所有会议
function queryCount(queryString, flag){
  //console.log(queryPage.queryString)
  queryString || (queryString = "")
  if(!flag)
    queryPage.queryString = `(sname like '%${queryString}%')`;
  $.ajax({
    url: preUrl + "/meeting/querySign",
    method: 'post',
    dataType: 'json',
    headers: {
      'Content-Type': 'application/json',
      "Authorization": localStorage.getItem("token")
    },
    processData: false,
    data: JSON.stringify(queryPage),
    success(res){
      render(res)
    }
  })
}
function getPages(){
  queryCount("", true);
}
// 查询所有会议ID
function queryMid(){
  $.ajax({
    url: preUrl + "/meeting",
    method: 'get',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    success(res){
      let month = _("#months");
      for(let value of res.data){
        let option = document.createElement("option");
        option.value = value;
        option.innerText = value;
        month.appendChild(option);
      }
    }
  })
}
// 会议id查询
function queryByMid(value){
  if(value)
    queryPage.queryString = `(sname like '%${document.querySelector('#queryString').value}%')` + ` and ms_mid=${value}`;
  else
    queryPage.queryString = `(sname like '%${document.querySelector('#queryString').value}%')`;
//queryPage.queryString += `and ms_mid=${value}`;
  queryCount("", true);
}
// 导出
function export_excel(){
  $.ajax({
    url: preUrl + "/meeting/export",
    method: 'post',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    dataType: 'json',
    data: queryPage,
    success(res){
      if(res.code == 200){
        window.open(res.msg)
      } else {
        alert(res.msg)
      }
    }
  })
}
