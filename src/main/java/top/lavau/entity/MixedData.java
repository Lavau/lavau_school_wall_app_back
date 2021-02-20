package top.lavau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MixedData implements Serializable {
    protected String id;
    protected Integer iId;
    protected String promulgatorId;
    protected Integer typeId;
    /**
     * 标题
     * (当类型为失物招领时，作为物品名；
     *  当类型为脱单时，无此字段；
     *  类型为一卡通时，为学院名）
     */
    protected String title;
    /**
     * 发布的文字内容
     * （类型为一卡通时，为姓名）
     */
    protected String description;
    protected Integer pictureNum;
    protected Integer likeNum;
    protected Integer viewNum;
    protected Integer commentNum;
    protected Date gmtCreate;
    protected Boolean Audit;
    protected Boolean Available;
    protected Boolean Anonymous;
    /**
     * 此字段仅对“一卡通”有效
     * （类型为一卡通时，为学号）
     */
    protected String stuId;


    /*
     * 在数据库中没有以下属性
     */
    protected String createTime;
    protected String typeName;
    protected List<String> pictureUrlList;

    private String avatarUrl;
    private String nickname;
    private Boolean Like;


}
