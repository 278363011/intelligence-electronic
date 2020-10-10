package com.sydl.console.security.handler;


import com.alibaba.fastjson.JSON;
import com.sydl.console.result.CodeMsg;
import com.sydl.console.result.Result;
import com.sydl.console.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ResponseUtils.renderToken(httpServletResponse,Result.error(CodeMsg.create(000, "需要登录认证!")));


    }
}
