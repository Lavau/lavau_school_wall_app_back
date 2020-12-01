package top.lavau.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.miniprogram.controller.login.OtherTypesController.PostForm;
import top.lavau.miniprogram.mapper.type.others.LostAndFoundMapper;
import top.lavau.miniprogram.mapper.type.others.MixedDataMapper;
import top.lavau.miniprogram.mapper.type.others.SingleMapper;
import top.lavau.miniprogram.service.OtherTypesService;
import top.lavau.miniprogram.service.UserService;
import top.lavau.model.LostAndFoundModel;
import top.lavau.model.MixedDataModel;
import top.lavau.model.SingleModel;
import top.lavau.model.UserModel;
import top.lavau.util.FileUtil;
import top.lavau.util.TimeStampUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * description: OtherTypesService 的实现类
 * @author: Leet
 * create: 2020-11-08 13:59
 **/
@Slf4j
@Service
public class OtherTypesServiceImpl implements OtherTypesService {

    @Value("${page.size}")
    private int pageSize;

    @Autowired
    private MixedDataMapper mixedDataMapper;

    @Autowired
    private LostAndFoundMapper lostAndFoundMapper;

    @Autowired
    private SingleMapper singleMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<MixedDataModel> listDataByTypeId(String typeId, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<MixedDataModel> list = mixedDataMapper.listDataByTypeId(typeId);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDataById(String typeId, String id) {
        if ("4".equals(typeId)) {
            lostAndFoundMapper.deleteLostAndFoundById(id);
        } else if ("6".equals(typeId)) {
            singleMapper.deleteSingleById(id);
        }
        if ("1234567".contains(typeId)) {
            mixedDataMapper.deleteMixedDataById(id);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateData(MixedDataModel mixedDataModel) {
        mixedDataMapper.updateMixedData(mixedDataModel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void like(String id, String openId, boolean isLike) {
        MixedDataModel mixedDataModel = mixedDataMapper.getMixedDataById(id);

        if (isLike) {
            mixedDataModel.setLikeNum(mixedDataModel.getLikeNum() + 1);
            mixedDataMapper.insertLike(id, getStuId(openId));
        } else {
            mixedDataModel.setLikeNum(mixedDataModel.getLikeNum() - 1);
            mixedDataMapper.deleteLike(id, getStuId(openId));
        }

        mixedDataMapper.updateMixedData(mixedDataModel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void claimThing(LostAndFoundModel lostAndFoundModel, String openId) {
        lostAndFoundModel.setAvailable(false);
        mixedDataMapper.updateMixedData(lostAndFoundModel);

        lostAndFoundModel.setClaimantId(getStuId(openId));
        lostAndFoundModel.setGmtClaim(new Date());
        lostAndFoundMapper.updateLostAndFound(lostAndFoundModel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MixedDataModel getTypeEntityByTypeIdAndId(String typeId, String id, String openId) {
        MixedDataModel mixedDataModel;

        if ("4".equals(typeId)) {
            mixedDataModel = lostAndFoundMapper.getLostAndFoundById(id);
        } else if ("6".equals(typeId)) {
            mixedDataModel =  singleMapper.getSingleById(id);
        } else if (Arrays.asList("1", "2", "3", "5").contains(typeId)) {
            mixedDataModel =  mixedDataMapper.getMixedDataById(id);
        } else {
            return null;
        }

        if (mixedDataModel == null) {
            return mixedDataModel;
        }

        if (mixedDataModel.getPictureNum() > 0) {
            mixedDataModel.setPictureUrlList(FileUtil.obtainPictureNameList(typeId, id, "miniprogram"));
        }
        mixedDataModel.setCreateTime(TimeStampUtil.timeStamp(mixedDataModel.getGmtCreate()));
        mixedDataModel.setLike(mixedDataMapper.isLike(id, getStuId(openId)));

        // 更新浏览量
        mixedDataModel.setViewNum(mixedDataModel.getViewNum() + 1);
        mixedDataMapper.updateMixedData(mixedDataModel);

        return mixedDataModel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOthers(PostForm form, String openId) {

        switch (form.getTypeId()) {
            case "4":
                LostAndFoundModel lostAndFoundModel = new LostAndFoundModel();
                lostAndFoundModel.setId(form.getId());
                lostAndFoundModel.setMsg(form.getMsg());
                lostAndFoundMapper.insertLostAndFound(lostAndFoundModel);
                break;

            case "6":
                SingleModel singleModel = new SingleModel();
                singleModel.setId(form.getId());
                singleModel.setHeight(form.getHeight());
                singleModel.setWeight(form.getWeight());
                singleModel.setSpeciality(form.getSpeciality());
                singleModel.setInterest(form.getInterest());
                singleMapper.insertSingle(singleModel);
                break;
            default: break;
        }

        // 混合表添加
        MixedDataModel mixedDataModel = new MixedDataModel();
        mixedDataModel.setId(form.getId());
        mixedDataModel.setPromulgatorId(getStuId(openId));
        mixedDataModel.setTypeId(Integer.valueOf(form.getTypeId()));
        mixedDataModel.setTitle(form.getTitle());
        mixedDataModel.setDescription(form.getDescription());
        mixedDataModel.setAnonymous(form.getAnonymous());
        mixedDataModel.setGmtCreate(new Date());
        mixedDataModel.setPictureNum(form.getPictureNum());
        mixedDataMapper.insertMixedData(mixedDataModel);
    }

    /**
     * 获取学号
     * @param openId openId
     * @return 如果查询不到，返回 “0”
     */
    private String getStuId(String openId) {
        UserModel user = userService.findUserByOpenId(openId);
        if (user == null) {
            return "0";
        } else {
            return user.getStuId();
        }
    }
}