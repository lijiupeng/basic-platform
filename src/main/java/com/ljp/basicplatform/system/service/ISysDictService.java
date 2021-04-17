package com.ljp.basicplatform.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.enums.DictCodeEnum;
import com.ljp.basicplatform.system.dto.SysDictDto;
import com.ljp.basicplatform.system.dto.SysSaveDictDto;
import com.ljp.basicplatform.system.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljp.basicplatform.system.vo.SysDictVo;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljp
 * @since 2021-04-15
 */
public interface ISysDictService extends IService<SysDict> {

    Page<SysDictVo> getPageListDictByDict(SysDictDto sysDictDto);

    Integer addDict(SysSaveDictDto sysSaveDictDto);

    List<SysDict> getListByCode(String code);

    /**
     * 根据code获取list
     * @param dictCodeEnum
     * @return
     */
    List<SysDict> getListByCode(DictCodeEnum dictCodeEnum);

    /**
     * 根据key获取value
     * @param sysDictVos 集合
     * @param key key
     * @return
     */
    String getValueByKey(List<SysDict> sysDictVos, Integer key);
}
