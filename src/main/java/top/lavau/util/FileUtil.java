package top.lavau.util;

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
     * true 为 Windows，false 为 Linux
     */
    private static boolean os;

    /**
     * @Value 注解若要给静态变量赋值，可以使用set()方法，其中需要在类上加入@Component注解
     * @param os os
     */
    @Value("${os.is-windows}")
    private void setOs(boolean os){
        FileUtil.os = os;
    }

    /**
     * 根据系统的生成不同的路径
     * @return file path
     */
    public static String getRootDirectory(String typeId, String uuid){
        if(os){
            return String.format("%s\\%s\\%s", "F:\\schoolWall", typeId, uuid);
        } else {
            return String.format("%s/%s/%s", "/root/schoolWall", typeId, uuid);
        }
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


    /**
     * 获取图片地址
     * @param typeId 分类号
     * @param uuid 记录的id
     * @return ******
     */
    public static List<String> obtainPictureNameList(String typeId, String uuid){
        List<String> pictureNameList = FileUtil.findAllPictureNames(typeId, uuid);
        List<String> pictureUrlList = new ArrayList<>();
        for(String pictureName : pictureNameList){
            pictureUrlList.add(String.format("/admin/picture?typeId=%s&uuid=%s&fileName=%s",
                    typeId, uuid, pictureName));
        }
        return pictureUrlList;
    }

    /**
     * 根据 typeId、id、type（设备类型）获取图片地址
     * @param typeId typeId
     * @param uuid uuid
     * @param type 设备类型
     * @return List<String>
     */
    public static List<String> obtainPictureNameList(String typeId, String uuid, String type){
        List<String> pictureNameList = FileUtil.findAllPictureNames(typeId, uuid);
        List<String> pictureUrlList = new ArrayList<>();
        for(String pictureName : pictureNameList){
            if (os) {
                pictureUrlList.add(String.format("http://127.0.0.1:8080/%s/picture?typeId=%s&uuid=%s&fileName=%s",
                    type, typeId, uuid, pictureName));
            } else {
                pictureUrlList.add(String.format("https://schoolwall.imwonder.top/%s/picture?typeId=%s&uuid=%s&fileName=%s",
                    type, typeId, uuid, pictureName));
            }
        }
        return pictureUrlList;
    }
}
