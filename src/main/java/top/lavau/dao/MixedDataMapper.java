package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import top.lavau.entity.MixedData;

@Mapper
public interface MixedDataMapper {

    @Insert("INSERT _mixed_data (_id, _promulgator_id, _type_id, _title, _description, _picture_num, _gmt_create, " +
            "_is_anonymous, _stu_id) VALUES (#{md.id}, #{md.promulgatorId}, #{md.typeId}, #{md.title}, " +
            "#{md.description}, #{md.pictureNum}, #{md.gmtCreate}, #{md.Anonymous}, #{md.stuId})")
    void insertMixedData(@Param("md") MixedData mixedDataModel);

    @Select("SELECT md._id AS id, _type_id AS typeId, _chinese_name AS typeName, _picture_num AS pictureNum, " +
            "_description AS description, _title AS title, _view_num AS viewNum, md._gmt_create AS gmtCreate, " +
            "_promulgator_id AS promulgatorId, u._avatar_url AS avatarUrl, u._nickname AS nickname, _is_anonymous " +
            "AS Anonymous, md._stu_id as stuId, _like_num AS likeNum, _comment_num AS commentNum, _is_available AS " +
            "Available FROM _mixed_data AS md LEFT JOIN _type ON md._type_id = _type._id LEFT JOIN _user AS u ON " +
            "u._stu_id = md._promulgator_id WHERE md._id = #{id}")
    MixedData getMixedDataById(@Param("id") String id);
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

    @Update("UPDATE _mixed_data SET _like_num = #{md.likeNum}, _view_num = #{md.viewNum}, _comment_num = " +
            "#{md.commentNum}, _is_available = #{md.Available} WHERE _id = #{md.id}")
    void updateMixedData(@Param("md") MixedData mixedData);
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
