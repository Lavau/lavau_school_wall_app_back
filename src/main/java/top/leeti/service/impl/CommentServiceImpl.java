package top.leeti.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.dao.CommentMapper;
import top.leeti.entity.Comment;
import top.leeti.entity.User;
import top.leeti.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> listCommentsOfMixedData(String attachedId) {
        List<Comment> comments = commentMapper.listCommentsOfMixedData(attachedId);
        comments.forEach(comment -> {
            comment.setCommentNum(commentMapper.getCommentNumByParentId(comment.getId()) - 1);
            comment.setMine(comment.getPromulgatorId().equals(User.obtainCurrentUser().getStuId()));
        });
        return comments;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommentByParentId(String parentId) {
        commentMapper.deleteCommentByParentId(parentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Comment getCommentById(String id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Comment> listCommentsOfRepliedComment(String parentId) {
        List<Comment> comments = commentMapper.listCommentsOfRepliedComment(parentId);
        comments.forEach(comment -> comment.setMine(comment.getPromulgatorId().equals(User.obtainCurrentUser().getStuId())));
        return comments;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommentById(String id) {
        commentMapper.deleteCommentById(id);
    }
}
