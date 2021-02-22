package top.lavau.service;

import top.lavau.entity.Comment;

import java.util.List;

public interface CommentService {

    void insert(Comment comment);

    List<Comment> listCommentsOfMixedData(String attachedId);

    void deleteCommentByParentId(String parentId);

    Comment getCommentById(String id);
}
