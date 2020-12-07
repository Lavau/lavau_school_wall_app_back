package top.lavau.myenum;

import lombok.Getter;

/**
 * description 处理结果code枚举
 *      W0XXX 为自定义状态码
 *      其余采用《阿里泰山版开发手册》中的状态码
 *
 * @author Leet
 * @date 2020-12-06 21:53
 **/
@Getter
public enum ResultCodeEnum {

    /**
     * code: W0600
     * explanation: valid error
     * 含义：JSR-303 校验出错
     */
    VALID_ERROR("W0600", "valid error"),
    /**
     * code: W0601
     * explanation: 图片保存失败
     */
    PICTURE_SAVE_UNSUCCESSFULLY("W0601", "图片保存失败"),
    /**
     * code: 0000
     * explanation: ok
     * 含义：请求处理完成，正常返回
     */
    OK("0000", "ok");


    /**
     * 状态码
     */
    private String code;
    /**
     * 解释 code 的含义
     */
    private String explanation;

    ResultCodeEnum(String code, String explanation) {
        this.code = code;
        this.explanation = explanation;
    }
}
