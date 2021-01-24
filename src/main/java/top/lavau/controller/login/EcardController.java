package top.lavau.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.Ecard;
import top.lavau.entity.User;
import top.lavau.entity.result.Result;
import top.lavau.service.EcardService;
import top.lavau.util.UuidUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * description: 一卡通：添加入库、获取详情、认领
 * @author Leet
 */
@Slf4j
@RestController
public class EcardController {

    @Resource
    private EcardService ecardService;

    @PostMapping("/app/login/ecard/publish")
    public String addEcardInfoToDatabase(@Valid Ecard ecard, BindingResult bindingResult){
        Result<Object> result = new Result<>();

        if (bindingResult.hasErrors()) {
            result.setData(false);
            result.setMsg("填写的信息有误");
            return JSON.toJSONString(result);
        }

        completeEcardInfo(ecard);
        ecardService.insertEcard(ecard);

        log.info("ecard data of inserted database: {}", ecard.toString());

        result.setMsg("替 Ta 谢谢你！(*^_^*)");
        result.setData(true);
        return JSON.toJSONString(result);
    }

    private void completeEcardInfo(Ecard ecard) {
        ecard.setId(UuidUtil.acquireUuid());
        ecard.setPromulgatorId(User.obtainCurrentUser().getStuId());
        ecard.setGmtCreate(new Date());
    }

//    /**
//     * /miniprogram/login/ecard/detail（POST）
//     * 获取 ecard 详情
//     * @param openId openId
//     * @param id id
//     * @return String
//     */
//    @PostMapping("/detail")
//    public String detailEcard(@RequestParam String openId, @RequestParam String id) {
//        openId = PasswordUtil.decrypt(openId);
//        Result result = new Result();
//
//        if (ecardService.isClaim(id)) {
//            result.setSuccess(false);
//            result.setMsg("该一卡通已被认领");
//        } else {
//            result.setObject(ecardService.getEcardById(id));
//            result.setSuccess(true);
//        }
//
//        return JSON.toJSONString(result);
//    }
//
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
