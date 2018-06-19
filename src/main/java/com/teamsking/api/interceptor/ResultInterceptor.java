package com.teamsking.api.interceptor;

import com.teamsking.util.Result;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ynfeng
 */
@ControllerAdvice
@Component
@Slf4j
public class ResultInterceptor {

    /**
     * 异常拦截器
     *
     * @param req 请求对象
     * @param e   异常
     * @return 响应对象
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result errorHandle(HttpServletRequest req, Exception e) {
//        if (log.isErrorEnabled()) {
////            log.error(logStr(req), e);
//        }
        return Result.exception(e.getMessage());
    }

    /**
     * 组装日志内容
     *
     * @param request 请求
     * @return 日志内容
     */
    private static String logStr(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        return String
            .format("API接口出错,URL = %s, HTTP_METHOD = %s", url, httpMethod);

    }
}
