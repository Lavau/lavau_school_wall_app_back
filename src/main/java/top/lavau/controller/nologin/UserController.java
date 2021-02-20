package top.lavau.controller.nologin;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.User;
import top.lavau.entity.result.Result;
import top.lavau.myenum.ResultCodeEnum;
import top.lavau.service.UserService;
import top.lavau.util.PasswordUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description user controller
 *      用户注册
 **/
@Slf4j
@RestController
@RequestMapping("/app/noLogin/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * /app/noLogin/user/register
     * 用户注册
     */
    @PostMapping("/register")
    public String register(@Valid User user, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return JSON.toJSONString(new Result<>(ResultCodeEnum.VALID_ERROR.getCode(),
                    ResultCodeEnum.VALID_ERROR.getExplanation(), null, null));
        }

        String avatarUrl = "http://localhost:8080/avatar/" + user.getStuId();
        user.setAvatarUrl(avatarUrl);
        user.setGmtCreate(new Date());
        user.setEnPassword(PasswordUtil.encrypt(user.getEnPassword()));
        userService.insertUser(user);

        log.info("At {}, {} register successfully!",
                new SimpleDateFormat("yyyy-MM-dd hh:mm").format(user.getGmtCreate()), user.getStuId());

        Result<Object> result = new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getExplanation(), null, null);
        return JSON.toJSONString(result);
    }

}
