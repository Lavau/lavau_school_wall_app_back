package top.lavau.util;

import jdk.nashorn.internal.runtime.StoredScript;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.lavau.exception.MkdirCreateException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leet
 * @date 2020/12/7 12：49
 */
@Component
public class FileUtil {

    /**
     * 生成不同的路径
     */
    public static String getRootDirectory(String typeId, String uuid){
        return new StringBuilder("F:\\schoolWall\\").append(typeId).append("\\").append(uuid).toString();
    }

    /**
     * 处理上传的图片
     * @param typeId 分类
     * @param uuid 图片所属的记录的 uuid
     * @param originalFilename 原文件名
     * @return 返回生成的文件
     */
    public static File createFile(String typeId, String uuid, String originalFilename) throws MkdirCreateException {
        String path = getRootDirectory(typeId, uuid);
        File file = new File(path, originalFilename);
        if(!file.exists()){
            if (!file.mkdirs()) {
                throw new MkdirCreateException();
            }
        }
        return file;
    }

    /**
     * 查找某一目录下所有图片的名字
     * @param typeId 分类号
     * @param uuid 记录的id
     * @return 所有图片的名字的字符串列表
     */
    public static List<String> findAllPictureNames(String typeId, String uuid) {
        String path = getRootDirectory(typeId, uuid);
        List<String> fileNameList = new ArrayList<>();
        File f = new File(path);
        getFile(f, fileNameList);
        return fileNameList;
    }

    private static void getFile(File file, List<String> fileNameList){
        if(file != null){
            File[] files = file.listFiles();
            if(files != null){
                for(File f : files){
                    getFile(f, fileNameList);
                }
            }else{
                fileNameList.add(file.getName());
            }
        }
    }

    public static void main(String[] args) {
        findAllPictureNames("7", "058579a6feec26a4d5ca1811b85676c7");
    }

    public static List<String> obtainListOfPictureUrl(String typeId, String uuid){
        List<String> pictureNameList = FileUtil.findAllPictureNames(typeId, uuid);
        List<String> pictureUrlList = new ArrayList<>();
        for(String pictureName : pictureNameList){
            pictureUrlList.add(new StringBuilder("http://192.168.1.105:8080/app/picture/obtain?typeId=").append(typeId).
                    append("&uuid=").append(uuid).append("&fileName=").append(pictureName).toString());
        }
        return pictureUrlList;
    }
}
