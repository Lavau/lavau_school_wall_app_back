package top.leeti.controller.nologin;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import top.leeti.entity.result.Result;
import top.leeti.myenum.ResultCodeEnum;
import top.leeti.util.WechatUtil;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/miniprogram/noLogin/")
public class LoginController {
    
    @PostMapping("login")
    public String login(@RequestParam String code) {
        Map<String, String> map = WechatUtil.acquireSessionKeyAndOpenId(code);
        String openId = map.get("openId");

        Result<String> result = null;

        if (openId == null) {
            result = new Result<>(null, "获取openId失败", null, false);
        } else {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(openId, "");
            Subject currentUser = SecurityUtils.getSubject();
            try{
                currentUser.login(usernamePasswordToken);
                result = new Result<>(null, null, "registered", true);
            } catch(AccountException accountException) {
                result = new Result<>(null, null, "unregistered", false);
                return JSON.toJSONString(result);
            }
        }

        return JSON.toJSONString(result);
    }

    @GetMapping("/error")
    public String loginError() {
        Result<Object> result = new Result<>(ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getCode(),
                ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getExplanation(), null, null);
        return JSON.toJSONString(result);
    }

    @GetMapping("/noAccess")
    public String noAccessVisit() {
        Result<Object> result = new Result<>(ResultCodeEnum.NO_ACCESS.getCode(),
                ResultCodeEnum.NO_ACCESS.getExplanation(), null, null);
        return JSON.toJSONString(result);
    }

}
