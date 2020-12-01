package top.lavau.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.lavau.entity.User;

/**
 * @author Leet
 * create: 2020/11/5 21:47
 */
@Mapper
public interface UserMapper {
    /**
     * 向 _user 表中添加用户
     * @param user user
     * @return boolean 添加成功：true；添加失败：false
     */
    @Insert("")
    boolean insertUser(@Param("user") User user);

    /**
     * 根据 username, password 字段从 _user 表中获取用户
     * @param username username
     * @param password password
     * @return User
     */
    @Select("select open_id as openId, stu_name as stuName, stu_id as stuId, college_id as collegeId, avatar_url " +
            "as avatarUrl, nickname, gmt_create as gmtCreate from _user where username = #{username} AND password = #{password}")
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
