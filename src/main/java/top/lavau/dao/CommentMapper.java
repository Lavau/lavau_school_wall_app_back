package top.lavau.dao;

import org.apache.ibatis.annotations.*;
import top.lavau.dao.provider.CommentMapperProvider;
import top.lavau.entity.Comment;

import java.util.List;

/**
 * description: commentMapper
 *
 * @author: Leet
 * @date: 2020-12-1 17:11
 **/
@Mapper
public interface CommentMapper {

    /**
     * isParent 为 true，根据 id（_attached_id） 获取评论；
     * isParent 为 false，根据 id（_parent_id） 获取评论
     * @param id id
     * @param isParent isParent
     * @return List<Comment>
     */
    @SelectProvider(value = CommentMapperProvider.class, method = "listComment")
    List<Comment> listComment(String id, boolean isParent);

    /**
     * 根据 id 获取评论
     * @param id id
     * @return commentModel
     */
    @Select("SELECT _id AS id, _promulgator_id AS promulgatorId, _attached_id AS attachedId, _parent_id AS parentId, " +
            "_content AS content, _gmt_create AS gmtCreate, u.avatar_url AS avatarUrl, u.nickname AS nickname FROM " +
            "_comment LEFT OUTER JOIN _user AS u ON _comment._promulgator_id = u.stu_id WHERE _id = #{id}")
    Comment getCommentById(@Param("id") String id);

    /**
     * 向 _comment 表中添加一条数据
     * @param commentModel commentModel
     */
    @Insert("INSERT _comment (_id, _promulgator_id, _attached_id, _parent_id, _content, _gmt_create) VALUES " +
            "(#{comment.id}, #{comment.promulgatorId}, #{comment.attachedId}, #{comment.parentId}, " +
            "#{comment.content}, #{comment.gmtCreate})")
    void insertComment(@Param("comment") Comment commentModel);

    /**
     * 获取评论数
     * @param id id
     * @return int
     */
    @Select("SELECT count(_id) FROM _comment WHERE _parent_id = #{id}")
    int getCommentNumById(String id);


    /**
     * 根据 id 从 _like 表中获取点赞数
     * @param id id
     * @return int
     */
    @Select("SELECT count(id) FROM _like WHERE id = #{id}")
    int getLikeNumById(@Param("id") String id);

    /**
     * 删除 _comment 表中的数据
     * @param id id
     * @param isParent true: id 为 _parent_id（删除某个一级评论及其子评论），false：id 为 _id（只删除某个二级评论）
     */
    @DeleteProvider(value = CommentMapperProvider.class, method = "deleteComment")
    void deleteComment(String id, boolean isParent);

    /**
     * 根据 AttachedId 删除 _comment 表中的数据
     * @param attachedId attachedId
     */
    @Delete("DELETE FROM _comment WHERE _attached_id = #{attacheId}")
    void deleteCommentByAttachedId(@Param("attacheId") String attachedId);
}
