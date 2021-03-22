package com.ljp.basicplatform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.system.entity.SysUser;
import com.ljp.basicplatform.system.mapper.SysUserMapper;
import com.ljp.basicplatform.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljp.basicplatform.system.vo.SysUserVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljp
 * @since 2021-03-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Override
    public Page<SysUser> getPageListByUser(){
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        Page<SysUser> sysUserPage = new Page<>();
        Page<SysUser> page = page(sysUserPage, sysUserQueryWrapper);
        return page;
    }
}
