package top.lavau.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
import top.lavau.util.UuidUtil;

import javax.annotation.Resource;
import java.util.Date;


/**
 * description: OtherTypesService 的实现类
 * @author Leet
 **/
@Slf4j
@Service
public class OtherTypesServiceImpl implements OtherTypesService {

    @Resource
    private LostAndFoundMapper lostAndFoundMapper;

    @Resource
    private MixedDataMapper mixedDataMapper;
    
    @
    Resource
    private SingleMapper singleMapper;

    @Override
    public void insertTypeData(Form form) {
        String uuid = UuidUtil.acquireUuid();
        
        insertMixedData(form, uuid);

        if (form.getTypeId().equals(TypeEnum.LOST_AND_FOUND.getTypeId())) {
            insertLostAndFound(form, uuid);
        }

        if (form.getTypeId().equals(TypeEnum.SINGLE.getTypeId())) {
            insertSingle(form, uuid);
        }
        
    }

    private void insertSingle(Form form, String uuid) {
        Single single = new Single();
        single.setId(uuid);
        single.setHeight(form.getHeight());
        single.setWeight(form.getWeight());
        single.setSpeciality(form.getSpeciality());
        single.setInterest(form.getInterest());
        singleMapper.insertSingle(single);
    }

    private void insertMixedData(Form form, String uuid) {
        MixedData mixedData = new MixedData();
        mixedData.setId(uuid);
        mixedData.setPromulgatorId(User.obtainCurrentUser().getStuId());
        mixedData.setTypeId(form.getTypeId());
        mixedData.setTitle(form.getTitle());
        mixedData.setDescription(form.getDescription());
        mixedData.setAnonymous(form.getAnonymous());
        mixedData.setGmtCreate(new Date());
        mixedData.setPictureNum(form.getPictureNum());
        mixedDataMapper.insertMixedData(mixedData);
    }

    private void insertLostAndFound(Form form, String uuid) {
        LostAndFound lostAndFound = new LostAndFound();
        lostAndFound.setId(uuid);
        lostAndFound.setMsg(form.getMsg());
        lostAndFoundMapper.insertLostAndFound(lostAndFound);
    }
}