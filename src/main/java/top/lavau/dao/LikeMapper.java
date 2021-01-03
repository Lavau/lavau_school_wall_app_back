package top.lavau.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.lavau.entity.Like;

/**
 * @author leet
 * @date 2020/12/1 21:03
 */
@Mapper
public interface LikeMapper {
    /**
     * 向 _like 表中添加一条点赞记录
     * @param like like
     * @return boolean 添加成功：true；添加失败：false
     */
    @Insert("INSERT _like (_id, _stu_id, _gmt_create) VALUES (#{like.id}, #{like.stuId}, #{like.gmtCreate})")
    boolean insertLike(@Param("like")Like like);

    /**
     * 根据 id、stu_id 两字段删除 _like 表中的数据
     * @param id id
     * @param stuId stuId
     * @return boolean 删除成功：true；删除失败：false
     */
    @Delete("DELETE FROM _like WHERE _id = #{id} AND _stu_id = #{stuId}")
    boolean deleteLikeByIdAndStuId(@Param("id") String id, @Param("stuId") String stuId);
}
