package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import top.lavau.entity.Ecard;

import java.util.List;

/**
 * description
 *
 * @author Leet
 * @date 2020/12/6 21:00
 */
@Mapper
public interface EcardMapper {

    /**
     * 向 ecard 表中添加一卡通数据
     * @param ecard ecard
     * @return boolean 添加成功：true；添加失败：false
     */
    @Insert("insert ecard (id, promulgator_id, gmt_create, college, stu_id, ecard_id, stu_name, msg) " +
            "values (#{e.id}, #{e.promulgatorId}, #{e.gmtCreate}, #{e.college}, #{e.stuId}, #{e.ecardId}, " +
            "#{e.stuName}, #{e.msg})")
    boolean insertEcard(@Param("e") Ecard ecard);

    /**
     * 根据 id 获取 ecard 信息
     * @param id id
     * @return Ecard
     */
    @Select("SELECT id, college, stu_id AS stuId, stu_name AS stuName, ecard_id AS ecardId, msg " +
            "FROM ecard WHERE id = #{id}")
    Ecard getEcardById(@Param("id") String id);

    /**
     * 根据 stuId 获取 ecard 信息
     * @param stuId stuId
     * @return Ecard
     */
    @Select("SELECT id, title AS college, description AS stuName, stu_id AS stuId FROM mixed_data WHERE " +
            "is_available = 1 AND is_audit = 1 AND stu_id = #{stuId} ORDER BY gmt_create DESC")
    Ecard getEcardByStuId(@Param("stuId") String stuId);

    /**
     * 根据 id 更新 ecard
     * @param ecard ecard
     * @return boolean 更新成功：true；更新失败：false
     */
    @Update("UPDATE ecard SET claimant_id = #{e.claimantId}, gmt_claim = #{e.gmtClaim} WHERE id = #{e.id}")
    boolean updateEcard(@Param("e") Ecard ecard);

    /**
     * 获取一卡通列表
     * @return List<Ecard>
     */
    @Select("SELECT id, title AS college, description AS stuName, stu_id AS stuId FROM mixed_data WHERE " +
            "is_available = 1 AND is_audit = 1 AND type_id = 7 ORDER BY gmt_create DESC")
    List<Ecard> listEcard();
}
