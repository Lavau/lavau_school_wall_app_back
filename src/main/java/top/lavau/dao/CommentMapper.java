package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import top.lavau.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT _id AS id, _promulgator_id AS promulgatorId, _attached_id As attachedId, _parent_id AS parentId, " +
            "_content AS content, _comment._gmt_create AS gmtCreate, _avatar_url AS avatarUrl, _nickname AS nickname " +
            "FROM _comment LEFT JOIN _user ON _user._stu_id = _comment._promulgator_id WHERE _is_available = 1 AND " +
            "_is_audit = 1 AND _attached_id = #{attachedId}")
    List<Comment> listCommentsOfMixedData(@Param("attachedId") String attachedId);

    @Select("SELECT count(_id) FROM _comment WHERE _parent_id = #{parentId}")
    int getCommentNumByParentId(@Param("parentId") String parentId);

    @Insert("INSERT _comment (_id, _promulgator_id, _attached_id, _parent_id, _content, _gmt_create) VALUES " +
            "(#{comment.id}, #{comment.promulgatorId}, #{comment.attachedId}, #{comment.parentId}, " +
            "#{comment.content}, #{comment.gmtCreate})")
    void insertComment(@Param("comment") Comment comment);

    @Delete ("DELETE FROM _comment WHERE _parent_id = #{parentId}")
    void deleteCommentByParentId(@Param("parentId") String parentId);


    @Select("SELECT _id AS id, _promulgator_id AS promulgatorId, _attached_id As attachedId, _parent_id AS parentId, " +
            "_content AS content, _comment._gmt_create AS gmtCreate, _avatar_url AS avatarUrl, _nickname AS nickname " +
            "FROM _comment LEFT JOIN _user ON _user._stu_id = _comment._promulgator_id WHERE _id = #{id}")
    Comment getCommentById(@Param("id") String id);

//    /**
//     * 根据 id 从 _like 表中获取点赞数
//     * @param id id
//     * @return int
//     */
//    @Select("SELECT count(id) FROM _like WHERE id = #{id}")
//    int getLikeNumById(@Param("id") String id);
//
//    /**
//     * 删除 _comment 表中的数据
//     * @param id id
//     * @param isParent true: id 为 _parent_id（删除某个一级评论及其子评论），false：id 为 _id（只删除某个二级评论）
//     */
//    @DeleteProvider(value = CommentMapperProvider.class, method = "deleteComment")
//    void deleteComment(String id, boolean isParent);
//
}
