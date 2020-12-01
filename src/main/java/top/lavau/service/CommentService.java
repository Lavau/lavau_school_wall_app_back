package top.lavau.service;

import com.github.pagehelper.PageInfo;
import top.lavau.entity.Comment;

/**
 * description: CommentService 服务类接口: 获取评论、添加评论、删除评论
 *
 * @author: Leet
 * @date: 2020-11-13 12:41
 **/
public interface CommentService {

    /**
     * isParent 为 true，根据 id（_attached_id） 获取评论；
     * isParent 为 false，根据 id（_parent_id） 获取评论
     * @param pageNum pageNum
     * @param id id
     * @param isParent isParent
     * @param openId stuId
     * @return PageInfo<Comment>
     */
    PageInfo<Comment> listComment(int pageNum, String id, boolean isParent, String openId);

    /**
     * 根据 id 获取评论
     * @param id id
     * @return Comment
     */
    Comment getCommentById(String id);

    /**
     * 向 _comment 表中添加一条数据
     * @param commentModel commentModel
     */
    void insert(Comment commentModel);

    /**
     * 删除 _comment 表中的数据
     * @param id id
     * @param isParent true: id 为 _parent_id（删除某个一级评论及其子评论），false：id 为 _id（只删除某个二级评论）
     */
    void deleteComment(String id, boolean isParent);

    /**
     * 根据 AttachedId 删除 _comment 表中的数据
     * @param attachedId attachedId
     */
    void deleteCommentByAttachedId(String attachedId);
}
