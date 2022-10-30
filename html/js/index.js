let inter = [];
let init_state = false;
onload = function(){
  getAllInter();
  render();
}
// 渲染接口
function render(){
  let interf = document.getElementsByClassName("interface")[0];
  for(let i = 0; i < inter.length; i++){
    const val = inter[i];
    interf.innerHTML += `
    <section>
      <a>${val.vname}</a>
      <!-- 开关按钮 -->
      <p class="btn-on" onclick="on_off(${i},${val.vqx == 0})">
        <!-- 圆点 -->
        <span class="btn-on-circle"></span>
        <!-- 文字 -->
        <span class="btn-on-text">${val.vqx == 1 ? 'ON' : 'OFF'}</span>
      </p>
    </section>`;
    on_off(i,val.vqx == 1);
  }
  init_state = true;
}
// 接口按钮控制
function on_off(index,type){
  var btn = document.getElementsByClassName("btn-on")[index];
  var circle = btn.getElementsByClassName("btn-on-circle")[0];
  var text = btn.getElementsByClassName("btn-on-text")[0];

  if(!type){
    btn.style= "background-color: #ccc;"
    circle.style="left: 40px;background-color: #888;box-shadow: 0 0 10px #888;";
    text.style="right: 30px;color: #888;";
    text.innerText="OFF";
  } else {
    btn.style= ""
    circle.style="";
    text.style="";
    text.innerText="ON";
  }
  btn.setAttribute("onclick", "on_off(" + index + ',' + !type + ")"); // 修改状态
  
  init_state && interface_on_off(inter[index].vid, type);
}
// 接口开关
/**
 * @param {Number} id 接口id
 * @param {boolean} state 接口状态(开关)
*/
function interface_on_off(id, state) {
  E.loading("加载中", 2000);
  $.ajax({
    url: preUrl + "/user/editInter",
    method: 'post',
    async: true,
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    data: {
      vid: id,
      state: state
    },
    success(res) {
      E.close();
      E.showModule({
        content: res.msg
      })
      if (res.code != 200) {
        // 刷新
        window.location.reload();
      }
    },
    error() {
      alert("出错");
      // 刷新
      window.location.reload();
      E.close();
    }
  })
}
// 获取所有按钮
function getAllInter() {
  $.ajax({
    url: preUrl + "/user/allView",
    method: 'get',
    async: false,
    headers: {
      "Authorization": localStorage.getItem("token")
    },
    success(res) {
      if (res.code == 200) {
        inter = res.data;
      }
    }
  })
}