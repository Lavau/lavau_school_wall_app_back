package top.leeti.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.leeti.entity.result.Result;
import top.leeti.util.FileUtil;

import java.util.List;

/**
 * description Picture controller
 **/
@Slf4j
@RestController("loginPictureController")
public class PictureController {

    @PostMapping("/app/login/picture/save")
    public String savePictures(@RequestParam Integer typeId,  @RequestParam String id,
            @RequestParam List<MultipartFile> pictures) throws Exception {
        log.info("id: {}, typeId: {}, num of pictures: {}", id, typeId, pictures.size());

        for (MultipartFile picture : pictures) {
            picture.transferTo(FileUtil.createFile(typeId, id, picture.getOriginalFilename()));
        }

        Result<Boolean> result = new Result<>(null, "图片保存成功", true, null);

        return JSON.toJSONString(result);
    }
}
