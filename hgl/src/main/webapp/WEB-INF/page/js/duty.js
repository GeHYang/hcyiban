let user_info;
onload = function (){
    if(!localStorage.getItem("token")){
        alert("没有权限");
        window.location.href = '/view/index'// 返回首页
        return;
    }
    duty.initCalendar();
}

let duty = {
    info: JSON.parse(sessionStorage.getItem("hgl_info")),
    /**
     * 初始化日历
     */
    initCalendar(){
        let now_time = dateSplit();
        // 1、获取月初时间
        let firstDate = getFirstDate();
        // 2、获取月末日期
        let lastDate = getLastDate(firstDate);
        // 3、获取当前月第一天星期
        let weekday = (firstDate.getDay() + 6) % 7;
        // 4、创建tbody
        let tbody = document.createElement("tbody");
        let doc = `<tr>`;
        // 5、填充前面
        for(let i = 0; i < weekday; i++){
            doc += `<td></td>`
        }
        // 6、添加日期
        for(let i = weekday; i <= lastDate.getDate() + weekday - 1; i++){
            if(i % 7 == 0){ // 满一周
                doc += `</tr><tr>`;
            }
            // 当天背景绿色
            if(Number(now_time.day) == (i - weekday + 1))
                doc += `<td class="bc">${i - weekday + 1}</td>`
            else if((i - weekday + 1) < Number(now_time.day)){
                doc += `<td class="light">${i - weekday + 1}</td>`
            } else {
                doc += `<td>${i - weekday + 1}</td>`
            }
        }
        doc += `</tr>`;
        tbody.innerHTML = doc;
        document.querySelector('#calendar').appendChild(tbody);
        document.querySelector("#year-month").innerText = `${now_time.year}年${now_time.month}月`;
        document.querySelector("#r-month").innerText = `${now_time.year}-${now_time.month}`;
        document.querySelector("#day_day").innerText = `${now_time.day}`;

        duty.initNowMonthDuty()
        duty.initNowDayDuty()
    },
    /**
     * 初始化当天值班数据
     */
    initNowDayDuty(){
        const _this = this;
        $.ajax({
            url: prefix + "/duty",
            method: 'get',
            headers: {
                'Authorization': localStorage.getItem("token")
            },
            data: {
                uid: _this.info.uid,
                month: `${new Date().getFullYear()}-${zeroPadding(new Date().getMonth() + 1)}-${zeroPadding(new Date().getDate())}`,
                flag: 2
            },
            success(res){
                rander(res.data)
            },
            error(){
                E.showModule({content: '出错'})
            }
        })
        // 渲染当月签到数据
        function rander(list){
            // 1、获取表格
            let tb = document.querySelectorAll(".t-table")[0];// 第二个
            // 2、创建tbody
            let tbody = document.createElement("tbody");
            // 3、存储内容
            let doc = ``;
            // 4、渲染数据
            for(let i = 0; i < list.length; i++){
                doc += `<tr>
                            <td>${list[i].signTime}</td>
                            <td>${list[i].state == 1 ? '签到' : (list[i].state == 2 ? '补签中' : '请假')}</td>
                        </tr>`;
            }
            tbody.innerHTML = doc;
            tb.appendChild(tbody);
        }
    },
    /**
     * 初始化当月值班数据
     */
    initNowMonthDuty(){
        let _this = this
        $.ajax({
            url: prefix + "/duty",
            method: 'get',
            headers: {
                'Authorization': localStorage.getItem("token")
            },
            data: {
                uid: _this.info.uid,
                month: `${new Date().getFullYear()}-${zeroPadding(new Date().getMonth() + 1)}`,
                flag: 1 // 标志，1当月，2当天
            },
            success(res){
                rander(res.data)
            },
            error(){
                E.showModule({content: '出错'})
            }
        })
        // 渲染当月签到数据
        function rander(list){
            // 1、获取表格
            let tb = document.querySelectorAll(".t-table")[1];// 第二个
            // 2、创建tbody
            let tbody = document.createElement("tbody");
            // 3、存储内容
            let doc = ``;
            // 4、渲染数据
            for(let i = 0; i < list.length; i++){
                doc += `<tr>
                            <td>${list[i].signTime}</td>
                            <td>${list[i].state == 1 ? '签到' : (list[i].state == 2 ? '补签中' : '请假')}</td>
                            <td>${list[i].count}</td>
                        </tr>`;
            }
            tbody.innerHTML = doc;
            tb.appendChild(tbody);
        }
    },
    /**
     * 签到
     */
    sign(){
        E.loading("加载中");
        let _this = this;
        let url = duty_sign_url + "/duty";
        if(_this.info.uid === 2){
            url = prefix + "/duty";
        }
        $.ajax({
            url: url,
            method: 'post',
            data: {uid: _this.info.uid},
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': localStorage.getItem("token")
            },
            async: false,
            success(res){
                E.close()
                if(typeof res == 'string') res = JSON.parse(res)
                if(res.code == 200){
                    _this.signSuccess();
                } else
                    E.showModule({
                        content: res.msg
                    })
            },
            error(){
                E.close()
                E.showModule({
                    content: '出错，请检查是否连接易班WIFI'
                })
            }
        })
    },
    /**
     * 签到成功
     */
    signSuccess(){
        let user_info = sessionStorage.getItem("hgl_info");
        user_info = JSON.parse(user_info)
        let box = E('.s-content')[0];
        let doc = `<div>学号:<span id="sid">${user_info.sid}</span></div>
                    <div>姓名:<span id="sname">${user_info.sname}</span></div>
                    <div>签到时间:<span id="time">${dateFormat(new Date()).substring(5)}</span></div>
                    <button id="s-ok" onclick="E('.sign-box')[0].style.display = 'none';window.location.reload();">确定</button>`;
        box.innerHTML = doc;
        E('.sign-box')[0].style.display = '';
    },
    /**
     * 补签
     */
    repair(){
        let datetime = document.querySelector("#repair_datetime");
        datetime = datetime.value.replace("T", " ");
        let _this = this
        $.ajax({
            url: prefix + '/duty/repair',
            method: 'post',
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': localStorage.getItem("token")
            },
            data:{
                uid: _this.info.uid,
                repair_datetime:datetime + ':00'
            },
            success (res) {
                if (typeof res == 'string') res = JSON.parse(res)
                E.showModule({
                    content: res.msg,
                    success(){
                        window.location.reload();
                    }
                })
            },
            error(){
                E.showModule({
                    content: "出错啦"
                })
            }
        })
    }
}

// 时间切割
function dateSplit(time){
    time || (time = new Date())
    let now_time = {
        year: time.getFullYear(),// 年
        month: (time.getMonth() + 1) > 9 ? (time.getMonth() + 1) : ('0' + (time.getMonth() + 1)),// 月
        day: (time.getDate()) > 9 ? (time.getDate()) : ('0' + (time.getDate())),// 日
        weekday: time.getDay(),// 星期
        hour: (time.getHours()) > 9 ? (time.getHours()) : ('0' + (time.getHours())),// 小时
        minute: (time.getMinutes()) > 9 ? (time.getMinutes()) : ('0' + (time.getMinutes()))// 分
    }
    return now_time;
}
// 时间转化
function dateFormat(time){
    let now_time = {
        year: time.getFullYear(),// 年
        month: (time.getMonth() + 1) > 9 ? (time.getMonth() + 1) : ('0' + (time.getMonth() + 1)),// 月
        day: (time.getDate()) > 9 ? (time.getDate()) : ('0' + (time.getDate())),// 日
        weekday: time.getDay(),// 星期
        hour: (time.getHours()) > 9 ? (time.getHours()) : ('0' + (time.getHours())),// 小时
        minute: (time.getMinutes()) > 9 ? (time.getMinutes()) : ('0' + (time.getMinutes()))// 分
    }
    return `${now_time.year}-${now_time.month}-${now_time.day} ${now_time.hour}:${now_time.minute}:00`;
}
// 补0
function zeroPadding(num){
    if(typeof num != "number") return num;
    return num > 9 ? num : ('0' + num);
}
// 获取月初日期
function getFirstDate(){
    let start_t = new Date()
    //2022,05,01
    start_t = `${start_t.getFullYear()}-${zeroPadding(start_t.getMonth()+1)}-01`
    start_t = new Date(start_t);
    return start_t;
}
// 获取月末日期
function getLastDate(date){
    date || (date = new Date())
    var y = date.getFullYear(); //获取年份
    var m = date.getMonth() + 1; //获取月份
    var d = new Date(y, m, 0); //获取当月最后一日
    return d;
}