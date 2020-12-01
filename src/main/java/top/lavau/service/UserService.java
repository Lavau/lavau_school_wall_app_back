package top.lavau.service;

import top.lavau.entity.User;

/**
 * @author Leet
 * create: 2020-11-5 22:21
 */
public interface UserService {
    /**
     * 向 _user 表中添加用户
     * @param user user
     * @return boolean 添加成功：true；添加失败：false
     */
    boolean insertUser(User user);

    /**
     * 根据 username, password 字段从 _user 表中获取用户
     * @param username username
     * @param password password
     * @return User
     */
    User getUserByUsernameAndPassword(String username, String password);
}
