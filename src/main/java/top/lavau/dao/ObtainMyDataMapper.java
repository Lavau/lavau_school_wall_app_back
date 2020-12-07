package top.lavau.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.lavau.entity.MixedData;

import java.util.List;

/**
 * description: 从数据库中获取“我”发布过的、点赞的、评论的数据
 * @author Leet
 * @date 2020-11-06 21:32
 **/
@Mapper
@Repository
public interface ObtainMyDataMapper {

    /**
     * 获取发布过的
     * @param stuId 学号
     * @return List<MixedData>
     */
    @Select("SELECT mixed_data.id AS id, description, title, picture_num AS pictureNum, comment_num AS commentNum, " +
            "gmt_create AS gmtCreate, like_num AS likeNum, type_id as typeId, chinese_name AS typeName FROM " +
            "mixed_data LEFT JOIN type ON mixed_data.type_id = type.id WHERE promulgator_id = #{stuId} " +
            "AND is_audit = 1 AND is_available = 1 ORDER BY mixed_data.gmt_create DESC")
    List<MixedData> listObtainPublishedData(@Param("stuId") String stuId);

    /**
     * 获取点赞过的
     * @param stuId 学号
     * @return List<MixedData>
     */
    @Select("SELECT mixed_data.id AS id, description, title, picture_num AS pictureNum, comment_num AS commentNum, " +
            "gmt_create AS gmtCreate, like_num AS likeNum, type_id as typeId, chinese_name AS typeName FROM " +
            "mixed_data LEFT JOIN type ON mixed_data.type_id = type.id WHERE is_audit = 1 AND is_available = 1 " +
            "AND mixed_data.id IN (SELECT id FROM _like WHERE stu_id = #{stuId}) ORDER BY mixed_data.gmt_create DESC")
    List<MixedData> listObtainLikedData(@Param("stuId") String stuId);

    /**
     * 获取评论过的
     * @param stuId 学号
     * @return List<MixedData>
     */
    @Select("SELECT mixed_data.id AS id, description, title, picture_num AS pictureNum, comment_num AS commentNum, " +
            "gmt_create AS gmtCreate, like_num AS likeNum, type_id AS typeId, chinese_name AS typeName FROM mixed_data " +
            "LEFT JOIN type ON mixed_data.type_id = type.id WHERE is_audit = 1 AND is_available = 1 AND mixed_data.id " +
            "IN (select _attached_id from _comment where _promulgator_id = #{stuId} and _attached_id is not null) ORDER BY " +
            "mixed_data.gmt_create DESC")
    List<MixedData> listObtainCommentedData(@Param("stuId") String stuId);
}
