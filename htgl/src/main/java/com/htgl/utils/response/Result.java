package com.htgl.utils.response;

public class Result<T> {
  private Integer code; // 返回状态码
  private String message; // 返回信息
  private  T data; // 返回数据

  public Result(){}

  // 返回数据
  protected static <T> Result<T> build(T data){
    Result<T> result = new Result<T>();
    if (data != null)
      result.setData(data);
    return result;
  }

  public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum){
    Result<T> result = build(data);
    result.setCode(resultCodeEnum.getCode());
    result.setMessage(resultCodeEnum.getMessage());
    return result;
  }

  public static <T> Result<T> ok(){
    return Result.ok(null);
  }

  /**
   * 操作成功
   * @param data
   * @param <T>
   * @return
   */
  public static <T> Result<T> ok (T data){
    Result<T> result = build(data);
    return build(data, ResultCodeEnum.SUCCESS);
  }

  public static <T> Result<T> fail(){
    return Result.fail(null);
  }

  /**
   * 操作失败
   * @param data
   * @param <T>
   * @return
   */
  public static <T> Result<T> fail(T data){
    Result<T> result = build(data);
    return build(data, ResultCodeEnum.FAIL);
  }
  public Result<T> message(String msg){
    this.setMessage(msg);
    return this;
  }
  public Result<T> code(Integer code){
    this.setCode(code);
    return this;
  }

  public boolean isOK(){
    if (this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()){
      return true;
    }
    return false;
  }





  /**
   * get set
   *
   */
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
