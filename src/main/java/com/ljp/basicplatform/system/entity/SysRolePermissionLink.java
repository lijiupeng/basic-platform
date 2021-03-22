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
public class SysRolePermissionLink implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer rid;

    /**
     * 权限id
     */
    private Integer pid;


}
