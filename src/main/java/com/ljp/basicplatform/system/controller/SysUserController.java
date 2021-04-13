package com.ljp.basicplatform.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.system.dto.SysUserDto;
import com.ljp.basicplatform.system.service.ISysUserService;
import com.ljp.basicplatform.system.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljp
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/system/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;


    @GetMapping("get")
    public Page<SysUserVo> getList(SysUserDto sysUserDto) {
        return iSysUserService.getPageListByUser(sysUserDto);
    }

}
