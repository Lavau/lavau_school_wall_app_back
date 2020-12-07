package top.lavau.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.lavau.entity.MixedData;

import java.util.List;

/**
 * description: 小程序首页数据
 * @author Leet
 * @date 2020-11-04 17:29
 **/
@Mapper
@Repository
public interface IndexMapper {

    /**
     * 获取小程序端用于首页展示的数据
     * @return List<MixedDataModel>
     */
    @Select("SELECT md.id AS id, type_id AS typeId, chinese_name AS typeName, picture_num AS pictureNum, " +
            "description, title, view_num AS viewNum, md.gmt_create AS gmtCreate, promulgator_id AS " +
            "promulgatorId, u.avatar_url AS avatarUrl, u.nickname AS nickname, is_anonymous AS Anonymous, " +
            "md.stu_id as stuId FROM mixed_data AS md LEFT JOIN type ON md.type_id = type.id LEFT JOIN _user " +
            "AS u ON u.stu_id = md.promulgator_id WHERE is_audit = 1 AND is_available = 1 " +
            "ORDER BY md.gmt_create DESC")
    List<MixedData> listMiniprogramIndexData();
}
