package com.ljp.basicplatform.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author ljp
 * @since 2021/4/17 11:49
 */
public enum DictCodeEnum {
    /**
     * 字典类型
     */
    DICT_TYPE("dict_type");

    private String code;

    DictCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
