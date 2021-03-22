package com.ljp.basicplatform.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型(1:路由权限，2：行为权限，3：按钮权限)
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    /**
     * 唯一编码
     */
    private String code;


}
