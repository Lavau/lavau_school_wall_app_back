//package top.lavau.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import top.lavau.miniprogram.mapper.IndexMapper;
//import top.lavau.miniprogram.service.IndexService;
//import top.lavau.model.MixedDataModel;
//import top.lavau.service.IndexService;
//
//import java.util.List;
//
///**
// * program: schoolWall
// * @author: Leet
// * create: 2020-10-13 20:42
// **/
//@Service
//public class IndexServiceImpl implements IndexService {
//
//    @Value("${page.size}")
//    private int pageSize;
//
//    @Autowired
//    private IndexMapper indexMapper;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public PageInfo<MixedDataModel> listMiniprogramIndexData(int pageNum) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<MixedDataModel> list = indexMapper.listMiniprogramIndexData();
//        return new PageInfo<>(list);
//    }
//}
