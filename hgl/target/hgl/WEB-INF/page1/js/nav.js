/**
 * 界面跳转js
*/
//底部导航栏
let pages = [
    {name:'home',title:'首页'},
    {name:'questions',title:'题库'},
    {name:'person',title:'个人中心'}
];
// 主页面跳转
function navToURL(url, id)
{
    if(!localStorage.getItem("token") && id != 'home'){
        alert("没有权限");
        return;
    }
    let iframe = document.querySelector("#iframe");
    iframe.src = url;
    setHeader(id);
}
function setHeader(id){
    for(let i = 0; i < pages.length; i++)
    {
        if(pages[i].name === id){
            document.querySelector("#" + id).style.color = "#50b9e7";
            continue;
        }
        document.querySelector("#" + pages[i].name).style.color = "#928f8f";
    }
}
function setHeader1(id){
    for(let i = 0; i < pages.length; i++)
    {
        if(pages[i].name === id){
            window.parent.document.querySelector("#" + id).style.color = "#50b9e7";
            continue;
        }
        window.parent.document.querySelector("#" + pages[i].name).style.color = "#928f8f";
    }
}
//iframe页面控制父页面跳转
function navToURL1(url, id)
{
    if(!localStorage.getItem("token")){
        alert("没有权限");
        return;
    }
    let iframe = window.parent.document.querySelector("#iframe");
    iframe.src = url;
    setHeader1(id);
}
//跳转非导航栏界面
function navToURL_out(url)
{
    if(!localStorage.getItem("token") && url.indexOf("/view/index") == -1){
        alert("没有权限");
        return;
    }
    if(window.parent.document) {
        window.parent.window.location.href = url;
    } else {
        window.location.href = url;
    }
}