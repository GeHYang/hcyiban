## 引入E.js

````html

<script src="E.js"></script>
````
## 操作Dom节点
````html
  <div class="box1"></div>
  <div id="box2"></div>
  <script>
    //获取节点  E('节点名'),返回值为一个伪数组
    E('div');//通过标签名获取
    E(".box1");//通过类名获取
    E("#box2");//通过元素id获取
    //获取节点下第index个节点
    E('div').eq(index);//返回值为伪数组
    //修改样式
    //E("div").css("属性", "值");//第一种方式,字符串形式
    E("div").css("color", "red");
    //E("div").css({"属性":"值","属性":"值"});//第二种方式，对象方式
    E("div").css({"color":"green","background-color":"black"});
    //E("div").css(属性);//获取指定样式属性
    E('div').css("color");
    //添加节点
    //element是一个节点对象
    E('div').add(element);

  </script>
````
## Ajax请求
````html
  <script>
    //1、初始方式请求，url参数为必须，其他可选，更多参数查看源代码
    E.ajax({
      url: "http://localhost:8090/testServlet",//请求地址
      method: "post",//请求方式，默认get
      async: false,//是否异步请求，默认为true
      data: { id: 1, name: 'zhangsan' },//需要传输的数据，可以是各种类型
      responseType: 'json',//服务器返回的数据类型，默认string
      timeout: 2000//超时时间
    }).then((result) => {//成功回调
      console.log(result);
    }).catch((err) => {//错误回调
      console.error(err);
    })

    //2、发送get请求  get(url, data, async, timeout) url必须
    E.get("http://localhost:8090/testServlet", { id: 1, name: 'zhangsan' }, false, 2000)
    .then((result) => {//成功回调
      console.log(result);
    }).catch((reason) => {//失败回调
      console.error(reason);
    });
    
    //3、发送post请求  post(url, data, async, timeout) url必须
    E.post("http://localhost:8090/testServlet", { id: 1, name: 'zhangsan' }, false, 1000)
    .then((result) => {//成功回调
      console.log(result);
    }).catch((reason) => {//失败回调
      console.error(reason);
    });
  </script>

````

## 弹窗
#### 加载弹窗
````html
  <script>
    //E.loading(title, duration)  title:标题文字(最长8字符)， duration：时长,毫秒单位,默认2s
    //参数可有可无
    E.loading('加载', 2000);
  </script>
````
#### 单元弹窗
````html
  <script>
    /*
      E.showModule({ title, content, cancelText, confirmText })
    */
    E.showModule({
      title: "标题",
      content: "这是内容",//必须，有长度限制，最多四行文字
      cancelText: "取消",
      confirmText: "确定",
      success: function(res){//成功回调boolean
        console.log(res);
      }
    });
  </script>
````
#### 提示文字
````html
  <script>
    //E.showMsg(content, duration)
    E.showMsg("提示内容", 2000);
  </script>
````

#### 关闭弹窗
````html
  <script>
    //E.close();
    E.showMsg("提示内容", 20000);
    setTimeout(function(){E.close()},2000);
  </script>
````

## base64加密解密
````html
  <script>
    let a = E.encryptBASE64("哈喽");
    let b = E.decryptBASE64(a);
    console.log(a, b);
  </script>
````
