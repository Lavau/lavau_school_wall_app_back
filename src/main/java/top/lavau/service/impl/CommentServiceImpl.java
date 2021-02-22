package top.lavau.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.dao.CommentMapper;
import top.lavau.entity.Comment;
import top.lavau.entity.User;
import top.lavau.service.CommentService;

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
}
