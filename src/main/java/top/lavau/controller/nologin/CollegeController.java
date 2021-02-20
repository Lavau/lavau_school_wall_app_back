package top.lavau.controller.nologin;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.College;
import top.lavau.entity.result.Result;
import top.lavau.myenum.ResultCodeEnum;
import top.lavau.service.CollegeService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    @GetMapping("/app/noLogin/college/list")
    public String listCollege() {
        Result<List<College>> result = new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getExplanation(), collegeService.listCollege(), null);
        return JSON.toJSONString(result);
    }
}
