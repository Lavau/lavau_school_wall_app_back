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
import top.leeti.service.EcardService;
import top.leeti.util.UuidUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * description: 一卡通：添加入库、获取详情、认领
 */
@Slf4j
@RestController
public class EcardController {

    @Resource
    private EcardService ecardService;

    @PostMapping("/app/login/ecard/publish")
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

    @GetMapping("/app/login/ecard/detail")
    public String obtainDetailInfoOfEcard(@RequestParam String id) {
        Ecard ecard = ecardService.getEcardById(id);
        Result<Ecard> result = new Result<>();

        if (ecard.getGmtClaim() == null) {
            log.info("ecard:{}", ecard.toString());
            result.setData(ecard);
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return JSON.toJSONString(result);
    }

    @GetMapping("/app/login/ecard/claim")
    public String claimEcard(@RequestParam String id) {
        Ecard ecard = ecardService.getEcardById(id);
        Result<Ecard> result = new Result<>();

        if (ecard.getGmtClaim() == null) {
            result.setSuccess(true);

            ecardService.claim(id);
        } else {
            result.setSuccess(false);
        }

        return JSON.toJSONString(result);
    }

//    /**
//     * /miniprogram/login/ecard/claim（POST）
//     * 认领 ecard
//     * @param openId openId
//     * @param id id
//     * @return String
//     */
//    @PostMapping("/claim")
//    public String claimEcard(@RequestParam String openId, @RequestParam String id) {
//        openId = PasswordUtil.decrypt(openId);
//        Result result = new Result();
//
//        // 判断该一卡通是否已经被认领
//        if (ecardService.isClaim(id)) {
//            result.setSuccess(false);
//            result.setMsg("该一卡通已经被认领");
//            return JSON.toJSONString(result);
//        }
//
//        // 更新操作
//        Ecard ecardModel =  ecardService.getEcardById(id);
//        ecardModel.setClaimantId(userService.findUserByOpenId(openId).getStuId());
//        ecardModel.setGmtClaim(new Date());
//        ecardService.updateEcard(ecardModel);
//
//        // 结果返回
//        result.setSuccess(true);
//        return JSON.toJSONString(result);
//    }
//
//    /**
//     * /miniprogram/login/ecard/search（POST）
//     * 查询：依据 stuId 获取”可用“的一卡通
//     * @param openId openId
//     * @param query query
//     * @return String
//     */
//    @PostMapping("/search")
//    public String searchEcard(@RequestParam String openId, @RequestParam String query) {
//        openId = PasswordUtil.decrypt(openId);
//        Result result = new Result();
//
//        if (query.length() == 9) {
//            Ecard ecardModel = ecardService.getEcardByStuId(query);
//
//            if (ecardModel == null) {
//                result.setSuccess(false);
//                result.setMsg("暂无信息");
//            } else {
//                result.setObject(ecardModel);
//                result.setSuccess(true);
//            }
//        } else {
//            result.setSuccess(false);
//            result.setMsg("错误的学号");
//        }
//
//        return JSON.toJSONString(result);
//    }
//

}
