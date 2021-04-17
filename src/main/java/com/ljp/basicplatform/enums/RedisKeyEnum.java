package com.ljp.basicplatform.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author ljp
 * @since 2021/4/17 10:45
 */
public enum RedisKeyEnum {
    // 在redis里的key
    DICT("DICT_LIST", -1l);

    // 键
    private String key;
    // 过期时间 -1 为永久
    private Long time;

    /**
     *
     * @param key
     * @param time
     */
    RedisKeyEnum(String key, Long time) {
        this.key = key;
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public Long getTime() {
        return time;
    }
}
