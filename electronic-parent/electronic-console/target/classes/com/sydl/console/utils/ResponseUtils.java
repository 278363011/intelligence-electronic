package com.sydl.console.utils;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    /**
     * 渲染返回 token 页面,因为前端页面接收的都是Result对象，故使用application/json返回
     *
     * @param response
     * @throws IOException
     */
    public static void renderToken(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(JSONObject.toJSONString(obj).getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
