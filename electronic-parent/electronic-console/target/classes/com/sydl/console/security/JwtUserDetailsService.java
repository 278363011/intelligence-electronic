package com.sydl.console.security;

import com.sydl.console.dto.UserRoleDto;
import com.sydl.console.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户及角色信息
        UserRoleDto urd = sysUserMapper.getUserRoleByUsername(username);
        return new JwtUserDetails(urd);
    }
}
