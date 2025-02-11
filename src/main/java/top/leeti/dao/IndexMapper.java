package top.leeti.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leeti.entity.MixedData;

import java.util.List;

@Mapper
public interface IndexMapper {

    @Select("SELECT md._id AS id, _type_id AS typeId, _chinese_name AS typeName, _picture_num AS pictureNum, " +
            "_description AS description, _title AS title, _view_num AS viewNum, md._gmt_create AS gmtCreate, " +
            "_promulgator_id AS promulgatorId, u._avatar_url AS avatarUrl, u._nickname AS nickname, _is_anonymous " +
            "AS Anonymous, md._stu_id as stuId FROM _mixed_data AS md LEFT JOIN _type ON md._type_id = _type._id " +
            "LEFT JOIN _user AS u ON u._stu_id = md._promulgator_id WHERE _is_audit = 1 AND _is_available = 1 " +
            "ORDER BY md._gmt_create DESC")
    List<MixedData> listIndexData();
}
