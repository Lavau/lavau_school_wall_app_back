package top.lavau.dao.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * description: CommentMapper 的复杂查询 sql 生成器
 *
 * @author: Leet
 * @date: 2020-11-13 08:59
 **/
public class CommentMapperProvider {

    public String listComment(String id, boolean isParent) {
        return new SQL(){
            {
                StringBuilder sb = new StringBuilder();
                sb.append("_id AS id, _promulgator_id AS promulgatorId, _attached_id AS attachedId, _parent_id AS ").
                    append("parentId, _content AS content, _gmt_create AS gmtCreate, u.avatar_url AS avatarUrl, ").
                    append("u.nickname AS nickname");

                SELECT(sb.toString());
                FROM("_comment");
                LEFT_OUTER_JOIN("_user AS u ON _comment._promulgator_id = u.stu_id");
                WHERE("_is_available = 1 AND _is_audit = 1 AND ".
                        concat(isParent ? "_attached_id = '" : "_parent_id = '").concat(id).concat("'"));
                ORDER_BY("_gmt_create ".concat(isParent ? "DESC" : "ASC"));
            }
        }.toString();
    }

    public String deleteComment(String id, boolean isParent) {
        return new SQL(){
            {
                DELETE_FROM("_comment");
                WHERE((isParent ? "_parent_id = '": "_id = '").concat(id).concat("'"));
            }
        }.toString();
    }
}
