package top.lavau.service;

import com.github.pagehelper.PageInfo;
import top.lavau.model.MixedDataModel;

/**
 * @author Leet
 * create: 2020/11/7 12:07
 */
public interface IndexService {

    /**
     * 获取小程序首页显示的数据
     * @param pageNum 第几页的数据
     * @return List<MixedDataModel>
     */
    PageInfo<MixedDataModel> listMiniprogramIndexData(int pageNum);
}
