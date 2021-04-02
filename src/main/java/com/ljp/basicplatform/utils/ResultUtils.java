package com.ljp.basicplatform.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author ljp
 * @since 2021/4/2 11:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultUtils {
    private Integer code;
    private Object data;
    private String msg;

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer FAIL_CODE = 500;
    private static final String EMPTY_STRING = "";

    public static ResultUtils success() {
        return success(EMPTY_STRING, SUCCESS_CODE, EMPTY_STRING);
    }

    public static ResultUtils success(Object data) {
        return success(data, SUCCESS_CODE, EMPTY_STRING);
    }

    public static ResultUtils success(Object data, Integer code) {
        return success(data, code, EMPTY_STRING);
    }

    public static ResultUtils success(Object data, String msg) {
        return success(data, SUCCESS_CODE, msg);
    }

    public static ResultUtils success(Object data, Integer code, String msg) {
        return new ResultUtils(code, data, msg);
    }


    public static ResultUtils fail() {
        return fail(EMPTY_STRING, FAIL_CODE, EMPTY_STRING);
    }

    public static ResultUtils fail(String msg) {
        return fail(EMPTY_STRING, FAIL_CODE, msg);
    }

    public static ResultUtils fail(Integer code) {
        return fail(EMPTY_STRING, code, EMPTY_STRING);
    }

    public static ResultUtils fail(Integer code, String msg) {
        return fail(EMPTY_STRING, code, msg);
    }

    public static ResultUtils fail(Object data, Integer code, String msg) {
        return new ResultUtils(code, data, msg);
    }
}
