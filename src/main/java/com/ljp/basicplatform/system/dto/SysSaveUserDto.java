package com.ljp.basicplatform.system.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljp
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysSaveUserDto {

    private Integer id;

    /**
     * 昵称
     */
    @NotBlank
    private String nickname;

    /**
     * 账号
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private Integer phone;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 备注
     */
    private String remakes;

}
