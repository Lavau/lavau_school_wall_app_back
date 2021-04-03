package top.leeti.service;

import com.github.pagehelper.PageInfo;
import top.leeti.entity.MixedData;

public interface IndexService {

    PageInfo<MixedData> listIndexData(int pageNum);
}
