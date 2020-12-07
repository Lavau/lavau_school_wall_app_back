//package top.lavau.dao;
//
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//import top.lavau.model.SingleModel;
//
///**
// * description；
// * @author Leet
// * create: 2020/11/8 14:10
// */
//@Mapper
//@Repository("miniprogramSingleMapper")
//public interface SingleMapper {
//
//    /**
//     * 根据 id 获取
//     * @param id id
//     * @return SingleModel
//     */
//    @Select("SELECT single.id AS id, promulgator_id AS promulgatorId, type_id AS typeId, title, description, " +
//            "picture_num AS pictureNum, like_num AS likeNum, view_num AS viewNum, comment_num AS commentNum, " +
//            "md.gmt_create AS gmtCreate, is_available AS Available, is_anonymous AS Anonymous, contact_information " +
//            "AS contactInformation, height, weight, interest, speciality, u.avatar_url AS avatarUrl, u.nickname AS " +
//            "nickname FROM mixed_data AS md LEFT JOIN single ON single.id = md.id LEFT JOIN _user AS u ON u.stu_id = " +
//            "md.promulgator_id WHERE md.id = #{id} ")
//    SingleModel getSingleById(@Param("id") String id);
//
//    /**
//     * 添加
//     * @param single single
//     */
//    @Insert("insert single (id, height, weight, speciality, interest, contact_information) values (#{s.id}, " +
//            "#{s.height}, #{s.weight}, #{s.speciality}, #{s.interest}, #{s.contactInformation})")
//    void insertSingle(@Param("s") SingleModel single);
//
//    /**
//     * 根据 id 删除
//     * @param id id
//     */
//    @Delete("DELETE FROM single WHERE id = #{id}")
//    void deleteSingleById(@Param("id") String id);
//}
