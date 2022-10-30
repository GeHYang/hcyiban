const thead = ['编号','学号', '姓名', '部门', '职称', '签到时间', '状态'];
let tbody = [];
// 初始化成功
onload = function(){
  queryDuty()
}
// 渲染表格
function render(data){
  let tb = _('#r-b-tb');
  renderTable(tb, thead, data.rows, data.count);
}
// 查询值班情况
function queryDuty(queryString){
  if(queryString){
    queryPage.queryString = `(sid like '%${queryString}%' or sname like '%${queryString}%')`;
  }
  $.ajax({
    url: preUrl + "/duty",
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    data: JSON.stringify(queryPage),
    success(res){
      render(res)
    }
  })

}