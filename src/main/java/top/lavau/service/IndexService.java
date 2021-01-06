package top.lavau.service;

import com.github.pagehelper.PageInfo;
import top.lavau.entity.MixedData;

/**
 * @author Leet
 * create: 2020/11/7 12:07
 */
public interface IndexService {

    /**
     * 获取首页显示的数据
     * @param pageNum 第几页的数据
     * @return List<MixedDataModel>
     */
    PageInfo<MixedData> listIndexData(int pageNum);
}
