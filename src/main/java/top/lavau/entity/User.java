package top.lavau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * description _user 表对应的实体类
 *
 * @author Leet
 * @date 2020-12-01 16:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends MixedData implements Serializable {
    private String openId;

    @NotBlank(message = "学生名字为空")
    @NotEmpty(message = "学生名字为空")
    @NotNull(message = "学生名字为空")
    private String stuName;

    @NotBlank(message = "学号为空")
    @NotEmpty(message = "学号为空")
    @NotNull(message = "学号为空")
    private String stuId;

    @NotBlank(message = "学院号为空")
    @NotEmpty(message = "学院号为空")
    @NotNull(message = "学院号为空")
    private String collegeId;

    @NotBlank(message = "头像url为空")
    @NotEmpty(message = "头像url为空")
    @NotNull(message = "头像url为空")
    private String avatarUrl;

    @NotBlank(message = "昵称为空")
    @NotEmpty(message = "昵称为空")
    @NotNull(message = "昵称为空")
    private String nickname;

    private Date gmtCreate;
}
