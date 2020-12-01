package top.lavau.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.dao.UserMapper;
import top.lavau.entity.User;
import top.lavau.service.UserService;

import javax.annotation.Resource;

/**
 * description
 *
 * @author leet
 * @date 2020/12/1 20:35
 */
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
    public User getUserByUsernameAndPassword(String username, String password) {
        return userMapper.getUserByUsernameAndPassword(username, password);
    }
}
