package com.ljp.basicplatform.system.controller;

import com.ljp.basicplatform.system.service.ISysUserService;
import com.ljp.basicplatform.system.service.impl.SysUserServiceImpl;
import com.ljp.basicplatform.system.vo.SysUserVo;
import com.ljp.basicplatform.utils.JwtUtils;
import com.ljp.basicplatform.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author ljp
 * @since 2021/4/2 14:54
 */
@RestController
public class SysLoginController {
    // 1000 * 60 * 60 * 24 * 7 七天
    private Long expiration = 604800000l;

    @Autowired
    private ISysUserService iSysUserService;


    @PostMapping("login")
    public Object login(String username, String password) {
        SysUserVo login = iSysUserService.login(username, password);
        if (login == null) return ResultUtils.fail("账号或密码错误");
        String sign = JwtUtils.sign(login.getId(), expiration);
        return ResultUtils.success(sign);
    }
}
