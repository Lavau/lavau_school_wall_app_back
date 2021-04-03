package top.leeti.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.leeti.entity.Ecard;
import top.leeti.entity.User;
import top.leeti.entity.result.Result;
import top.leeti.exception.RecordOfDataBaseNoFoundException;
import top.leeti.service.EcardService;
import top.leeti.util.UuidUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
public class EcardController {

    @Resource
    private EcardService ecardService;

    @PostMapping("/miniprogram/login/ecard/publish")
    public String addEcardInfoToDatabase(@Valid Ecard ecard, BindingResult bindingResult){
        Result<Boolean> result = new Result<>();

        if (bindingResult.hasErrors()) {
            result.setData(false);
            result.setMsg("填写的信息有误");
            return JSON.toJSONString(result);
        }

        completeEcardInfo(ecard);

        ecardService.insertEcard(ecard);

        log.info("ecard data of inserted database: {}", ecard.toString());

        result.setData(false);
        result.setMsg("替 Ta 谢谢你！(*^_^*)");
        return JSON.toJSONString(result);
    }

    private void completeEcardInfo(Ecard ecard) {
        ecard.setId(UuidUtil.acquireUuid());
        ecard.setPromulgatorId(User.obtainCurrentUser().getStuId());
        ecard.setGmtCreate(new Date());
    }

    @PostMapping("/miniprogram/login/ecard/detail")
    public String obtainDetailInfoOfEcard(@RequestParam String id) {
        Ecard ecard = ecardService.getEcardById(id);
        if (ecard == null) {
            throw new RecordOfDataBaseNoFoundException("id: " + id + ", 该一卡通信息在数据库中不存在");
        } else {
            Result<Ecard> result = new Result<>();
            if (ecard.getGmtClaim() == null) {
                result.setData(ecard);
                result.setSuccess(true);
            } else {
                result.setSuccess(false);
                result.setMsg("该一卡通已被认领！如有疑问，请联系管理员");
            }
            return JSON.toJSONString(result);
        }
    }

    @PostMapping("/miniprogram/login/ecard/claim")
    public String claimEcard(@RequestParam String id) {
        Ecard ecard = ecardService.getEcardById(id);
        if (ecard == null) {
            throw new RecordOfDataBaseNoFoundException("id: " + id + ", 该一卡通信息在数据库中不存在");
        } else {
            Result<String> result = new Result<>();
            if (ecard.getGmtClaim() == null) {
                ecardService.claim(id);
                result.setSuccess(true);
            } else {
                result.setSuccess(false);
                result.setMsg("该一卡通已被认领！如有疑问，请联系管理员");
            }
            return JSON.toJSONString(result);
        }
    }

}
