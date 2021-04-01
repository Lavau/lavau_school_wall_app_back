package top.leeti.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String dealWithException(Exception e) {
        e.printStackTrace();
        return JSON.toJSONString("服务器繁忙！！");
    }
}
