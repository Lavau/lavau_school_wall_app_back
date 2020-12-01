package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.lavau.model.LostAndFoundModel;

/**
 * description；
 * @author Leet
 * create: 2020/11/8 14:10
 */
@Mapper
@Repository("miniprogramLostAndFoundMapper")
public interface LostAndFoundMapper {

    /**
     * 根据 id 获取
     * @param id id
     * @return LostAndFoundModel
     */
    @Select("SELECT md.id AS id, promulgator_id AS promulgatorId, type_id AS typeId, title, description, picture_num " +
            "AS pictureNum, like_num AS likeNum, view_num AS viewNum, comment_num AS commentNum, md.gmt_create AS " +
            "gmtCreate, is_available AS Available, is_anonymous AS Anonymous, laf.msg AS msg, laf.claimant_id AS " +
            "claimantId, laf.gmt_claim AS gmtClaim, u.avatar_url AS avatarUrl, u.nickname AS nickname FROM " +
            "mixed_data AS md LEFT JOIN lost_and_found AS laf ON laf.id = md.id LEFT JOIN _user AS u ON u.stu_id = " +
            "md.promulgator_id WHERE md.id = #{id} ")
    LostAndFoundModel getLostAndFoundById(@Param("id") String id);

    /**
     * 添加
     * @param lostAndFound lostAndFound
     */
    @Insert("insert lost_and_found (id, msg) values (#{laf.id}, #{laf.msg})")
    void insertLostAndFound(@Param("laf") LostAndFoundModel lostAndFound);

    /**
     * 添加
     * @param lostAndFound lostAndFound
     */
    @Update("UPDATE lost_and_found SET claimant_id = #{laf.claimantId}, " +
            "gmt_claim = #{laf.gmtClaim} WHERE id = #{laf.id}")
    void updateLostAndFound(@Param("laf") LostAndFoundModel lostAndFound);

    /**
     * 根据 id 删除
     * @param id id
     */
    @Delete("DELETE FROM lost_and_found WHERE id = #{id}")
    void deleteLostAndFoundById(@Param("id") String id);
}
