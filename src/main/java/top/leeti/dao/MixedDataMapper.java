package top.leeti.dao;

import org.apache.ibatis.annotations.*;
import top.leeti.entity.MixedData;

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

    @Update("UPDATE _mixed_data SET _like_num = #{md.likeNum}, _view_num = #{md.viewNum}, _comment_num = " +
            "#{md.commentNum}, _is_available = #{md.Available} WHERE _id = #{md.id}")
    void updateMixedData(@Param("md") MixedData mixedData);
}
