package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import top.lavau.entity.Ecard;

@Mapper
public interface EcardMapper {

    @Insert("insert _ecard (_id, _promulgator_id, _gmt_create, _college, _stu_id, _ecard_id, _stu_name, _msg) " +
            "values (#{e.id}, #{e.promulgatorId}, #{e.gmtCreate}, #{e.college}, #{e.stuId}, #{e.ecardId}, " +
            "#{e.stuName}, #{e.msg})")
    boolean insertEcard(@Param("e") Ecard ecard);

    @Select("SELECT _id AS id, _college AS college, _stu_id AS stuId, _stu_name AS stuName, _ecard_id AS ecardId, " +
            "_msg AS msg, _gmt_create AS gmtCreate, _gmt_claim AS gmtClaim FROM _ecard WHERE _id = #{id}")
    Ecard getEcardById(@Param("id") String id);

    @Update("UPDATE _ecard SET _claimant_id = #{e.claimantId}, _gmt_claim = #{e.gmtClaim} WHERE _id = #{e.id}")
    boolean updateEcard(@Param("e") Ecard ecard);
}
