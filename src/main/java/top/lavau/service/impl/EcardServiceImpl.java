package top.lavau.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.dao.EcardMapper;
import top.lavau.entity.Ecard;
import top.lavau.service.EcardService;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 * @author Leet
 * create: 2020/11/8 11:44
 */
@Service
public class EcardServiceImpl implements EcardService {

    @Value("${page.size}")
    private int pageSize;

    @Resource
    private EcardMapper ecardMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertEcard(Ecard ecard) {
        return ecardMapper.insertEcard(ecard);
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
