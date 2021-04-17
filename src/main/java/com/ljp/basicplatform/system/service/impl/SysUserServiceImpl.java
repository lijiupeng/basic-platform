package com.ljp.basicplatform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.exception.ApplicationException;
import com.ljp.basicplatform.system.dto.SysSaveUserDto;
import com.ljp.basicplatform.system.dto.SysUserDto;
import com.ljp.basicplatform.system.entity.SysUser;
import com.ljp.basicplatform.system.mapper.SysUserMapper;
import com.ljp.basicplatform.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljp.basicplatform.system.vo.SysUserVo;
import com.ljp.basicplatform.utils.DtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ljp
 * @since 2021-03-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUserVo login(String username, String password) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("username", username);
        sysUserQueryWrapper.eq("password", password);
        SysUser sysUser = baseMapper.selectOne(sysUserQueryWrapper);
        if (sysUser == null) return null;
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUser, sysUserVo);
        return sysUserVo;
    }


    @Override
    public Page<SysUserVo> getPageListByUser(SysUserDto sysUserDto) {
        if (sysUserDto.getPageInfo() == null)
            throw new ApplicationException("分页对象不能为空").error();
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        Page<SysUser> page = page(sysUserDto.getPageInfo(), sysUserQueryWrapper);
        return DtUtils.pageToVo(page, SysUserVo.class, null);
    }

    @Override
    public Integer addUser(SysSaveUserDto sysSaveUserDto) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("username", sysSaveUserDto.getUsername());
        Integer integer = baseMapper.selectCount(sysUserQueryWrapper);
        if(integer > 0) throw new ApplicationException("账号已存在");
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysSaveUserDto, sysUser);
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setStatus(1);
        return baseMapper.insert(sysUser);
    }
}
