package top.lavau.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String dealWithException() {
        return JSON.toJSONString("服务器繁忙！！");
    }
}
