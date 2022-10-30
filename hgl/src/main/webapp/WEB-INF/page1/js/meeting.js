let info;
onload = function (){

    if(!localStorage.getItem("token")){
        alert("没有权限");
        window.location.href = '/view/index'// 返回首页
        return;
    }
    info = JSON.parse(sessionStorage.getItem("hgl_info"))
    meeting.initMeeting();
}

const meeting = {
    app: {},
    prefix: prefix + '/meeting',
    initMeeting(){
        const _this = this;
        // 判断app是否已创建
        if(!this.app === {}){
            return;
        }
        this.app = new Vue({
            el: '.main',
            data(){
                return{
                    meetings: []
                }
            },
            mounted(){
                this.getAllMeeting();
            },
            methods: {
                // 获取会议信息
                getAllMeeting(){
                    const that = this;
                    $.ajax({
                        url: prefix + "/meeting",
                        method: 'get',
                        headers: {
                            'Authorization': localStorage.getItem("token")
                        },
                        success(res){
                            if(typeof res == 'string') res = JSON.parse(res);
                            if(res.data){
                                that.meetings = res.data;
                            } else if(res.msg){
                                E.showModule({
                                    content: res.msg
                                })
                            } else {
                                E.showModule({
                                    content: '出错'
                                })
                            }
                        },
                        error(){
                            E.showModule({
                                content: '出错'
                            })
                        }
                    })
                },
                meetingSign(index){
                    let meeting = this.meetings[index];
                    if(meeting.state <= 0){
                        E.showModule({content: '已结束'});
                        return;
                    } else if(meeting.state >= 2){
                        E.showModule({content: '未开始'});
                        return;
                    }
                    this.scan(meeting.mid)
                },
                //扫码
                scan(mid){
                    try{
                        yiban.scan({success:function(result) {
                            $.ajax({
                                url: prefix + "/meeting/sign",
                                method: 'post',
                                async: false,
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded',
                                    'Authorization': localStorage.getItem("token")
                                },
                                data: {code: result + "", mid: mid,uid: info.uid},
                                success(res) {
                                    if(typeof res == 'string') res = JSON.parse(res);
                                    E.showModule({
                                        content: res.msg
                                    })
                                },
                                error(){
                                    E.showModule({
                                        content: "出错"
                                    })
                                }
                            })
                        }, fail:function(error) {
                            E.showModule({
                                content: error
                            })
                        }});
                    } catch (err){
                        E.showModule({
                            content: '请在易班平台签到'
                        })
                    }

                },
            },
        })
    }
}