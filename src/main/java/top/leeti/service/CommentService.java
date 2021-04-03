package top.leeti.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import top.leeti.entity.Comment;

import java.util.List;

public interface CommentService {

    void insert(Comment comment);

    PageInfo<Comment> listCommentsOfMixedData(String attachedId, int pageNum);

    void deleteCommentByParentId(String parentId);

    Comment getCommentById(String id);

    PageInfo<Comment> listCommentsOfRepliedComment(String parentId, int pageNum);

    void deleteCommentById(String id);
}
