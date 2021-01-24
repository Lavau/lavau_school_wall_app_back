package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.lavau.entity.LostAndFound;

/**
 * description；
 * @author Leet
 */
@Mapper
@Repository("miniprogramLostAndFoundMapper")
public interface LostAndFoundMapper {
//
//    /**
//     * 根据 id 获取
//     * @param id id
//     * @return LostAndFoundModel
//     */
//    @Select("SELECT md._id AS id, _promulgator_id AS promulgatorId, _type_id AS typeId, _title AS title, _description " +
//            "AS description, _picture_num AS pictureNum, _like_num AS likeNum, _view_num AS viewNum, _comment_num AS " +
//            "commentNum, md._gmt_create AS gmtCreate, _is_available AS Available, _is_anonymous AS Anonymous, _laf.msg " +
//            "AS msg, laf._claimant_id AS claimantId, laf._gmt_claim AS gmtClaim, u._avatar_url AS avatarUrl, u._nickname " +
//            "AS nickname FROM _mixed_data AS md LEFT JOIN _lost_and_found AS laf ON laf._id = md._id LEFT JOIN _user AS " +
//            "u ON u._stu_id = md._promulgator_id WHERE md._id = #{id} ")
//    LostAndFound getLostAndFoundById(@Param("id") String id);

    @Insert("insert _lost_and_found (_id, _msg) values (#{laf.id}, #{laf.msg})")
    boolean insertLostAndFound(@Param("laf") LostAndFound lostAndFound);
//
//    /**
//     * 添加
//     * @param lostAndFound lostAndFound
//     */
//    @Update("UPDATE _lost_and_found SET _claimant_id = #{laf.claimantId}, " +
//            "_gmt_claim = #{laf.gmtClaim} WHERE _id = #{laf.id}")
//    void updateLostAndFound(@Param("laf") LostAndFound lostAndFound);
//
//    /**
//     * 根据 id 删除
//     * @param id id
//     */
//    @Delete("DELETE FROM _lost_and_found WHERE _id = #{id}")
//    void deleteLostAndFoundById(@Param("id") String id);
}
