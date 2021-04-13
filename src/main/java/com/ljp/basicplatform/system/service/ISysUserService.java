package com.ljp.basicplatform.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.system.dto.SysUserDto;
import com.ljp.basicplatform.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljp.basicplatform.system.vo.SysUserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljp
 * @since 2021-03-22
 */
public interface ISysUserService extends IService<SysUser> {

    SysUserVo login(String username, String password);

    Page<SysUserVo> getPageListByUser(SysUserDto sysUserDto);
}
