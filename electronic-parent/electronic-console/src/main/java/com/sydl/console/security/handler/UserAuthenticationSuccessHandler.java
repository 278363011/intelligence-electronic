package com.sydl.console.security.handler;

import com.sydl.console.result.Result;
import com.sydl.console.security.JwtUserDetails;
import com.sydl.console.utils.JwtTokenUtil;
import com.sydl.console.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtTokenUtil JwtTokenUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        String token = JwtTokenUtil.generateToken(jwtUserDetails);
        ResponseUtils.renderToken(httpServletResponse,Result.success(token));

    }
}
