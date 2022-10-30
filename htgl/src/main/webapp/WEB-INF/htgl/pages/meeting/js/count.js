const thead = ['签到编号', '会议编号', '姓名', '签到时间'];
let tbody = [];
onload = function(){
  queryMid();
  queryCount()
}
function render(data){
  renderTable(_("#r-b-tb"), thead, data.rows, data.count);
}
// 查询所有会议
function queryCount(queryString, flag){
  queryString || (queryString = "")
  if(!flag)
    queryPage.queryString = `(sname like '%${queryString}%')`;
  $.ajax({
    url: preUrl + "/meeting/querySign",
    method: 'post',
    dataType: 'json',
    headers: {
      'Content-Type': 'application/json'
    },
    data: JSON.stringify(queryPage),
    success(res){
      render(res)
    }
  })
}
// 查询所有会议ID
function queryMid(){
  $.ajax({
    url: preUrl + "/meeting",
    method: 'get',
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
  queryPage.queryString += `and ms_mid=${value}`;
  queryCount("", true);
}
// 导出
function export_excel(){
  $.ajax({
    url: preUrl + "/meeting/export",
    method: 'post',
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