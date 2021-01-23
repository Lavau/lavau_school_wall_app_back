package top.lavau.controller.nologin;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.User;
import top.lavau.entity.result.Result;
import top.lavau.myenum.ResultCodeEnum;

/**
 * description 登录
 *
 * @author Leet
 **/
@Slf4j
@RestController
public class LoginController {

    /**
     * 通过 "学号 + 密码" 登录
     */
    @PostMapping("/app/noLogin/login")
    public String login(@RequestParam String stuId, @RequestParam String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(stuId, password);
        Subject currentUser = SecurityUtils.getSubject();
        // isAuthenticated() 判断 currentUser 是否已经被认证
        if(!currentUser.isAuthenticated()){
            try{
                currentUser.login(usernamePasswordToken);
            } catch(AccountException accountException){
                Result<Object> result = new Result<>(ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getCode(),
                        ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getExplanation(), null);
                return JSON.toJSONString(result);
            }
        }
        Result<String> result = new Result<>(ResultCodeEnum.LOGIN_SUCCESSFULLY.getCode(),
                ResultCodeEnum.LOGIN_SUCCESSFULLY.getExplanation(), "登录成功");
        return JSON.toJSONString(result);
    }

    @GetMapping("/app/noLogin/error")
    public String loginError() {
        Result<Object> result = new Result<>(ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getCode(),
                ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getExplanation(), null);
        return JSON.toJSONString(result);
    }

    @GetMapping("/app/noLogin/noAccess")
    public String noAccessVisit() {
        Result<Object> result = new Result<>(ResultCodeEnum.NO_ACCESS.getCode(),
                ResultCodeEnum.NO_ACCESS.getExplanation(),null);
        return JSON.toJSONString(result);
    }

    @GetMapping("/app/noLogin/isLogin")
    public String obtainLoginStatusOfUser() {
        Subject subject = SecurityUtils.getSubject();
        User testUser = (User)subject.getPrincipal();

        Result<Boolean> result;

        if (testUser == null) {
            result = new Result<>(null, "服务器端不存在登录信息", false);
        } else {
            result = new Result<>(null, "服务器端存在登录信息", true);
        }

        return JSON.toJSONString(result);
    }

}
