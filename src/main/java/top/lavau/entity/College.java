package top.lavau.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * description college 表的实体类
 *
 * @author Leet
 * @date 2020-12-01 16:20
 **/
@Data
public class College implements Serializable {
    private Integer id;
    private String collegeId;
    private String collegeName;
}
