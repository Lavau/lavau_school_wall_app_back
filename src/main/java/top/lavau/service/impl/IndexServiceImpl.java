package top.lavau.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.dao.IndexMapper;
import top.lavau.entity.MixedData;
import top.lavau.service.IndexService;

import javax.annotation.Resource;
import java.util.List;

/**
 * program: schoolWall
 * @author: Leet
 * create: 2020-10-13 20:42
 **/
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
