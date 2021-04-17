package com.ljp.basicplatform.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljp.basicplatform.system.dto.SysSaveUserDto;
import com.ljp.basicplatform.system.dto.SysUserDto;
import com.ljp.basicplatform.system.service.ISysUserService;
import com.ljp.basicplatform.system.vo.SysUserVo;
import com.ljp.basicplatform.utils.ResultUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ljp
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/system/sys-user")
@RequiredArgsConstructor
public class SysUserController {

    private final ISysUserService iSysUserService;


    @GetMapping()
    public ResultUtils getList(@Validated SysUserDto sysUserDto) {
        return ResultUtils.success(iSysUserService.getPageListByUser(sysUserDto));
    }

    @PostMapping()
    public ResultUtils add(@Validated SysSaveUserDto sysSaveUserDto) {
        return ResultUtils.success(iSysUserService.addUser(sysSaveUserDto));
    }

}
