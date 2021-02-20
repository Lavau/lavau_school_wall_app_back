package top.lavau.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.lavau.entity.result.Result;
import top.lavau.service.OtherTypesService;
import top.lavau.util.FileUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * description
 **/
@Slf4j
@RestController
public class OtherTypesController {

    @Resource
    private OtherTypesService otherTypesService;

    @PostMapping("/app/login/others/publish")
    public String addTypeDataInfoToDatabase(@Valid Form form, BindingResult bindingResult,
                        @RequestParam List<MultipartFile> pictures) throws Exception {
        Result<Object> result = new Result<>();

        if (bindingResult.hasErrors()) {
            result.setData(false);
            result.setMsg("填写的信息有误");
            return JSON.toJSONString(result);
        }

        log.info("data of inserted database: {}", form.toString());
        otherTypesService.insertTypeData(form);
        for (MultipartFile picture : pictures) {
            picture.transferTo(FileUtil.createFile(form.typeId, form.getId(), picture.getOriginalFilename()));
        }

        result.setData(true);
        result.setMsg("发布成功");
        return JSON.toJSONString(result);
    }


    @Data
    public static class Form {
        @NotBlank
        @NotEmpty
        @NotNull
        private String id;

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
