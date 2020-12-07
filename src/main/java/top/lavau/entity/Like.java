package top.lavau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * description _like 表的实体类
 *
 * @author Leet
 * @date 2020-12-01 20:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like implements Serializable {
    private String id;
    private String stuId;
    private Date gmtCreate;
}
