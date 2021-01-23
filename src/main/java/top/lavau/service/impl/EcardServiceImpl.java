package top.lavau.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.dao.EcardMapper;
import top.lavau.dao.MixedDataMapper;
import top.lavau.entity.Ecard;
import top.lavau.entity.MixedData;
import top.lavau.myenum.TypeEnum;
import top.lavau.service.EcardService;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 * @author Leet
 */
@Service
public class EcardServiceImpl implements EcardService {

    @Value("${page.size}")
    private int pageSize;

    @Resource
    private EcardMapper ecardMapper;

    @Resource
    private MixedDataMapper mixedDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertEcard(Ecard ecard) {
        insertMixedData(ecard);
        return ecardMapper.insertEcard(ecard);
    }

    private void insertMixedData(Ecard ecard) {
        MixedData mixedData = new MixedData();
        mixedData.setId(ecard.getId());
        mixedData.setTypeId(TypeEnum.ECARD.getTypeId());
        mixedData.setGmtCreate(ecard.getGmtCreate());
        mixedData.setPromulgatorId(ecard.getPromulgatorId());
        mixedData.setTitle(ecard.getCollege());
        mixedData.setDescription(ecard.getStuName());
        mixedData.setStuId(ecard.getStuId());
        mixedData.setPictureNum(0);
        mixedData.setAnonymous(false);
        mixedDataMapper.insertMixedData(mixedData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Ecard getEcardById(String id) {
        return ecardMapper.getEcardById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Ecard getEcardByStuId(String stuId) {
        return ecardMapper.getEcardByStuId(stuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateEcard(Ecard ecard) {
        return ecardMapper.updateEcard(ecard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<Ecard> listEcard(int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ecard> ecardList = ecardMapper.listEcard();
        return new PageInfo<>(ecardList);
    }
}
