
onload = function(){
  initTable()
}
// 初始化表格
function initTable() {
  let tb = document.querySelector(".tb-content");
  let trs = tb.querySelectorAll("tr");
  let c = 0;
  let color = ["c0", "c1"];// 单元格样式
  for (let tr of trs) {
    let tds = tr.querySelectorAll("td");
    for (let i = 1; i < tds.length; i++) {
      if ((i - 11) % 10 == 0) {
        c = ++c % 2;
        tds[i].style.marginLeft = "5px";
      }
      tds[i].className = color[c]
    }
    tds[0].style.backgroundColor = "blue";
    tds[0].style.color = "white";
    tds[0].className = "";
  }
}
// 修改值班表
function editTable(){
  $(".tb-content td").addClass("bor")
  $(".editBtn").eq(0).text("完成")
  $(".editBtn").eq(0).attr("onclick", "complete()")
  labelMove()
}
// 完成
function complete(){
  $(".tb-content").unbind()
  $(".tb-content td").removeClass("bor")
  $(".editBtn").eq(0).text("修改")
  $(".editBtn").eq(0).attr("onclick", "editTable()")
}

// 点击移动
function labelMove(){
  let tb = $(".tb-content");
  let tds = [...$(".tb-content td")]
  tb.mousedown(function(e){
    // 当鼠标按下，获取鼠标位置
    var mouseOldX = e.pageX;
    var mouseOldY = e.pageY;
    // 获取元素的初始位置
    var old = {...$(e.target).offset()}
    // 为点击标签添加移动事件
    if(e.target.className.indexOf("c0") != -1 || e.target.className.indexOf("c1") != -1)
      e.target.style = "z-index: 100;"
    $(e.target).on("mousemove",function(e){
        // 当鼠标运动时获取鼠标的位置
        var mouseNewX = e.pageX;
        var mouseNewY = e.pageY;
        // 计算鼠标移动的距离
        var moveX = mouseNewX - mouseOldX;//x移动的距离
        var moveY = mouseNewY - mouseOldY;//y移动的距离
        //将新的鼠标位置保存
        mouseOldX = mouseNewX;
        mouseOldY = mouseNewY;
        //将标签位置按照鼠标移动的距离进行移动
        var divOldXY = $(this).offset();
        $(this).offset({
          left:divOldXY.left + moveX,
          top:divOldXY.top + moveY
        })
    });
    //为鼠标添加抬起事件
    $(e.target).on("mouseup mouseout",function(e1){
      // 清除当前标签的鼠标移动事件
      if(e.target.className.indexOf("c0") != -1 || e.target.className.indexOf("c1") != -1)
        e.target.style = ""
      $(this).unbind();
      let new_el = $(document.elementFromPoint(e1.pageX, e1.pageY));

      // 判断是否为可交换
      if(new_el.attr("class") && (new_el.attr("class").indexOf("c0") != -1 || new_el.attr("class").indexOf("c1") != -1)){
        let td = new_el[0].innerText;
        let index = tds.indexOf(new_el[0]);// 获取需要调换到的下标
        let oldIndex = tds.indexOf(e.target);// 需要调换的下标
        // 交换内容
        tds[index].innerText = tds[oldIndex].innerText;
        tds[oldIndex].innerText = td;
        // 修改背景，方便认出
        tds[index].style.backgroundColor = "green";
        tds[oldIndex].style.backgroundColor = "green";
        tds[index].style.color = "white";
        tds[oldIndex].style.color = "white";
      } else {
        // 恢复原来位置
        $(this).offset({
          left: old.left,
          top: old.top
        })
      }
    });
  })

}

function createTable(){
  if(confirm("每次生成的值班表都会是初始状态，确定生成？")){ 
    E.loading("加载中", 100000);
    $.ajax({
      url: preUrl + "/duty/exportTable",
      dataType: 'json',
      success(res){
        E.close();
        if(res.code == 200){
          window.open(res.msg);
        }
      },
      error(){
        E.close();
        alert("出错");
      }
    })
  }
}
