package com.sydl.console.security.handler;

import com.alibaba.fastjson.JSON;
import com.sydl.console.result.CodeMsg;
import com.sydl.console.result.Result;
import com.sydl.console.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        ResponseUtils.renderToken(httpServletResponse,Result.error(CodeMsg.create(300, "权限不足!")));

    }
}
