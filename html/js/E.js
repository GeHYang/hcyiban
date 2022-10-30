; (function (w, document) {

  let timerout;
  /**对象方式建立连接
   * url, method, timeout, async, dataType, header, responseType
   * @param {Object}  
   */
  function ajax(option = { url, method, timeout, async, dataType, headers, responseType }) {
    return new Promise((resolve, reject) => {

      if (!option.url) {
        // console.error("url is null");
        reject("url is null");
      }
      if (!option.method) option.method = "GET";//请求方式
      if (!option.timeout) option.timeout = 2000;//超时时间，默认2s
      if (!option.async) option.async = true;//是否异步
      if (!option.dataType) option.dataType = "String";//数据类型
      if (!option.headers && option.method.toLowerCase() === "post") option.headers = { "Content-type": "application/x-www-form-urlencoded" };//请求头

      let timeoutFlag = null;//监听超时
      let xhr = new XMLHttpRequest();
      //网络异常监听
      xhr.onerror = function () {
        xhr.abort();
        reject("Network exception");
      }

      //打开连接
      xhr.open(option.method, option.url, option.async);
      //设置请求头
      if (option.headers) {
        //设置请求头
        xhr.setRequestHeader(Object.keys(option.headers)[0], option.headers[Object.keys(option.headers)[0]]);
      }
      //监听连接是否超时
      timeoutFlag = window.setTimeout(() => {
        window.clearTimeout(timeoutFlag);
        xhr.abort();
        reject("timeout");
      }, option.timeout)
      //是否为json数据
      if (option.dataType.toLowerCase() === "json") {
        try {
          option.data = JSON.stringify(option.data);
        } catch (error) {
          console.error(error);
          xhr.abort();
          reject(error);
        }
      } else if (option.data instanceof Array) {
        reject("data cannot be an array");
      } else if (option.data) {
        option.data = serializeStr(option.data);
      }


      //发送数据
      xhr.send(option.data);
      //接收服务器回调
      xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
          if (xhr.status >= 200 && xhr.status < 300) {
            window.clearTimeout(timeoutFlag);
            let data = xhr.responseText;
            if (option.responseType == 'json') data = JSON.parse(xhr.responseText);//转换json
            resolve(data, xhr.responseXML);
          }
        }
      }
    });
  }
  /**get请求
   * 
   * @param {*} url 
   * @param {*} data 
   * @param {*} async 
   * @param {*} timeout 
   * @returns 
   */
  function get(url, data, async, timeout) {
    return new Promise((resolve, reject) => {
      //序列化data
      data = serializeStr(data);

      let timeoutFlag = null;

      async = async || true;

      if (!url) {
        reject("url cannot be null");
      }
      //创建请求
      const xhr = new XMLHttpRequest();

      //监听
      xhr.addEventListener("readystatechange", function () {
        if (xhr.readyState === 4) {
          if (xhr.status >= 200 && xhr.status < 300) {
            window.clearTimeout(timeoutFlag);
            let result;
            try {
              result = JSON.parse(xhr.responseText);
            } catch (error) {
              result = xhr.responseText;
            }
            resolve(result);
          }
        }
      })

      //将参数添加至地址
      url += '?' + data;
      //打开连接
      xhr.open("GET", url, async);

      //网络异常监听
      xhr.onerror = function () {
        xhr.abort();
        reject("Network exception");
      }

      timeoutFlag = window.setTimeout(() => {
        window.clearTimeout(timeoutFlag);
        xhr.abort();
        reject("timeout");
      }, timeout);

      xhr.send();
    });
  }
  function post(url, data, async, timeout) {
    return new Promise((resolve, reject) => {
      //序列化data
      data = serializeStr(data);

      async = async || true;

      if (!url) {
        reject("url cannot be null");
      }
      let timeoutFlag = null;//监听超时
      //创建请求
      const xhr = new XMLHttpRequest();

      //监听
      xhr.addEventListener("readystatechange", function () {
        if (xhr.readyState === 4) {
          if (xhr.status >= 200 && xhr.status < 300) {
            window.clearTimeout(timeoutFlag);
            let result;
            try {
              result = JSON.parse(xhr.responseText);
            } catch (error) {
              result = xhr.responseText;
            }
            resolve(result);
          }
        }
      })

      //打开连接
      xhr.open("POST", url, async);

      //网络异常监听
      xhr.onerror = function () {
        xhr.abort();
        reject("Network exception");
      }
      //设置头部
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

      timeoutFlag = window.setTimeout(function () {
        window.clearTimeout(timeoutFlag);
        xhr.abort();
        reject("timeout");
      }, timeout);

      xhr.send(data);
    });
  }
  /**序列化字符串
   * id=xxx&name=&&&
   * @param {*} data 
   */
  function serializeStr(data) {
    if (data instanceof String) {
      return data;
    }
    if (data instanceof Object) {
      let new_data = "";
      for (const key in data) {
        new_data += key + "=" + data[key];
        new_data += '&';
      }
      new_data = new_data.substring(0, new_data.length - 1);//删除最后&
      return new_data;
    }
    return data;
  }
  /**弹窗组件
   * 
   */
  /**加载弹窗
   * 
   * @param {标题} title 最长8字符
   * @param {时长} duration 
   */
  function loading(title, duration) {
    //创建弹窗
    let box = document.createElement("div");//弹窗盒子
    let style = document.createElement("style");//弹窗样式
    let doc = ``;//弹窗内容
    let styleDoc = ``;//样式内容


    box.className = "popup1";
    style.className = "popup1_style";

    {
      doc = `
       <div class="popup1-box">
         <!-- 加载组件 -->
         <div class="loading">
           <div class="top"></div>
         </div>
         <div style="margin-top: 5px;text-align: center;">${title ? title.substring(0, 8) : "加载中"}</div>
       </div>
     `;
    }
    {
      styleDoc = `
     .popup1{
       z-index: 100000;
       display: flex;
       justify-content: center;
       align-items: center;
       position: fixed;
       top: 0;
       left: 0;
       width: 100vw;
       height: 100vh;
       background-color: rgba(255, 255, 255, .5);
     }
     .loading{
       display: flex;
       justify-content: center;
       align-items: center;
     }
     .loading>div{
       display: flex;
       justify-content: center;
       align-items: center;
       position: relative;
       text-align: center;
       animation: loading 1.5s infinite;
     }
     .loading>.top::before{
       display: block;
       content: "";
       border: 10px solid transparent;
       border-top-color: rgb(255, 0, 43);
     }
     .loading>.top::after{
       position: absolute;
       content: "";
       border: 10px solid transparent;
       border-bottom-color: rgb(0, 132, 255);
     }
     @keyframes loading {
       0%{
         transform: rotate(0deg);
       }
       100%{
         transform: rotate(360deg);
       }
     }
     `
    }

    box.innerHTML = doc;
    style.innerHTML = styleDoc;
    document.head.appendChild(style);
    document.body.appendChild(box);

    timerout = setTimeout(function (){close();}, duration ? duration : 3000);

  }
  /**模块弹窗
   * 
   * @param {标题} title 
   * @param {内容} content 
   * @param {取消文字} cancelText 
   * @param {确定文字} confirmText 
   * @returns 
   */
  function showModule(option = { title, content, cancelText, confirmText }) {
    option.title = option.title ? option.title : "提示";

    option.cancelText = option.cancelText ? option.cancelText : "取消";
    option.confirmText = option.confirmText ? option.confirmText : "确定";
    if (!option.content) {
      console.error("content is undefined");
      option.error && option.error("content is undefined");
      return;
    }

    let box = document.createElement("div");//弹窗盒子
    let style = document.createElement("style");//弹窗样式
    let doc = ``;//弹窗内容
    let styleDoc = ``;//样式内容

    box.className = "popup1";
    style.className = "popup1_style";

    {
      doc = `
     <div class="popup1-box">
       <div class="module">
         <div class="title">${option.title.substring(0, 5)}</div>
         <div class="content">${option.content}</div>
         <div class="btn-box">
           <button id="cancel">${option.cancelText.substring(0, 4)}</button><button id="confirm">${option.confirmText.substring(0, 4)}</button>
         </div>
       </div>
     </div>
     `;
      styleDoc = `
     .popup1{
       z-index: 100000;
       display: flex;
       justify-content: center;
       align-items: center;
       position: fixed;
       top: 0;
       left: 0;
       width: 100vw;
       height: 100vh;
       background-color: rgba(255, 255, 255, .5);
     }
     .module{
       overflow: hidden;
       display: flex;
       flex-direction: column;
       justify-content: center;
       align-items: center;
       width: 250px;
       border: 1px solid rgba(0, 0, 0, .1);
       box-shadow: 0 0 50px #c7c7c7 ;
       border-radius: 10px;
       background-color: white;
     }
     .module>.title{
       width: 100%;
       padding: 10px 0 5px 0;
       text-align: center;
       font-size: 20px;
       font-weight: bold;
       border-bottom: 1px solid #ccc;
     }
     .module>.content{
       box-sizing: border-box;
       overflow: hidden;
       width: 100%;
       min-height: 50px;
       max-height: 98px;
       padding: 10px 5px;
       text-align: center;
       text-overflow: ellipsis;
       display: -webkit-box;
       -webkit-box-orient: vertical;
       -webkit-line-clamp: 4;
     }
     .btn-box{
       overflow: hidden;
       width: 100%;
     }
     .btn-box>button{
       box-sizing: border-box;
       margin: 0;
       line-height: 35px;
       width: 50%;
       border: none;
       border-top: 1px solid #ccc;
       background-color: transparent;
       font-size: 18px;
     }
     .btn-box>#confirm{
       color: rgb(45, 172, 83);
       border-left: 1px solid #ccc;
     }
     .btn-box>button:hover{
       background-color: #ececec;
     }
     .btn-box>#cancel:active{
       background-color: #ccc;
     }
     .btn-box>#confirm:active{
       color: white;
       background-color: rgb(45, 172, 83);
     }
     `;
    }

    document.body.appendChild(box);
    document.head.appendChild(style);

    box.innerHTML = doc;
    style.innerHTML = styleDoc;

    document.querySelector("#confirm").addEventListener('click', function () {
      close();
      option.success && option.success(true);
    });
    document.querySelector("#cancel").addEventListener('click', function () {
      close();
      option.success && option.success(false);
    });

  }
  /**提示信息弹窗
   * 
   * @param {content} content 
   * @param {duration} duration 
   * @returns 
   */
  function showMsg(content, duration) {
    if (!content) {
      console.error("content is undefined");
      return;
    }
    let box = document.createElement("div");//弹窗盒子
    let style = document.createElement("style");//弹窗样式
    let doc = ``;//弹窗内容
    let styleDoc = ``;//样式内容

    box.className = "popup1";
    style.className = "popup1_style";

    {
      doc = `
     <div class="popup1">
       <div class="popup1-box">
         <div class="module">
           <span>${content}</span>
         </div>
       </div>
     </div>
     `;
      styleDoc = `
     .popup1{
       z-index: 100000;
       display: flex;
       justify-content: center;
       align-items: center;
       position: fixed;
       top: 0;
       left: 0;
       width: 100vw;
       height: 100vh;
       background-color: rgba(255, 255, 255, .5);
     }
     .module{
       display: flex;
       flex-direction: column;
       justify-content: center;
       align-items: center;
       max-width: 250px;
       padding: 0px 8px;
       border: 1px solid rgba(255,255,255, .3);
       box-shadow: 0 0 20px #c7c7c7 ;
       animation:show 1s;
       overflow: hidden;
       text-overflow: ellipsis;
       white-space: nowrap;
 
       border-radius: 10px;
       background-color: rgb(218, 218, 218);
     }
     .module>span{
       
       display: block;
       line-height: 28px;
     }
     @keyframes show{
       0%{
         opacity: 0;
       }
       100%{
         opacity: 1;
       }
     }
     `;
    }

    box.innerHTML = doc;
    style.innerHTML = styleDoc;

    document.body.appendChild(box);
    document.head.appendChild(style);

    setTimeout(function () {
      close();
    }, duration ? duration : 3000);

  }
  /**关闭
   * 
   */
  function close() {
    // 关闭延时器
    if (document.querySelector('.popup1')) { document.body.removeChild(document.querySelector('.popup1')); }
    if (document.querySelector('.popup1_style')) { document.head.removeChild(document.querySelector('.popup1_style')); }
    clearTimeout(timerout)
  }
  //base64加密
  function encryptBASE64(word) {
    word = encodeURIComponent(word);
    return window.btoa(word);
  }
  //base64解密
  function decryptBASE64(word) {
    return decodeURIComponent(window.atob(word));
  }

  //弹窗
  

  //E工厂
  function E(selector) { return new E.fn.init(selector); };
  E.fn = E.prototype = {};
  E.ajax = function (option = { url, method, timeout, async, dataType, header, responseType }) { return ajax(option) }
  E.get = function (url, data, async, timeout) { return get(url, data, async, timeout); }
  E.post = function (url, data, async, timeout) { return post(url, data, async, timeout); }
  E.loading = function (title, duration) { close(); loading(title, duration) }
  E.showModule = function (option) { close(); showModule(option) }
  E.showMsg = function (content, duration) { close(); showMsg(content, duration); }
  E.close = function () {close();}
  E.encryptBASE64 = function (word) { return encryptBASE64(word); }
  E.decryptBASE64 = function (word) { return decryptBASE64(word); }

  var init = E.fn.init = function (selector) {
    const that = this;
    function Fn(arr) {
      this.length = arr.length;
      for (let i = 0; i < arr.length; i++) {
        this[i] = arr[i];
      }
    };
    Fn.prototype = {
      eq(index) {//第几个节点
        for (let i = 0; i < this.length; i++) {
          if (i != index) {
            delete this[i];
            this.length--;
          }
        }
        if (index != 0) {
          this[0] = this[index];
          delete this[index]
        }
        that.fn.preNode = new Fn(that.el);
        that.el = this[index];
        return this;
      },
      add(el) {//添加节点
        if (!this.preNode) {
          this[0].parentNode.appendChild(el);
          this[this.length] = el;
          this.length++;
        } else {
          this[0].appendChild(el);
        }
        return this;
      },
      css() {//添加样式
        const args = arguments;
        if (typeof args[0] === 'string' && args.length === 1) {
          //用户只传入一个参数，代表获取该样式信息
          return getComputedStyle(this[0])[args[0]];
        } else if (typeof args[0] === 'string' && typeof args[1] === 'string' && args.length === 2) {
          for (let i = 0; i < this.length; i++) {
            this[i].style = `${args[0]}: ${args[1]};`;
          }
          return this;
        } else if (args[0] instanceof Object && args.length === 1) {
          for (let i = 0; i < this.length; i++) {
            for (const key in args[0]) {
              this[i].style = `${key}: ${args[0][key]};`;
            }
          }
          return this;
        } else {
          console.error("CSS function arguments is incorrect");
          return this;
        }
      }
    }
    if (!selector) {
      return this;
    } else {
      this.el = document.querySelectorAll(selector);
      this.fn = new Fn(this.el);
      return this.fn;
    }
  }
  init.prototype = E.fn
  w.E = E;
})(window, document);