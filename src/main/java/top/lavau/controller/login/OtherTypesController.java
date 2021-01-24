package top.lavau.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.result.Result;
import top.lavau.service.OtherTypesService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * description
 *
 * @author Leet
 **/
@Slf4j
@RestController
public class OtherTypesController {

    @Resource
    private OtherTypesService otherTypesService;

    @PostMapping("/app/login/others/publish")
    public String addTypeDataInfoToDatabase(@Valid Form form, BindingResult bindingResult) {
        Result<Object> result = new Result<>();

        if (bindingResult.hasErrors()) {
            result.setData(false);
            result.setMsg("填写的信息有误");
            return JSON.toJSONString(result);
        }

        otherTypesService.insertTypeData(form);

        log.info("data of inserted database: {}", form.toString());

        result.setData(true);
        result.setMsg("发布成功");
        return JSON.toJSONString(result);
    }


    @Data
    public static class Form {
        @Min(value = 1, message = "图片数量不符合规范")
        @Max(value = 7, message = "图片数量不符合规范")
        Integer typeId;

        String title;
        Double height;
        Double weight;
        String speciality;
        String interest;
        String description;
        String msg;
        Boolean anonymous;

        @Min(value = 0, message = "图片数量不符合规范")
        @Max(value = 3, message = "图片数量不符合规范")
        Integer pictureNum;
    }
}
