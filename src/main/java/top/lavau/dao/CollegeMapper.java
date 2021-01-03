package top.lavau.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.lavau.entity.College;

import java.util.List;

/**
 * description
 *
 * @author leet
 * @date 2020/12/1 16:19
 */
@Mapper
public interface CollegeMapper {
    /**
     * 获取学院信息
     * @return List<College>
     */
    @Select("select _college_id as collegeId, _college_name as collegeName from _college " +
            "where _is_available = 1 order by _college_id asc")
    List<College> listCollege();
}
