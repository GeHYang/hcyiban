const thead = ['编号', '学号', '姓名', '总时长'];
let tbody = [];

onload = function(){
  getAllMonth();
  queryCount()
}

function render(data){
  tbody = data.rows;
  renderTable(_('#r-b-tb'), thead, tbody, data.count);
  createPager(data.count % 10 == 0 ? data.count / 10 : data.count / 10 + 1);
}

function getPages(){
  queryCount();
}

function queryCount(queryString, flag){
  queryString || (queryString = "")
  if(!flag)
    queryPage.queryString = `(sid like '%${queryString}%' or sname like '%${queryString}%')`;
  $.ajax({
    url: preUrl + "/duty/queryDutyCount",
    method: 'post',
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    //processData: false,
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
    headers: {
      "Authorization": localStorage.getItem("token")
    },
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
  if(value)
    queryPage.queryString = queryPage.queryString = `(sid like '%${document.querySelector('#queryString').value}%' or sname like '%${document.querySelector('#queryString').value}%')`
	 + `and date_format(sign_time, '%Y-%m')='${value}'`;
  else
    queryPage.queryString = queryPage.queryString = `(sid like '%${document.querySelector('#queryString').value}%' or sname like '%${document.querySelector('#queryString').value}%')`;
  queryCount("", true);
}

function export_excel(){
  $.ajax({
    url: preUrl + "/duty/export",
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
