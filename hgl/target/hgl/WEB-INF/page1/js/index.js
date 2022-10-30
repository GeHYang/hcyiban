
// 界面首次加载完毕
onload = function () {
    navToURL("/view/home", "home");
    App.initApp();
}

const App = {
    app: {},
    info: {
        yb_info: {},// 易班信息
        hgl_info: {} // 惠管理用户信息
    },

    initApp(){
        // 授权
        this.verifyOauth();
    },
    // 检查是否授权
    verifyOauth(){
        E.loading("加载中", 2000);
        const _this = this;
        let yb_uid = localStorage.getItem("yb_uid");
        if(!yb_uid) _this.login();
        else $.ajax({
            url: prefix1 + '/oauth/token_info',
            method: 'post',
            async: false,
            data: {
                client_id: AppID,
                yb_uid: localStorage.getItem("yb_uid")
            },
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            async success(res){
                await E.close();
                if(typeof res == 'string') res = JSON.parse(res);
                if(res.status != 200){// 授权过期
                    E.showModule({
                        content: '授权已过期，请重新授权',
                        cancelText: '取消',
                        confirmText: '授权',
                        success(res) {
                            res && _this.login(true)
                        }
                    })
                } else {
                    // 通过token获取用户信息
                    let ok = _this.getInfoByToken();
                    if(ok){
                        return;
                    } else{
                        _this.login();
                    }
                }
            },
            error(){
                E.showModule({
                    content: '出错'
                })
            }
        })
    },
    // 授权登录
    login(flag){
        const _this = this;
        if(location.href.indexOf("verify_request") == -1 || flag)
            window.location.href = prefix1 + '/oauth/authorize?client_id=' + AppID + '&redirect_uri=' + redirect_uri;
        else{
            // 获取地址栏verify_request数据
            let verify_request = location.href;
            verify_request = verify_request.substring(verify_request.indexOf('verify_request=') + 15)
            verify_request = verify_request.substring(0,verify_request.indexOf('&'));
            // 解密
            _this.decrypt(verify_request);
        }
    },
    // 解密数据
    decrypt(verify_request){
        const _this = this;
        $.ajax({
            url: prefix + "/auth/" + verify_request,
            method: 'get',
            dataType: 'json',
            async: false,
            success(res){
                if(res.code == 1601){// 不是惠管理用户
                    // 清空token及惠管理信息
                    localStorage.clear();
                    sessionStorage.clear();
                    _this.getYBInfo(res.msg);
                } else if(res.code == 200){// 惠管理用户
                    _this.info.hgl_info = res.data;
                    _this.getYBInfo(res.data.access_token);
                    // 临时保存用户信息
                    sessionStorage.setItem("hgl_info", JSON.stringify(res.data.info))
                    // 保存access_token
                    localStorage.setItem("access_token", res.data.access_token);
                    // 保存token
                    localStorage.setItem("token", res.data.token)
                    // 保存ybid到本地
                    localStorage.setItem("yb_uid", res.data.info.ybUid);
                    // 判断是否填写完整信息
                    // _this.is_complete(res.data.info);
                } else if(res.code == 208){// 没有授权
                    _this.login(true);
                }else {
                    E.showModule({
                        content: res.msg,
                        success(){
                            // 清除缓存
                            localStorage.clear();
                            sessionStorage.clear();
                        }
                    })
                }
            },
            error(){
                E.showModule({
                    content: '错误',
                    success(){
                        // 清除缓存
                        localStorage.clear();
                        sessionStorage.clear();
                    }
                })
            }
        })
    },
    // 获取易班用户信息
    getYBInfo(access_token){
        const _this = this;
        $.ajax({
            url: prefix1 + "/user/me",
            method: 'get',
            dataType: 'json',
            data:{access_token: access_token},
            success(res){
                if(res.status == 'success'){
                    _this.info.yb_info = res.info;
                    // 保存ybid到本地
                    localStorage.setItem("yb_uid", res.info.yb_userid);
                    sessionStorage.setItem("yb_info", JSON.stringify(res.info))
                } else {
                    E.showModule({
                        content: '错误',
                        success(){
                            // 清除缓存
                            localStorage.clear();
                            sessionStorage.clear();
                        }
                    })
                }
            },
            error(){
                E.showModule({
                    content: '错误',
                    success(){
                        // 清除缓存
                        localStorage.clear();
                        sessionStorage.clear();
                    }
                })
            }
        })
    },
    getInfoByToken(){
        const _this = this;
        // 检查token是否存在
        let token = localStorage.getItem("token");
        let ok;
        if(!token){
            ok = false;
            return ok;
        }
        $.ajax({
            url: prefix + "/auth/verify",
            method: 'get',
            async: false,
            headers: {
                'Authorization': token
            },
            dataType: 'json',
            success(res){
                if(res.code != 200){// token过期或其他
                    ok = false;
                } else {
                    _this.info.hgl_info = res.data;
                    // 临时保存用户信息
                    sessionStorage.setItem("hgl_info", JSON.stringify(res.data.info))
                    // 保存用户易班id
                    localStorage.setItem("yb_uid", res.data.info.ybUid);
                    // 保存token
                    localStorage.setItem("token", res.data.token);
                    // 获取易班信息
                    _this.getYBInfo(localStorage.getItem("access_token"))

                    _this.is_complete(res.data.info)
                    ok = true;
                }
            }
        })
        return ok;
    },
    // 判断信息是否完善
    is_complete(info){
        // 判断是否填写完整信息
        for(const key in info){
            if(key != 'vqx' && key != 'state' && (!info[key] || info[key] == 'null')){
                E.showModule({
                    content: '信息未填写完整，是否前往完善个人信息',
                    cancelText: '否',
                    confirmText: '是',
                    success(r){
                        if(r){
                            navToURL("/view/person", 'person');
                        }
                    }
                })
                break;
            }
        }
    }
}