package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import top.lavau.entity.Like;

@Mapper
public interface LikeMapper {
    @Insert("INSERT _like (_id, _stu_id, _gmt_create) VALUES (#{like.id}, #{like.stuId}, #{like.gmtCreate})")
    void insertLike(@Param("like")Like like);

    @Delete("DELETE FROM _like WHERE _id = #{id} AND _stu_id = #{stuId}")
    void deleteLikeByIdAndStuId(@Param("id") String id, @Param("stuId") String stuId);

    @Select("SELECT _id AS id, _stu_id AS stuId, _gmt_create AS gmtCreate FROM _like WHERE _id = #{id} AND _stu_id = #{stuId}")
    Like getLikeByIdAndStuId(@Param("id") String id, @Param("stuId") String stuId);

    @Select("SELECT count(_id) FROM _like WHERE _stu_id = #{stuId}")
    Integer getLikeNumByStuId(@Param("stuId") String stuId);
}
