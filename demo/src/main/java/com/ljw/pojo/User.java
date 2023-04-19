package com.ljw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ljw
 * @version 1.0
 * date 2023/4/19 下午8:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer uid;
    private String email;
    private String userName;
    private String password;
}
