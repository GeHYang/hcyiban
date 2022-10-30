package com.hgl.utils.response;

import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseData;
import com.hgl.utils.response.entity.ResponseEnum;
import com.hgl.utils.response.entity.ResponseMsg;

public class ResponseUtils {


    public static Response response(ResponseEnum responseEnum){
        return new ResponseMsg(responseEnum);
    }

    /**
     * 成功状态
     * @return
     */
    public static Response success(){
        ResponseMsg response = new ResponseMsg();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMsg(ResponseEnum.SUCCESS.getMsg());
        return response;
    }
    /**
     * 失败状态
     * @return
     */
    public static Response error(){
        ResponseMsg response = new ResponseMsg();
        response.setCode(ResponseEnum.FAIL.getCode());
        response.setMsg(ResponseEnum.FAIL.getMsg());
        return response;
    }

    public static Response error(String msg){
        ResponseMsg response = new ResponseMsg();
        response.setCode(ResponseEnum.FAIL.getCode());
        response.setMsg(msg);
        return response;
    }

    /**
     * 自定义返回信息及状态
     * @param code
     * @param msg
     * @return
     */
    public static Response respMsg(int code, String msg){
        ResponseMsg response = new ResponseMsg();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static Response respMsg( String msg){
        ResponseMsg response = new ResponseMsg();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMsg(msg);
        return response;
    }


    /**
     * 自定义状态数据响应
     * @param code
     * @param data
     * @return
     */
    public static Response respData(int code, Object data){
        ResponseData response = new ResponseData();
        response.setCode(code);
        response.setData(data);
        return response;
    }

    /**
     * 响应数据
     * @param data
     * @return
     */
    public static Response respData(Object data){
        ResponseData response = new ResponseData();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

}
