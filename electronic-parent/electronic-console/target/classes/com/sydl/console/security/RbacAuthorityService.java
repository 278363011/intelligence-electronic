package com.sydl.console.security;

import com.sydl.console.mapper.SysUserMapper;
import com.sydl.console.model.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("rbacauthorityservice")
public class RbacAuthorityService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Value("${server.servlet.context-path}")
    String serverContext;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission  = false;

        if (userInfo instanceof JwtUserDetails) {

            //获取角色
            Collection<? extends GrantedAuthority> authorities = ((JwtUserDetails) userInfo).getAuthorities();

            //获取资源
            Set<String> urls = new HashSet();

            //根据角色获取url
            for(GrantedAuthority grantedAuthority : authorities){
                sysUserMapper.getResourceByRoleName(grantedAuthority.getAuthority()).forEach(item->{
                    urls.add(serverContext+item.getUrl());
                });
            }

            AntPathMatcher antPathMatcher = new AntPathMatcher();

            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }
}
