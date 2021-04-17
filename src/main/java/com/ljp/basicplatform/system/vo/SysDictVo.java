package com.ljp.basicplatform.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysDictVo extends SysDict {

    private String typeName;

}
