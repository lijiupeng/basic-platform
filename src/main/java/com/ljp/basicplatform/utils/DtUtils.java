package com.ljp.basicplatform.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * Data Transfer Utils （数据传输工具）
 * </p>
 *
 * @author ljp
 * @since 2021/3/25 23:22
 */
public class DtUtils {

    /**
     * 分页对象转换成VO对象
     *
     * @param page     源
     * @param tClass   需要转换泛型的实体
     * @param consumer 转换中进行的操作
     * @param <T>      泛型
     * @return
     */
    public static <T> Page<T> pageToVo(Page page, Class<T> tClass, Consumer<T> consumer) {
        Page<T> newPage = new Page<>();
        List records = page.getRecords();
        List<T> list = new ArrayList<>(records.size());
        try {
            // 用fori是因为list性能最好
            for (int i = 0; i < records.size(); i++) {
                T t = tClass.newInstance();
                BeanUtils.copyProperties(records.get(0), t);
                // 执行循环中所需要操作方法
                if (consumer != null) consumer.accept(t);
                list.add(t);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 这里为null是为了让copy 加快性能
        page.setRecords(null);
        BeanUtils.copyProperties(page, newPage);
        newPage.setRecords(list);
        return newPage;
    }
}
