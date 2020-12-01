package top.lavau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description single 表的实体类
 *
 * @author Leet
 * @date 2020-12-01 16:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Single extends MixedData implements Serializable {
    private String contactInformation;
    private Double height;
    private Double weight;
    private String speciality;
    private String interest;
}
