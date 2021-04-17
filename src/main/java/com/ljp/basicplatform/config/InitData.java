package com.ljp.basicplatform.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljp.basicplatform.enums.RedisKeyEnum;
import com.ljp.basicplatform.system.entity.SysDict;
import com.ljp.basicplatform.system.service.ISysDictService;
import com.ljp.basicplatform.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author ljp
 * @since 2021/4/17 11:21
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class InitData {

    private final ISysDictService iSysDictService;

    private final RedisUtils redisUtils;


    @PostConstruct
    private void initRedisData() {
        QueryWrapper<SysDict> queryWrapper=new QueryWrapper();
        queryWrapper.orderByAsc("`key`");

        List<SysDict> list = iSysDictService.list(queryWrapper);
        Map<Object, Object> map = new HashMap<>(list.size() / 3);
        for (int i = 0; i < list.size(); i++) {
            SysDict sysDict = list.get(i);
            List<SysDict> sysDicts = (List<SysDict>) map.get(sysDict.getCode());
            if(CollectionUtils.isEmpty(sysDicts)){
                List<SysDict> dictList = new ArrayList<>();
                dictList.add(sysDict);
                map.put(sysDict.getCode(), dictList);
            }else{
                sysDicts.add(sysDict);
            }
        }
        redisUtils.hmset(RedisKeyEnum.DICT, map);
        log.info("=============数据字典导入redis成功================");
    }
}
