package xyz.ieden.mybatis.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.ieden.mybatis.entity.Result;
import xyz.ieden.mybatis.exception.EdenException;
import xyz.ieden.mybatis.util.ResultUtils;

/**
 * @author ThinkPad
 * @date Created by 2018/4/20 18:42
 */
@RestControllerAdvice
public class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 自定义异常
     */
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(EdenException.class)
    public Result<Object> handleRRException(EdenException e) {
        Result<Object> result = ResultUtils.createResult();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        Result<Object> result = ResultUtils.error();
        return result;
    }
}