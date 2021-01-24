package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.lavau.entity.MixedData;

import java.util.List;

/**
 * description:
 * @author Leet
 */
@Mapper
public interface MixedDataMapper {

    @Insert("INSERT _mixed_data (_id, _promulgator_id, _type_id, _title, _description, _picture_num, _gmt_create, " +
            "_is_anonymous, _stu_id) VALUES (#{md.id}, #{md.promulgatorId}, #{md.typeId}, #{md.title}, " +
            "#{md.description}, #{md.pictureNum}, #{md.gmtCreate}, #{md.Anonymous}, #{md.stuId})")
    void insertMixedData(@Param("md") MixedData mixedDataModel);
//
//    @Select("SELECT md.id AS id, i_id AS iId, promulgator_id AS promulgatorId, type_id AS typeId, title, description, " +
//            "picture_num AS pictureNum, like_num AS likeNum, view_num AS viewNum, comment_num AS commentNum, " +
//            "md.gmt_create AS gmtCreate, is_available AS Available, is_anonymous AS Anonymous, is_audit AS Audit, " +
//            "md.stu_id AS stuId, u.avatar_url AS avatarUrl, u.nickname AS nickname FROM mixed_data AS md LEFT JOIN " +
//            "_user AS u ON md.promulgator_id = u.stu_id WHERE id = #{id}")
//    MixedData getMixedDataById(@Param("id") String id);
//
//    /**
//     * 根据 typeId 获取信息
//     * @param typeId typeId
//     * @return List<MixedDataModel>
//     */
//    @Select("SELECT md.id AS id, type_id AS typeId, chinese_name AS typeName, picture_num AS pictureNum, " +
//            "description, title, view_num AS viewNum, md.gmt_create AS gmtCreate, promulgator_id AS " +
//            "promulgatorId, u.avatar_url AS avatarUrl, u.nickname AS nickname, is_anonymous AS Anonymous, " +
//            "md.stu_id as stuId FROM mixed_data AS md LEFT JOIN type ON md.type_id = type.id LEFT JOIN _user " +
//            "AS u ON u.stu_id = md.promulgator_id WHERE is_audit = 1 AND is_available = 1 AND md.type_id = " +
//            "#{typeId} ORDER BY md.gmt_create DESC")
//    List<MixedData> listDataByTypeId(@Param("typeId") String typeId);
//
//    /**
//     * 更改  mixed_data 表
//     * @param mixedData MixedDataModel
//     */
//    @Update("UPDATE mixed_data SET like_num = #{md.likeNum}, view_num = #{md.viewNum}, comment_num = " +
//            "#{md.commentNum}, is_available = #{md.Available} WHERE id = #{md.id}")
//    void updateMixedData(@Param("md") MixedData mixedData);
//
//    /**
//     * 根据 id 删除
//     * @param id id
//     */
//    @Delete("DELETE FROM mixed_data WHERE id = #{id}")
//    void deleteMixedDataById(@Param("id") String id);
//
//    /**
//     * 是否点过赞
//     * @param id id
//     * @param stuId stuId
//     * @return Boolean
//     */
//    @Select("SELECT count(id) FROM _like WHERE id = #{id} AND stu_id = #{stuId}")
//    boolean isLike(@Param("id") String id, @Param("stuId") String stuId);
//
//    /**
//     * 添加赞
//     * @param id id
//     * @param stuId stuId
//     */
//    @Insert("INSERT _like (id, stu_id) VALUES (#{id}, #{stuId})")
//    void insertLike(@Param("id") String id, @Param("stuId") String stuId);
//
//    /**
//     * 删除赞
//     * @param id id
//     * @param stuId stuId
//     */
//    @Delete("DELETE FROM _like WHERE id = #{id} AND stu_id = #{stuId}")
//    void deleteLike(@Param("id") String id, @Param("stuId") String stuId);
}
