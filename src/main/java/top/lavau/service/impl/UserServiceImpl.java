package top.lavau.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.dao.UserMapper;
import top.lavau.entity.User;
import top.lavau.service.UserService;

import javax.annotation.Resource;

/**
 * description: UserService 的实现类
 *
 * @author leet
 * @date 2020/12/1 20:35
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getUserByStuIdAndPassword(String stuId, String enPassword) {
        return userMapper.getUserByStuIdAndPassword(stuId, enPassword);
    }
}
