package com.ljp.basicplatform.config;

import com.ljp.basicplatform.exception.ApplicationException;
import com.ljp.basicplatform.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 前端控制器异常配置
 * </p>
 *
 * @author ljp
 * @since 2021/4/14 19:38
 */
@ControllerAdvice
@Log4j2
public class ControllerExceptionConfig {
    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultUtils exceptionHandler(Exception ex) {
        long bugId = System.currentTimeMillis();
        log.error("前端控制器未知错误:#{}", bugId);
        ex.printStackTrace();
        return ResultUtils.fail(ResultUtils.FAIL_CODE_SYSTEM,"错误码:#"+bugId);
    }


    /**
     * 拦截捕捉应用异常 ApplicationException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ApplicationException.class)
    public ResultUtils applicationExceptionHandler(ApplicationException ex) {
        if (ApplicationException.LEVEL_SYSTEM.equals(ex.getLevel())) {
            long bugId = System.currentTimeMillis();
            log.error("前端控制器逻辑异常#{}: {} ({}:{})"
                    , bugId
                    , ex.getMessage()
                    , ex.getStackTrace()[0].getFileName()
                    , ex.getStackTrace()[0].getLineNumber()
            );
            return ResultUtils.fail(ResultUtils.FAIL_CODE_SYSTEM, "错误码:"+ bugId);
        }
        return ResultUtils.fail(ex.getMessage());
    }

    /**
     * 拦截捕捉参数错误异常 BindException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResultUtils bindExceptionHandler(BindException ex) {
        log.error("前端控制器参数错误: {}:{} ({})",
                ex.getFieldError().getField(),
                ex.getFieldError().getDefaultMessage(),
                ex.getFieldError().getObjectName()
        );
        return ResultUtils.fail(ResultUtils.FAIL_CODE_SYSTEM,
                "参数错误：" + ex.getFieldError().getField() + ex.getFieldError().getDefaultMessage()
        );
    }
}
