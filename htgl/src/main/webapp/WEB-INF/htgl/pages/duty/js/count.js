const thead = ['编号', '学号', '姓名', '总时长'];
let tbody = [];

onload = function(){
  getAllMonth();
  queryCount()
}

function render(data){
  tbody = data.rows;
  renderTable(_('#r-b-tb'), thead, tbody, data.count);
}

function queryCount(queryString, flag){
  queryString || (queryString = "")
  if(!flag)
    queryPage.queryString = `(sid like '%${queryString}%' or sname like '%${queryString}%')`;
  $.ajax({
    url: preUrl + "/duty/queryDutyCount",
    method: 'post',
    dataType: 'json',
    data: queryPage,
    success(res){
      render(res)
    }
  })
}
// 获取所有月份
function getAllMonth(){
  $.ajax({
    url: preUrl + "/duty/month",
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
// 月份查询
function queryByMonth(value){
  queryPage.queryString += `and date_format(sign_time, '%Y-%m')='${value}'`;
  queryCount("", true);
}

function export_excel(){
  $.ajax({
    url: preUrl + "/duty/export",
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