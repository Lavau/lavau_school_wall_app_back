package top.lavau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * description lost_and_found 表的实体类
 *
 * @author Leet
 * @date 2020-12-01 14:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LostAndFound extends MixedData implements Serializable {
    private String msg;
    private String claimantId;
    private Date gmtClaim;

    /*
     * 在数据库中没有以下属性
     */
    private String claimantName;
}
