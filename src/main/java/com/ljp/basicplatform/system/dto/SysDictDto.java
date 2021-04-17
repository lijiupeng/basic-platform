package com.ljp.basicplatform.system.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.system.entity.SysDict;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class SysDictDto extends SysDict {

    private Page pageInfo;

}
