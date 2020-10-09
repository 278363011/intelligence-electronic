package com.sydl.console.security.handler;

import com.alibaba.fastjson.JSON;
import com.sydl.console.result.CodeMsg;
import com.sydl.console.result.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        Result<Object> success = Result.error(CodeMsg.create(100, "注销成功!"));
        httpServletResponse.getWriter().write(JSON.toJSONString(success));
    }
}
