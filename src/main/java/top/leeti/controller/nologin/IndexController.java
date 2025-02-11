package top.leeti.controller.nologin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.leeti.entity.MixedData;
import top.leeti.entity.result.Result;
import top.leeti.myenum.ResultCodeEnum;
import top.leeti.service.IndexService;
import top.leeti.util.FileUtil;
import top.leeti.util.TimeStampUtil;

import java.util.ArrayList;

@Slf4j
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/miniprogram/noLogin/index")
    public String obtainIndexPageData(@RequestParam(defaultValue = "1") int pageNum) {
        PageInfo<MixedData> pageInfo = indexService.listIndexData(pageNum);

        // 获取图片地址
        pageInfo.getList().forEach(mixedDataModel -> {
            if (new Integer(0).equals(mixedDataModel.getPictureNum())) {
                mixedDataModel.setPictureUrlList(new ArrayList<>(0));
            } else {
                mixedDataModel.setPictureUrlList(FileUtil.obtainListOfPictureUrl(
                        mixedDataModel.getTypeId(), mixedDataModel.getId()));
            }
            mixedDataModel.setCreateTime(TimeStampUtil.timeStamp(mixedDataModel.getGmtCreate()));
        });

        Result.MyPage<MixedData> page = new Result.MyPage<>(pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getList());
        Result<Result.MyPage<MixedData>> result = new Result<>(null, null, page, null);
        return JSON.toJSONString(result);
    }

}
