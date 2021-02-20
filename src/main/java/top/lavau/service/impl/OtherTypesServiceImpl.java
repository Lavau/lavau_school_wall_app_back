package top.lavau.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.controller.login.OtherTypesController.Form;
import top.lavau.dao.LostAndFoundMapper;
import top.lavau.dao.MixedDataMapper;
import top.lavau.dao.SingleMapper;
import top.lavau.entity.LostAndFound;
import top.lavau.entity.MixedData;
import top.lavau.entity.Single;
import top.lavau.entity.User;
import top.lavau.myenum.TypeEnum;
import top.lavau.service.OtherTypesService;

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
        boolean flag = typeId.equals(TypeEnum.PERSONAL_PUBLICITY.getTypeId()) ||
                typeId.equals(TypeEnum.THANK_OR_RIDICULE.getTypeId()) ||
                typeId.equals(TypeEnum.SEEK_HELP.getTypeId());
        if (flag) {
            return mixedDataMapper.getMixedDataById(id);
        }

        if (typeId.equals(TypeEnum.LOST_AND_FOUND.getTypeId())) {
            return lostAndFoundMapper.getLostAndFoundById(id);
        }

        if (typeId.equals(TypeEnum.SINGLE.getTypeId())) {
            return singleMapper.getSingleById(id);
        }

        return null;
    }
}