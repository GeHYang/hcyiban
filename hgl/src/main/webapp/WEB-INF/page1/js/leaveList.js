let leaveList = [];
onload = function () {
    getAllLeaveBySid();
};
function setW() {
    let linstro = document.getElementsByClassName("lintro");
    let width = window.screen.width - 170;//左90右80
    for (let i = 0; i < linstro.length; i++) {
        linstro[i].style.width = width + 'px';
    }
    E.close();
}

//获取所有请假信息
function getAllLeaveBySid(){
    let user = sessionStorage.getItem("hgl_info");
    user = JSON.parse(user);
    E.loading("加载中");
    E.ajax({
        url: prefix + '/leave/' + user.uid,
        method: 'get',
        headers: {
            'Authorization': localStorage.getItem("token")
        },
        async: false
    }).then( (res) => {
        if(typeof res == 'string')
            res = JSON.parse(res);
        leaveList = res.data;
        showDutyLeaveList(res.data.dutyLeave);
    })
}

function showDutyLeaveList(data){
    if(data == 0){
        data = leaveList.dutyLeave
    }
    E('.btn')[1].className = 'btn';
    E('.btn')[0].className = 'btn btn-on';
    let doc = ``;
    if(data.length > 0){
        for(let i = 0; i < data.length; i++){
            doc += `
    <li>
        <div class="leaveList-item">
            <!-- 请假图片 -->
            <div class="limg">
                <img src="${data[i].img}" alt="">
            </div>
            <!-- 请假摘要 -->
            <div class="lintro">
                <!-- 请假人 -->
                <a class="sname">姓名：${data[i].sname}</a>
                <!-- 请假类型 -->
                <a class="ltype">请假类型：值班请假</a>
                <!-- 请假时间 -->
                <a class="lsend_time">请假时间：${data[i].ltime}</a>
                <!-- 时间段 -->
                <a class="ltime">时间段：${data[i].time}</a>
                <!-- 请假内容 -->
                <a class="lcontent">内容：${data[i].content}</a>
            </div>
            <!-- 请假状态 -->
            <div class="lstatus">
                <a>${data[i].state === 0 ? '审核中' : '审核通过'}</a>
            </div>
        </div>
    </li>`;
        }
    } else {
        doc += `<h2 align="center">暂无数据</h2>`
    }
    let ul = E('.leaveListUl')[0];
    ul.innerHTML = doc;
    setW();
}

function showMeetingLeaveList(data){
    if(data == 1){
        data = leaveList.meetingLeave
    }
    E('.btn')[0].className = 'btn';
    E('.btn')[1].className = 'btn btn-on';
    let doc = ``;
    if(data.length > 0) {
        for (let i = 0; i < data.length; i++) {
            doc += `
    <li>
        <div class="leaveList-item">
            <!-- 请假图片 -->
            <div class="limg">
                <img src="${data[i].img}" alt="">
            </div>
            <!-- 请假摘要 -->
            <div class="lintro">
                <!-- 请假人 -->
                <a class="sname">姓名：${data[i].sname}</a>
                <!-- 请假类型 -->
                <a class="ltype">请假类型：会议请假</a>
                <!-- 请假时间 -->
                <a class="lsend_time">请假时间：${data[i].ltime}</a>
                <!-- 时间段 -->
                <a class="ltime">会议ID：${data[i].mid}</a>
                <!-- 请假内容 -->
                <a class="lcontent">请假内容：${data[i].content}</a>
            </div>
            <!-- 请假状态 -->
            <div class="lstatus">
                <a>${data[i].state === 0 ? '审核中' : '审核通过'}</a>
            </div>
        </div>
    </li>`;
        }
    }else {
        doc += `<h2 align="center">暂无数据</h2>`
    }
    let ul = E('.leaveListUl')[0];
    ul.innerHTML = doc;
    setW();
}
