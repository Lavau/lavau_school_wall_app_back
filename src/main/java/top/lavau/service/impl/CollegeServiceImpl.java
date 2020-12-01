package top.lavau.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.dao.CollegeMapper;
import top.lavau.entity.College;
import top.lavau.service.CollegeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author leet
 * @date 2020/12/1 17:23
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Resource
    private CollegeMapper collegeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<College> listCollege() {
        return collegeMapper.listCollege();
    }
}
