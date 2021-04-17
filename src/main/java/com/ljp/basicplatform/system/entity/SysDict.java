package com.ljp.basicplatform.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 建
     */
    @TableField(value="`key`")
    private Integer key;

    /**
     * 值
     */
    @TableField(value="`value`")
    private String value;

    /**
     * 字典类型
     */
    private String code;

    /**
     * 1:系统级，2:用户级
     */
    private Integer type;

    /**
     * 备注
     */
    private String remakes;


}
