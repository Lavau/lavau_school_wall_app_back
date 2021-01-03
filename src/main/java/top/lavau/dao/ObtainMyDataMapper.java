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
    @Select("SELECT _mixed_data._id AS id, _description AS description, _title AS title, _picture_num AS pictureNum, " +
            "_comment_num AS commentNum, _gmt_create AS gmtCreate, _like_num AS likeNum, _type_id as typeId, " +
            "_chinese_name AS typeName FROM _mixed_data LEFT JOIN _type ON _mixed_data._type_id = _type._id WHERE " +
            "_promulgator_id = #{stuId} AND _is_audit = 1 AND _is_available = 1 ORDER BY _mixed_data._gmt_create DESC")
    List<MixedData> listObtainPublishedData(@Param("stuId") String stuId);

    /**
     * 获取点赞过的
     * @param stuId 学号
     * @return List<MixedData>
     */
    @Select("SELECT _mixed_data._id AS id, _description AS description, _title AS title, _picture_num AS pictureNum, " +
            "_comment_num AS commentNum, _gmt_create AS gmtCreate, _like_num AS likeNum, _type_id as typeId, " +
            "_chinese_name AS typeName FROM _mixed_data LEFT JOIN _type ON _mixed_data._type_id = _type._id WHERE " +
            "_is_audit = 1 AND _is_available = 1 AND _mixed_data._id IN (SELECT _id FROM _like WHERE _stu_id = " +
            "#{stuId}) ORDER BY _mixed_data._gmt_create DESC")
    List<MixedData> listObtainLikedData(@Param("stuId") String stuId);

    /**
     * 获取评论过的
     * @param stuId 学号
     * @return List<MixedData>
     */
    @Select("SELECT _mixed_data._id AS id, _description AS description, _title AS title, _picture_num AS pictureNum, " +
            "_comment_num AS commentNum, _gmt_create AS gmtCreate, _like_num AS likeNum, _type_id AS typeId, " +
            "_chinese_name AS typeName FROM _mixed_data LEFT JOIN _type ON _mixed_data._type_id = _type._id WHERE " +
            "_is_audit = 1 AND _is_available = 1 AND _mixed_data.id IN (select _attached_id from _comment " +
            "where _promulgator_id = #{stuId} and _attached_id is not null) ORDER BY " +
            "_mixed_data._gmt_create DESC")
    List<MixedData> listObtainCommentedData(@Param("stuId") String stuId);
}
