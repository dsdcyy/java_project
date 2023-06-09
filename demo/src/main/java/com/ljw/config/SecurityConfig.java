package com.ljw.config;

import com.alibaba.fastjson2.JSONObject;
import com.ljw.pojo.RestBean;
import com.ljw.service.AuthUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

/**
 * @author ljw
 * @version 1.0
 * date 2023/4/18 下午9:08
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Resource
    AuthUserService authUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(this::onAuthenticationFailure)
                .and()
                .build();

    }

    /**
     * 用户名安全管理
     *
     * @param httpSecurity
     * @return AuthenticationManager
     * @throws Exception
     */

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authUserService)
                .and()
                .build();
    }

    /**
     * 用户名密码加密类
     *
     * @return BCryptPasswordEncoder
     */

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String loginSuccess = JSONObject.toJSONString(RestBean.success("登入成功"));
        response.getWriter().write(loginSuccess);
        log.info("用户:{}登录成功,登入信息{}", username, loginSuccess);

    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String loginFail = JSONObject.toJSONString(RestBean.fail(401, exception.getMessage()));
        response.getWriter().write(loginFail);
        log.info("用户:{}登录失败,登入信息{}", username, loginFail);
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

    }


}
