package com.ljp.basicplatform.exception;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author ljp
 * @since 2021/4/14 19:23
 */
@Data
public class ApplicationException extends RuntimeException{

    // 错误信息
    private String message;
    // 错误级别
    private Integer level;

    public static final Integer LEVEL_USER = 99;

    public static final Integer LEVEL_SYSTEM = 90;

    public ApplicationException(String message) {
        this.message = message;
        this.level = LEVEL_USER;
    }

    // 系统级别的错误
    public ApplicationException error(){
        this.level = LEVEL_SYSTEM;
        return this;
    }
}
