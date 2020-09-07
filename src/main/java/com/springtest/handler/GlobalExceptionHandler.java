package com.springtest.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理类
 *
 * @author xianghaochen
 * @date 2020/8/27 0:51
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一异常处理，应该针对不同的Service业务逻辑编写不同的自定义异常处理类，而且是自定义的异常类，指定异常处理类
     * 将value设置为自定义异常类，针对抛出该异常类的事务进行异常处理，并且可以写打印写入一些日志
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private Map<String, Object> exceptionHandler(HttpServletRequest request, Exception e) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", false);
        modelMap.put("errMsg", e.getMessage());
        return modelMap;
    }
}
