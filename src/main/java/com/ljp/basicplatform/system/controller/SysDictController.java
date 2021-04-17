package com.ljp.basicplatform.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljp.basicplatform.enums.DictCodeEnum;
import com.ljp.basicplatform.exception.ApplicationException;
import com.ljp.basicplatform.system.dto.SysDictDto;
import com.ljp.basicplatform.system.dto.SysSaveDictDto;
import com.ljp.basicplatform.system.dto.SysSaveUserDto;
import com.ljp.basicplatform.system.entity.SysDict;
import com.ljp.basicplatform.system.service.ISysDictService;
import com.ljp.basicplatform.system.vo.SysDictVo;
import com.ljp.basicplatform.utils.DtUtils;
import com.ljp.basicplatform.utils.ResultUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljp
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/system/sys-dict")
@RequiredArgsConstructor
public class SysDictController {

    private final ISysDictService iSysDictService;

    @GetMapping
    public ResultUtils getPageListDict(SysDictDto sysDictDto){
        return ResultUtils.success(iSysDictService.getPageListDictByDict(sysDictDto));
    }

    @PostMapping
    public ResultUtils add(@Validated SysSaveDictDto dto) {
        return ResultUtils.success(iSysDictService.addDict(dto));
    }

    @GetMapping("list")
    public ResultUtils getListByCode(@RequestParam String code){
        List<SysDict> list = iSysDictService.getListByCode(code);;
        List<SysDict> listByCode = iSysDictService.getListByCode(DictCodeEnum.DICT_TYPE);
        List<SysDictVo> sysDictVos = DtUtils.listToVo(list, SysDictVo.class, (item -> {
                item.setTypeName(iSysDictService.getValueByKey(listByCode, item.getKey()));
        }));
        return ResultUtils.success(sysDictVos);
    }
}
