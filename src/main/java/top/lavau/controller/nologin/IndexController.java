package top.lavau.controller.nologin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.MixedData;
import top.lavau.entity.result.Result;
import top.lavau.myenum.ResultCodeEnum;
import top.lavau.service.IndexService;
import top.lavau.util.FileUtil;
import top.lavau.util.TimeStampUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * description app 首页数据提供
 *
 * @author Leet
 * @date 2021-01-04 10:32
 **/
@Slf4j
@RestController
@RequestMapping("/app/noLogin/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * /app/noLogin/index
     * app 首页数据提供
     * @param pageNum pageNum
     * @return String
     */
    @GetMapping
    public String index(@RequestParam(defaultValue = "1") int pageNum) {
        PageInfo<MixedData> pageInfo = indexService.listIndexData(pageNum);
        // 获取图片地址
        pageInfo.getList().forEach(mixedDataModel -> {
            if (new Integer(0).equals(mixedDataModel.getPictureNum())) {
                mixedDataModel.setPictureUrlList(new ArrayList<>(0));
            } else {
                mixedDataModel.setPictureUrlList(FileUtil.obtainPictureNameList(
                        mixedDataModel.getTypeId().toString(), mixedDataModel.getId());
            }
        });
        Result.MyPage<MixedData> page = new Result.MyPage<>(pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getList());
        Result<Result.MyPage<MixedData>> result = new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getExplanation(), page);
        return JSON.toJSONString(result);
    }

}
