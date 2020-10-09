package com.sydl.console.security;

import com.sydl.console.security.JwtUserDetailsService;
import com.sydl.console.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 自定义jwtuser
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    //用户登录成功、失败、没有权限、用户未登录、退出登录相关处理涉及的处理器如下

    //用户未登录处理器
    @Autowired
    UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;

    //登录成功处理器
    @Autowired
    UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    //登录失败处理器
    @Autowired
    UserAuthenticationFailureHandler  userAuthenticationFailureHandler;

    //注销成功处理器
    @Autowired
    UserLogoutSuccessHandler userLogoutSuccessHandler;

    //没有权限的处理器
    @Autowired
    UserAccessDeniedHandler userAccessDeniedHandler;

    //拦截token JWT 拦截器

    //自定义登录

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 密码加密
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/electronic/index2")
                .antMatchers("/electronic/error")
                .antMatchers("/index2");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .cors() //跨域支持
                .and().csrf().disable() //关闭csrf验证
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 用了jwt token 不需要session
//                .and()

                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler) //未登陆时返回 JSON 格式的数据给前端（否则为 html）

                .and()
                .authorizeRequests()
                //基于请求方式和角色和认证方式拦截
                //.antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("ADMIN")
                // 测试用资源，需要验证了的用户才能访问
                //.antMatchers("/tasks/**").authenticated()
                // 其他都放行了
                //RBAC的方式进行权限拦截
                .anyRequest()
                .access("@rbacauthorityservice.hasPermission(request,authentication)")

                //form login
                .and()
                .formLogin()  //开启登录
                .successHandler(userAuthenticationSuccessHandler) // 登录成功
                .failureHandler(userAuthenticationFailureHandler) // 登录失败
                .permitAll()
                //logout
                .and()
                .logout()
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .permitAll();

//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .and()
//                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
//                .accessDeniedHandler(new JWTAccessDeniedHandler());      //添加无权限时的处理


            // 记住我
            http.rememberMe().rememberMeParameter("remember-me")
                    .userDetailsService(jwtUserDetailsService).tokenValiditySeconds(300);

            http.exceptionHandling().accessDeniedHandler(userAccessDeniedHandler); // 无权访问 JSON 格式的数据
            //http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
