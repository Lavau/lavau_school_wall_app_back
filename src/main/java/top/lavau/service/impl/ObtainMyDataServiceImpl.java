package top.lavau.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.miniprogram.mapper.UserMapper;
import top.lavau.miniprogram.mapper.type.others.ObtainMyDataMapper;
import top.lavau.miniprogram.service.ObtainMyDataService;
import top.lavau.model.MixedDataModel;
import top.lavau.model.UserModel;

import java.util.List;

/**
 * description: 处理获取“我”发布过的、点赞的、评论的数据的业务层的实现类
 * @author: Leet
 * create: 2020-11-06 21:38
 **/
@Slf4j
@Service
public class ObtainMyDataServiceImpl implements ObtainMyDataService {

    @Value("8")
    private int pageSize;
    
    @Autowired
    private ObtainMyDataMapper obtainAndDeleteMyDataMapper;

    @Autowired
    private UserMapper userMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<MixedDataModel> listObtainPublishedData(String openId, int pageNum) {
        String stuId = getStuId(openId);
        PageHelper.startPage(pageNum, pageSize);
        List<MixedDataModel> list = obtainAndDeleteMyDataMapper.listObtainPublishedData(stuId);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<MixedDataModel> listObtainLikedData(String openId, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<MixedDataModel> list = obtainAndDeleteMyDataMapper.listObtainLikedData(getStuId(openId));
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<MixedDataModel> listObtainCommentedData(String openId, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        log.info("stuid:{}",getStuId(openId) );
        List<MixedDataModel> list = obtainAndDeleteMyDataMapper.listObtainCommentedData(getStuId(openId));
        return new PageInfo<>(list);
    }

    /**
     * 获取学号
     * @param openId openId
     * @return 如果查询不到，返回 “0”
     */
    private String getStuId(String openId) {
        UserModel user = userMapper.findUserByOpenId(openId);
        if (user == null) {
            return "0";
        } else {
            return user.getStuId();
        }
    }
}
