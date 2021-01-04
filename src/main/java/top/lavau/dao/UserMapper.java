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
    @Insert("INSERT _user (_stu_id, _stu_name, _college_id, _avatar_url, _nickname, _gmt_create, _en_password) VALUES " +
            "(#{user.stuId}, #{user.stuName}, #{user.collegeId}, #{user.avatarUrl}, #{user.nickname}, " +
            "#{user.gmtCreate}, #{user.enPassword})")
    boolean insertUser(@Param("user") User user);

    /**
     * 根据 _stu_id, _en_password 字段从 _user 表中获取用户
     * @param stuId stuId
     * @param enPassword enPassword 被加密的密码
     * @return User
     */
    @Select("SELECT _open_id as openId, _stu_name as stuName, _stu_id as stuId, _college_id as collegeId, _avatar_url " +
            "as avatarUrl, _nickname AS nickname, _gmt_create as gmtCreate from _user where _stu_id = #{stuId} " +
            "AND _en_password = #{enPassword}")
    User getUserByStuIdAndPassword(@Param("stuId") String stuId, @Param("enPassword") String enPassword);
}
