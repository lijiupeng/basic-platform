package com.ljp.basicplatform.config;

import com.alibaba.fastjson.JSONObject;
import com.ljp.basicplatform.utils.JwtUtils;
import com.ljp.basicplatform.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author
 * @date 2019-11-23 12:04:13
 * 拦截所有请求,除了登录,判断token是否存在或者有效
 */
@Log4j2
public class SecurityHandler implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception arg3) throws Exception {
        log.info("============================拦截器关闭==============================");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model)
            throws Exception {
        log.info("===========================执行处理完毕=============================");
        long startTime = (long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.info("处理时间：{}", (endTime - startTime) + "ms");
    }

    /**
     * 拦截每个请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("============================拦截器启动==============================");
        log.info("============请求地址：{}",  request.getRequestURI());
        request.setAttribute("startTime", System.currentTimeMillis());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        //token不存在
        if (null != token && "" != token) {
            Integer id = JwtUtils.unsign(token);
            if (null == id) {
                responseMessage(response, response.getWriter(), ResultUtils.fail(401,"登录凭证过期或无效"));
                log.info("======================token过期或无效======================");
                return false;
            } else {
                return true;
            }
        } else {
            //失败
            responseMessage(response, response.getWriter(), ResultUtils.fail(401,"无登录凭证"));
            log.warn("============================无token信息============================");
            return false;
        }
    }

    /**
     * 请求不通过，返回错误信息给客户端
     */
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResultUtils resultGet) {
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(resultGet);
        out.print(json);
        out.flush();
        out.close();
    }
}
