package com.ljp.basicplatform.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ljp
 * @since 2021-03-22
 */
public interface ISysUserService extends IService<SysUser> {

    Page<SysUser> getPageListByUser();
}
