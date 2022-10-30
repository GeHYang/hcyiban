
// 页面加载完成
onload = function (){
    if(!localStorage.getItem("token")){
        alert("没有权限");
        window.location.href = '/view/index'// 返回首页
        return;
    }
    leave.initLeaveView();
}

const leave = {
    app: {},
    dutyLeaveOption: [
        {id: 1, value: '08:00-09:25'},
        {id: 2, value: '09:50-12:00'},
        {id: 3, value: '14:40-16:05'},
        {id: 4, value: '16:30-17:55'},
        {id: 5, value: '19:40-21:05'}
    ],
    meetingLeaveOption: [
        {id: 1, value: '1'},
    ],
    // 初始化
    initLeaveView(){
        // 判断app是否已创建
        if(!this.app === {}){
            return;
        }
        const _this = this;
        // 创建vue容器
        this.app = new Vue({
            el: '#app',
            data(){
                return{
                    leave: {
                        typeName: ['值班', '会议'],
                        leaveType: 0,
                        leaveOptions: [_this.dutyLeaveOption],
                        sid: '',// 学号
                        sname: '',// 姓名
                        content: '',// 请假事由
                        date: '',// 日期
                        time: '',// 时间
                        mid: '', // 会议编号
                    },
                    s: 1
                }
            },
            watch: {
                'leave.leaveType': {
                    handler(value){
                        if(value == 2){
                            this.getAllMid()
                        }
                    },
                    immediate:true,
                    deep: true
                }
            },
            mounted(){
                let info = sessionStorage.getItem("hgl_info");
                info = JSON.parse(info);
                this.leave.sid = info.sid;
                this.leave.sname = info .sname;
            },
            methods: {
                dutyLeave(){
                    let formData = new FormData(this.$refs.form);
                    let leaveData = {
                        'sid': this.leave.sid,
                        'sname': this.leave.sname,
                        'content': this.leave.content,
                        'time': `${this.leave.date} ${this.leave.time}`
                    }
                    if(!this.leave.date || !this.leave.time) {
                        E.showModule({
                            content: "请填写时间",
                        });
                        return;
                    }
                    formData.append('info', JSON.stringify(leaveData));
                    $.ajax({
                        url: prefix + '/duty/dutyLeave',
                        method: 'post',
                        async: false,
                        headers: {
                            'Authorization': localStorage.getItem("token")
                        },
                        data: formData,
                        processData: false,   //  告诉jquery不要处理发送的数据
                        contentType: false,   // 告诉jquery不要设置content-Type请求头
                        success(res){
                            if(typeof res == 'string') res = JSON.parse(res);
                            E.showModule({
                                content: res.msg,
                                success(r) {
                                    if(res.code === 200){
                                        window.location.href = "/view/duty"
                                    }
                                }
                            })
                        },
                        error(){
                            E.showModule({
                                content: '出错啦'
                            })
                        }
                    })
                },
                meetingLeave(){
                    let formData = new FormData(this.$refs.form);
                    let leaveData = {
                        'sid': this.leave.sid,
                        'sname': this.leave.sname,
                        'content': this.leave.content,
                        'mid': this.leave.mid
                    }
                    formData.append('info', JSON.stringify(leaveData));
                    $.ajax({
                        url: prefix + '/meeting/meetingLeave',
                        method: 'post',
                        headers: {
                            'Authorization': localStorage.getItem("token")
                        },
                        async: false,
                        data: formData,
                        processData: false,   //  告诉jquery不要处理发送的数据
                        contentType: false,   // 告诉jquery不要设置content-Type请求头
                        success(res){
                            if(typeof res == 'string') res = JSON.parse(res);
                            E.showModule({
                                content: res.msg,
                                success(r) {
                                    if(res.code === 200){
                                        window.location.href = "/view/index"
                                    }
                                }
                            })
                        },
                        error(){
                            E.showModule({
                                content: '出错啦'
                            })
                        }
                    })
                },
                confirmLeave(){
                    if(this.leave.leaveType == 1){
                        this.dutyLeave()
                    } else if(this.leave.leaveType == 2){
                        this.meetingLeave();
                    }
                },
                getAllMid(){
                    const that = this;
                    // 获取所有会议编号
                    $.ajax({
                        url: prefix + "/meeting/mids",
                        method: "POST",
                        async: false,
                        headers: {
                            'Authorization': localStorage.getItem("token")
                        },
                        dataType: 'json',
                        success(res){
                            if(res.data){
                                that.leave.leaveOptions[1] = res.data
                            } else {
                                E.showModule({content: res.msg || '出错'})
                            }
                        },
                        error(){
                            E.showModule({content: '出错', success(){ window.history.go(-1);}})
                        }
                    })
                },
            }
        })
    }
}