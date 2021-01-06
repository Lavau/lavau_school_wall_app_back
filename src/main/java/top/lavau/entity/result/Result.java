package top.lavau.entity.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description 处理结果
 *
 * @author Leet
 * @date 2020-12-06 21:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private String code;

    /**
     * 返回的信息
     */
    private String msg;

    /**
     * 返回的泛型结果
     */
    private T data;

    @Data
    @AllArgsConstructor
    public static class MyPage<P> {
        private int pageNum;
        private int pages;
        private List<P> list;
    }
}
