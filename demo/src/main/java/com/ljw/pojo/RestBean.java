package com.ljw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ljw
 * @version 1.0
 * date 2023/4/19 下午7:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestBean<T> {
    private Integer status;
    private boolean success;
    private T message;

    public static <T> RestBean<T> success() {
        return new RestBean<T>(200, true, null);
    }

    public static <T> RestBean<T> success(T data) {
        return new RestBean<T>(200, true, data);
    }

    public static <T> RestBean<T> fail(Integer status, T data) {
        return new RestBean<T>(status, true, data);
    }


}
