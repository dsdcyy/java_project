package com.ljw.service;

import com.ljw.mapper.UserMapper;
import com.ljw.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ljw
 * @version 1.0
 * date 2023/4/19 下午8:25
 */
@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByEmailOrUserName(username);
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .roles("user").build();
    }
}
