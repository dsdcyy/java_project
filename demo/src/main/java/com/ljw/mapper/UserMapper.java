package com.ljw.mapper;

import com.ljw.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ljw
 * @version 1.0
 * date 2023/4/19 下午8:26
 */
@Mapper
public interface UserMapper {
    @Select("select * from t_user where user_name=#{username} and password=#{password};")
    User getUserByUserNameAndPassWord(String username, String passWord);

    @Select("select * from t_user where email=#{email};")
    User getUserByEmail(String email);

    @Select("select * from t_user where email=#{text} or user_name=#{text};")
    User getUserByEmailOrUserName(String text);

}
