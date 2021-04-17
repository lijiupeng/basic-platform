package com.ljp.basicplatform.system.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.system.entity.SysDict;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
public class SysSaveDictDto extends SysDict {


    /**
     * 建
     */
    @NotNull
    private Integer key;

    /**
     * 值
     */
    @NotBlank
    private String value;

    /**
     * 字典类型
     */
    @NotBlank
    private String code;

    /**
     * 1:系统级，2:用户级
     */
    @NotNull
    private Integer type;
}
