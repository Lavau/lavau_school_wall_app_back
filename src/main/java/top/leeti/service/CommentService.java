package top.leeti.service;

import top.leeti.entity.Comment;

import java.util.List;

public interface CommentService {

    void insert(Comment comment);

    List<Comment> listCommentsOfMixedData(String attachedId);

    void deleteCommentByParentId(String parentId);

    Comment getCommentById(String id);

    List<Comment> listCommentsOfRepliedComment(String parentId);

    void deleteCommentById(String id);
}
