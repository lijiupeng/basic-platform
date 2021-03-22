package com.ljp.basicplatform.system.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class SysUserRoleLink implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 角色id
     */
    private Integer rid;


}
