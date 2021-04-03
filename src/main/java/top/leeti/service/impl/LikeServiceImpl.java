package top.leeti.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.dao.LikeMapper;
import top.leeti.dao.MixedDataMapper;
import top.leeti.entity.Like;
import top.leeti.entity.MixedData;
import top.leeti.entity.User;
import top.leeti.exception.RecordOfDataBaseNoFoundException;
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
    public void like(Boolean isLike, String id) throws RecordOfDataBaseNoFoundException {
        MixedData mixedData = mixedDataMapper.getMixedDataById(id);

        if (mixedData == null) {
            throw new RecordOfDataBaseNoFoundException("该记录不存在");
        }

        if (isLike) {
            like(mixedData);
        } else {
            cancelLike(mixedData);
        }
    }

    private void cancelLike(MixedData mixedData) {
        String stuId = User.obtainCurrentUser().getStuId();
        likeMapper.deleteLikeByIdAndStuId(mixedData.getId(), stuId);

        mixedData.setLikeNum(mixedData.getLikeNum() - 1);
        mixedDataMapper.updateMixedData(mixedData);
    }

    private void like(MixedData mixedData) {
        String stuId = User.obtainCurrentUser().getStuId();
        Like like = new Like(mixedData.getId(), stuId, new Date());
        likeMapper.insertLike(like);

        mixedData.setLikeNum(mixedData.getLikeNum() + 1);
        mixedDataMapper.updateMixedData(mixedData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer getLikeNumByStuId(String stuId) {
        return likeMapper.getLikeNumByStuId(stuId);
    }
}
