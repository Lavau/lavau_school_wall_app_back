package top.lavau.service;

import com.github.pagehelper.PageInfo;
import top.lavau.entity.Ecard;

/**
 * description
 *
 * @author Leet
 * @date 2020/12/6 21:06
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
    Ecard getEcardById(String id);

    /**
     * 根据 stuId 获取 ecard 信息
     * @param stuId stuId
     * @return Ecard
     */
    Ecard getEcardByStuId(String stuId);

    /**
     * 根据 id 更新 ecard
     * @param ecard ecard
     * @return boolean 更新成功：true；更新失败：false
     */
    boolean updateEcard(Ecard ecard);

    /**
     * 获取一卡通列表
     * @param pageNum 请求的页码
     * @return List<Ecard>
     */
    PageInfo<Ecard> listEcard(int pageNum);
}
