package top.lavau.controller.nologin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.exception.MkdirCreateException;
import top.lavau.util.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * description Picture controller
 *      保存头像
 *
 * @author Leet
 * @date 2020-12-07 12:56
 **/
@Slf4j
@RestController
@RequestMapping("/app/noLogin/picture")
public class PictureController {

    /**
     * /app/login/picture/obtain
     * 获取图片
     * @param pictureUrl pictureUrl
     * @return
     * @throws MkdirCreateException
     */
    /**
     * /miniprogram/picture（GET）
     * 向小程序传送请求的图片
     * @param typeId 分类号
     * @param uuid uuid
     * @param fileName 文件名
     * @param response 处理的响应流
     */
    @RequestMapping(value = {"/app/login/picture/obtain"})
    public void findPicture(String typeId, String uuid, String fileName, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream writer = null;
        try {
            inputStream = new FileInputStream(new File(
                    FileUtil.getRootDirectory(typeId, uuid), fileName));
            writer = response.getOutputStream();

            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                writer.write(buf, 0, len);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(inputStream != null){
                    inputStream.close();
                }
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
