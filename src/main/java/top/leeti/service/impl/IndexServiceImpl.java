package top.leeti.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.dao.IndexMapper;
import top.leeti.entity.MixedData;
import top.leeti.service.IndexService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Value("${page.size}")
    private int pageSize;

    @Resource
    private IndexMapper indexMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<MixedData> listIndexData(int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<MixedData> list = indexMapper.listIndexData();
        return new PageInfo<>(list);
    }
}
