package com.sydl.console.security.handler;

import com.alibaba.fastjson.JSON;
import com.sydl.console.result.CodeMsg;
import com.sydl.console.result.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        Result<Object> success = Result.error(CodeMsg.create(200, "登录成功!"));


//        SelfUserDetails userDetails = (SelfUserDetails) authentication.getPrincipal();
//
//        String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), 300, "_secret");
//        responseBody.setJwtToken(jwtToken);

        httpServletResponse.getWriter().write(JSON.toJSONString(success));
    }
}
