package top.leeti.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.leeti.entity.Single;

@Mapper
public interface SingleMapper {

    @Select("SELECT _single._id AS id, _promulgator_id AS promulgatorId, _type_id AS typeId, _title AS title, " +
            "_description AS description, _picture_num AS pictureNum, _like_num AS likeNum, _view_num AS viewNum, " +
            "_comment_num AS commentNum, md._gmt_create AS gmtCreate, _is_available AS Available, _is_anonymous AS " +
            "Anonymous, _contact_information AS contactInformation, _height AS height, _weight AS weight, _interest " +
            "AS interest, _speciality AS speciality, u._avatar_url AS avatarUrl, u._nickname AS nickname FROM " +
            "_mixed_data AS md LEFT JOIN _single ON _single._id = md._id LEFT JOIN _user AS u ON u._stu_id = " +
            "md._promulgator_id WHERE md._id = #{id} ")
    Single getSingleById(@Param("id") String id);

    @Insert("insert _single (_id, _height, _weight, _speciality, _interest, _contact_information) values (#{s.id}, " +
            "#{s.height}, #{s.weight}, #{s.speciality}, #{s.interest}, #{s.contactInformation})")
    boolean insertSingle(@Param("s") Single single);
}
