package top.leeti.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.dao.ObtainMyDataMapper;
import top.leeti.entity.MixedData;
import top.leeti.entity.User;
import top.leeti.service.ObtainMyDataService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ObtainMyDataServiceImpl implements ObtainMyDataService {

    @Resource
    private ObtainMyDataMapper obtainMyDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MixedData> obtainMyDataByTypeId(Integer typeId) {
        String stuId = User.obtainCurrentUser().getStuId();

        if (new Integer(1).equals(typeId)) {
            return obtainMyDataMapper.listObtainPublishedData(stuId);
        } else if (new Integer(2).equals(typeId)) {
            return obtainMyDataMapper.listObtainLikedData(stuId);
        } else if (new Integer(3).equals(typeId)) {
            return obtainMyDataMapper.listObtainCommentedData(stuId);
        }

        return null;
    }
}
