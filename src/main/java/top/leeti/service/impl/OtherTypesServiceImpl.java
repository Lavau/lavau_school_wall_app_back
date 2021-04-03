package top.leeti.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.controller.login.OtherTypesController.Form;
import top.leeti.dao.LikeMapper;
import top.leeti.dao.LostAndFoundMapper;
import top.leeti.dao.MixedDataMapper;
import top.leeti.dao.SingleMapper;
import top.leeti.entity.LostAndFound;
import top.leeti.entity.MixedData;
import top.leeti.entity.Single;
import top.leeti.entity.User;
import top.leeti.myenum.TypeEnum;
import top.leeti.service.OtherTypesService;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class OtherTypesServiceImpl implements OtherTypesService {

    @Resource
    private LostAndFoundMapper lostAndFoundMapper;

    @Resource
    private MixedDataMapper mixedDataMapper;
    
    @Resource
    private SingleMapper singleMapper;

    @Resource
    private LikeMapper likeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertTypeData(Form form) {
        insertMixedData(form);

        if (form.getTypeId().equals(TypeEnum.LOST_AND_FOUND.getTypeId())) {
            insertLostAndFound(form);
        }

        if (form.getTypeId().equals(TypeEnum.SINGLE.getTypeId())) {
            insertSingle(form);
        }
    }

    private void insertSingle(Form form) {
        Single single = new Single();
        single.setId(form.getId());
        single.setHeight(form.getHeight());
        single.setWeight(form.getWeight());
        single.setSpeciality(form.getSpeciality());
        single.setInterest(form.getInterest());
        singleMapper.insertSingle(single);
    }

    private void insertMixedData(Form form) {
        MixedData mixedData = new MixedData();
        mixedData.setId(form.getId());
        mixedData.setPromulgatorId(User.obtainCurrentUser().getStuId());
        mixedData.setTypeId(form.getTypeId());
        mixedData.setTitle(form.getTitle());
        mixedData.setDescription(form.getDescription());
        mixedData.setAnonymous(form.getAnonymous());
        mixedData.setGmtCreate(new Date());
        mixedData.setPictureNum(form.getPictureNum());
        mixedDataMapper.insertMixedData(mixedData);
    }

    private void insertLostAndFound(Form form) {
        LostAndFound lostAndFound = new LostAndFound();
        lostAndFound.setId(form.getId());
        lostAndFound.setMsg(form.getMsg());
        lostAndFoundMapper.insertLostAndFound(lostAndFound);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MixedData getMixedDataByIdAndTypeId(String id, Integer typeId) {
        String stuId = User.obtainCurrentUser().getStuId();
        MixedData mixedData = null;

        boolean flag = typeId.equals(TypeEnum.PERSONAL_PUBLICITY.getTypeId()) ||
                typeId.equals(TypeEnum.THANK_OR_RIDICULE.getTypeId()) ||
                typeId.equals(TypeEnum.SEEK_HELP.getTypeId());
        if (flag) {
            mixedData = mixedDataMapper.getMixedDataById(id);
            mixedData.setLike(likeMapper.getLikeByIdAndStuId(id, stuId) != null);
        }

        if (typeId.equals(TypeEnum.LOST_AND_FOUND.getTypeId())) {
            mixedData = lostAndFoundMapper.getLostAndFoundById(id);
            mixedData.setLike(likeMapper.getLikeByIdAndStuId(id, stuId) != null);
        }

        if (typeId.equals(TypeEnum.SINGLE.getTypeId())) {
            mixedData = singleMapper.getSingleById(id);
            mixedData.setLike(likeMapper.getLikeByIdAndStuId(id, stuId) != null);
        }

        return mixedData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MixedData getMixedDataById(String id) {
        return mixedDataMapper.getMixedDataById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMixedData(MixedData mixedData) {
        mixedDataMapper.updateMixedData(mixedData);

        if (mixedData.getTypeId().equals(TypeEnum.LOST_AND_FOUND.getTypeId())) {
            lostAndFoundMapper.updateLostAndFound((LostAndFound) mixedData);
        }
    }
}