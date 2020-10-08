package com.sydl.console.result;

public class CodeMsg {

    private int code;
    private String msg;
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(50010, "服务端异常!");
    public static CodeMsg USER_ID_EMPRT = new CodeMsg(1, "用户ID不能为空");

    public static CodeMsg create(int code, String msg) {
        CodeMsg aCodeMsg = new CodeMsg(code, msg);
        return aCodeMsg;
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
