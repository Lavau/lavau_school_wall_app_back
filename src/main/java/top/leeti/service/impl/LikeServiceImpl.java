package top.leeti.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.dao.LikeMapper;
import top.leeti.dao.MixedDataMapper;
import top.leeti.entity.Like;
import top.leeti.entity.MixedData;
import top.leeti.entity.User;
import top.leeti.service.LikeService;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    @Resource
    private MixedDataMapper mixedDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void like(Boolean isLike, String id) {
        String stuId = User.obtainCurrentUser().getStuId();

        if (isLike) {
            like(id, stuId);
        } else {
            cancelLike(id, stuId);
        }
    }

    private void cancelLike(String id, String stuId) {
        likeMapper.deleteLikeByIdAndStuId(id, stuId);

        MixedData mixedData = mixedDataMapper.getMixedDataById(id);
        mixedData.setLikeNum(mixedData.getLikeNum() - 1);
        mixedDataMapper.updateMixedData(mixedData);
    }

    private void like(String id, String stuId) {
        Like like = new Like(id, stuId, new Date());
        likeMapper.insertLike(like);

        MixedData mixedData = mixedDataMapper.getMixedDataById(id);
        mixedData.setLikeNum(mixedData.getLikeNum() + 1);
        mixedDataMapper.updateMixedData(mixedData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer getLikeNumByStuId(String stuId) {
        return likeMapper.getLikeNumByStuId(stuId);
    }
}
