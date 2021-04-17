package com.ljp.basicplatform.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljp.basicplatform.enums.DictCodeEnum;
import com.ljp.basicplatform.enums.RedisKeyEnum;
import com.ljp.basicplatform.exception.ApplicationException;
import com.ljp.basicplatform.system.dto.SysDictDto;
import com.ljp.basicplatform.system.dto.SysSaveDictDto;
import com.ljp.basicplatform.system.entity.SysDict;
import com.ljp.basicplatform.system.entity.SysUser;
import com.ljp.basicplatform.system.mapper.SysDictMapper;
import com.ljp.basicplatform.system.service.ISysDictService;
import com.ljp.basicplatform.system.vo.SysDictVo;
import com.ljp.basicplatform.utils.DtUtils;
import com.ljp.basicplatform.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljp
 * @since 2021-04-15
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    private final RedisUtils redisUtils;
    
    @Override
    public Page<SysDictVo> getPageListDictByDict(SysDictDto sysDictDto){
        if (sysDictDto.getPageInfo() == null)
            throw new ApplicationException("分页对象不能为空").error();
        QueryWrapper<SysDict> sysUserQueryWrapper = new QueryWrapper<>();
        List<SysDict> listByCode = getListByCode(DictCodeEnum.DICT_TYPE);
        Page<SysUser> page = page(sysDictDto.getPageInfo(), sysUserQueryWrapper);
        return DtUtils.pageToVo(page, SysDictVo.class, (item) -> {
            item.setTypeName(getValueByKey(listByCode, item.getKey()));
        });
    }

    @Override
    public Integer addDict(SysSaveDictDto sysSaveDictDto) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("`key`", sysSaveDictDto.getKey())
                .eq("code", sysSaveDictDto.getCode());
        Integer integer = baseMapper.selectCount(queryWrapper);
        if(integer > 0) throw new ApplicationException("此字典code的值已存在");
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(sysSaveDictDto, sysDict);
        return baseMapper.insert(sysDict);
    }


    @Override
    public List<SysDict> getListByCode(String code){
        return (List<SysDict>) redisUtils.hmget(RedisKeyEnum.DICT).get(code);
    }

    @Override
    public List<SysDict> getListByCode(DictCodeEnum dictCodeEnum){
        return getListByCode(dictCodeEnum.getCode());
    }

    @Override
    public String getValueByKey(List<SysDict> sysDicts, Integer key){
        if(CollectionUtils.isEmpty(sysDicts)) throw new ApplicationException("字典集合不能为空").error();
        if(key == null) throw new ApplicationException("key不能为空").error();
        Optional<SysDict> first= sysDicts.stream().filter(sysDict -> key.equals(sysDict.getKey())).findFirst();
        if (first.isPresent()) {
            return first.get().getValue();
        } else {
            throw new ApplicationException("字典值转换时找不到对应的数据").error();
        }
    }


}
