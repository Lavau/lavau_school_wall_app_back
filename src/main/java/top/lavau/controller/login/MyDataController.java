package top.lavau.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.MixedData;
import top.lavau.entity.User;
import top.lavau.entity.result.Result;
import top.lavau.service.LikeService;
import top.lavau.service.ObtainMyDataService;
import top.lavau.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MyDataController {

    @Resource
    private ObtainMyDataService obtainMyDataService;

    @GetMapping("/app/login/myData/info")
    public String obtainMyInfo() {
        Result<Map<String, String>> result = new Result<>();

        Map<String, String> myInfo = new HashMap<>(2);
        User user = User.obtainCurrentUser();
        myInfo.put("avatarUrl", user.getAvatarUrl());
        myInfo.put("nickname", user.getNickname());

        result.setData(myInfo);
        result.setSuccess(true);
        return JSON.toJSONString(result);
     }

    @GetMapping("/app/login/myData")
    public String obtainMyData(@RequestParam Integer typeId) {
        Result<List<MixedData>> result = new Result<>();

        List<MixedData> list = obtainMyDataService.obtainMyDataByTypeId(typeId);

        result.setData(list);
        result.setSuccess(true);
        return JSON.toJSONString(result);
    }
}

