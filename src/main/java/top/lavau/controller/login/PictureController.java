package top.lavau.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.lavau.entity.result.Result;
import top.lavau.exception.MkdirCreateException;
import top.lavau.myenum.ResultCodeEnum;

import java.io.File;
import java.io.IOException;

/**
 * description Picture controller
 *      保存头像
 *
 * @author Leet
 * @date 2020-12-07 12:56
 **/
@Slf4j
@RestController
@RequestMapping("/app/login/picture")
public class PictureController {

    /**
     * /app/login/picture/avatar
     * 保存头像
     */
//    @PostMapping("/avatar")
//    public String savePictureOfAvatar(@RequestParam MultipartFile pictureFile, @RequestParam String stuId)
//            throws MkdirCreateException {
//        final String PICTURE_FILE_PATH = isWindows ?
//                "F:\\schoolWall\\avatar\\".concat(stuId) : "/root/schoolWall/avatar/".concat(stuId);
//        String fileName = pictureFile.getOriginalFilename() == null ? "avatar" : pictureFile.getOriginalFilename();
//        File file = new File(PICTURE_FILE_PATH, fileName);
//        if(!file.exists()){
//            if (!file.mkdirs()) {
//                throw new MkdirCreateException();
//            }
//        }
//
//        try {
//            pictureFile.transferTo(file);
//            return JSON.toJSONString(new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getExplanation(), null));
//        } catch (IOException ioException) {
//            log.info("url: /app/picture/avatar; stuId: {}; Exception msg: {}", stuId, ioException.getMessage());
//            Result<Object> result = new Result<>(ResultCodeEnum.PICTURE_SAVE_UNSUCCESSFULLY.getCode(),
//                    ResultCodeEnum.PICTURE_SAVE_UNSUCCESSFULLY.getExplanation(), null);
//            return JSON.toJSONString(result);
//        }
//    }
}
