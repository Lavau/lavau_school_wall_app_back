package top.lavau.controller.nologin;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import top.lavau.entity.result.Result;
import top.lavau.myenum.ResultCodeEnum;

/**
 * description 登录
 *
 * @author Leet
 * @date 2021-01-03 17:24
 **/
@Slf4j
@RestController
@RequestMapping("/app/noLogin")
public class LoginController {

    /**
     * /app/noLogin/login
     * 通过 "学号 + 密码" 登录
     * @param stuId stuId
     * @param password password
     * @return String
     */
    @PostMapping("/login")
    public String login(@RequestParam String stuId, @RequestParam String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(stuId, password);
        Subject currentUser = SecurityUtils.getSubject();
        // isAuthenticated() 判断 currentUser 是否已经被认证
        if(!currentUser.isAuthenticated()){
            try{
                currentUser.login(usernamePasswordToken);
            } catch(AccountException accountException){
                return "redirect:/app/noLogin/error";
            }
        }
        Result<Object> result = new Result<>(ResultCodeEnum.LOGIN_SUCCESSFULLY.getCode(),
                ResultCodeEnum.LOGIN_SUCCESSFULLY.getExplanation(), null);
        return JSON.toJSONString(result);
    }

    /**
     * /app/noLogin/error
     * 登录失败
     * @return String
     */
    @GetMapping("/error")
    public String loginError() {
        Result<Object> result = new Result<>(ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getCode(),
                ResultCodeEnum.LOGIN_UNSUCCESSFULLY.getExplanation(), null);
        return JSON.toJSONString(result);
    }

    /**
     * /app/noLogin/noAccess
     * 无权访问
     * @return String
     */
    @GetMapping("/noAccess")
    public String noAccess() {
        Result<Object> result = new Result<>(ResultCodeEnum.NO_ACCESS.getCode(),
                ResultCodeEnum.NO_ACCESS.getExplanation(),null);
        return JSON.toJSONString(result);
    }

}
