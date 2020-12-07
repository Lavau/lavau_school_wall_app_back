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
    @Insert("INSERT _user (stu_id, stu_name, college_id, avatar_url, nickname, gmt_create, en_password) VALUES " +
            "(#{user.stuId}, #{user.stuName}, #{user.collegeId}, #{user.avatarUrl}, #{user.nickname}, " +
            "#{user.gmtCreate}, #{user.enPassword})")
    boolean insertUser(@Param("user") User user);

    /**
     * 根据 username, password 字段从 _user 表中获取用户
     * @param nickname nickname
     * @param enPassword enPassword 被加密的密码
     * @return User
     */
    @Select("select open_id as openId, stu_name as stuName, stu_id as stuId, college_id as collegeId, avatar_url " +
            "as avatarUrl, nickname, gmt_create as gmtCreate from _user where nickname = #{nickname} AND en_password = #{enPassword}")
    User getUserByNicknameAndPassword(@Param("nickname") String nickname, @Param("enPassword") String enPassword);
}
