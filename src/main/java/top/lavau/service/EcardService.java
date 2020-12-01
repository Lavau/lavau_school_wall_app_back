package top.lavau.service;

import org.apache.ibatis.annotations.Param;
import top.lavau.entity.Ecard;

import java.util.List;

/**
 * description:
 * @author Leet
 * create: 2020/11/8 10:42
 */
public interface EcardService {

    /**
     * 向 ecard 表中添加一卡通数据
     * @param ecard ecard
     * @return boolean 添加成功：true；添加失败：false
     */
    boolean insertEcard(Ecard ecard);

    /**
     * 根据 id 获取 ecard 信息
     * @param id id
     * @return Ecard
     */
    Ecard getEcardById(@Param("id") String id);

    /**
     * 根据 stuId 获取 ecard 信息
     * @param stuId stuId
     * @return Ecard
     */
    Ecard getEcardByStuId(@Param("stuId") String stuId);

    /**
     * 根据 id 更新 ecard
     * @param ecard ecard
     * @return boolean 更新成功：true；更新失败：false
     */
    boolean updateEcard(@Param("e") Ecard ecard);

    /**
     * 获取一卡通列表
     * @return List<Ecard>
     */
    List<Ecard> listEcard();

}
