package com.sydl.console.security;

import com.sydl.console.dto.UserRoleDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUserDetails implements UserDetails,Serializable {
    private Integer id;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;


    public JwtUserDetails(UserRoleDto userRole) {
        this.id = userRole.getId();
        this.username = userRole.getUserName();
        this.password = userRole.getPassword();
        this.authorities = userRole.getRoleList().stream().map(item ->new SimpleGrantedAuthority(item.getName())).collect(Collectors.toSet());
    }
    //获取角色权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    //查到的密码
    @Override
    public String getPassword() { // 最重点
        return this.password;
    }
    //查到的账号
    @Override
    public String getUsername() {
        return this.username;
    }
    //账号是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账号是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //账号凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //账号是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
