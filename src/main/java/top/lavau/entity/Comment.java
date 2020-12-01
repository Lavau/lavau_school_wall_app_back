package top.lavau.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * description: 评论 model
 *
 * @author: Leet
 * @date: 2020-11-13 08:44
 **/
@Data
public class Comment implements Serializable {
    private String id;
    private String promulgatorId;
    private String attachedId;
    private String parentId;
    private String content;
    private Date gmtCreate;
    private Boolean Available;
    private Boolean Audit;

    /*
     * 在数据库中没有以下属性
     */
    // 这条评论是不是我发布的
    private Boolean Mine;
    private String avatarUrl;
    private String nickname;
    private String createTime;
    private Integer likeNum;
    private Integer commentNum;
    private Boolean Like;
}
