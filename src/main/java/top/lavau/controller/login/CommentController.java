package top.lavau.controller.login;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lavau.entity.Comment;
import top.lavau.entity.User;
import top.lavau.entity.result.Result;
import top.lavau.service.CommentService;
import top.lavau.service.OtherTypesService;
import top.lavau.util.UuidUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private OtherTypesService otherTypesService;

    @GetMapping("/app/login/comment/insert")
    public String addCommentOfMixedData(@RequestParam String commentContent, @RequestParam String attachedId) {
        Result<Boolean> result = new Result<>();

        if (otherTypesService.getMixedDataById(attachedId) == null) {
            result.setSuccess(false);
            return JSON.toJSONString(result);
        }

        String id = UuidUtil.acquireUuid();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setPromulgatorId(User.obtainCurrentUser().getStuId());
        comment.setAttachedId(attachedId);
        comment.setParentId(id);
        comment.setContent(commentContent);
        comment.setGmtCreate(new Date());

        commentService.insert(comment);

        log.info("attachedId: {}, id: {}. 添加评论成功", attachedId, id);

        result.setSuccess(true);
        return JSON.toJSONString(result);
    }

    @GetMapping("/app/login/comment/list/typeData")
    public String listCommentsOfMixedData(@RequestParam String attachedId) {
        List<Comment> comments = commentService.listCommentsOfMixedData(attachedId);

        Result<List<Comment>> result = new Result<>();
        result.setData(comments);
        result.setSuccess(true);

        return JSON.toJSONString(result);
    }

    @GetMapping("/app/login/comment/delete/typeData")
    public String deleteCommentOfMixedData(@RequestParam String parentId) {
        Result<List<Comment>> result = new Result<>();

        if (commentService.getCommentById(parentId) == null) {
            result.setSuccess(false);
            return JSON.toJSONString(result);
        }

        commentService.deleteCommentByParentId(parentId);

        result.setSuccess(true);
        return JSON.toJSONString(result);
    }

    @GetMapping("/app/login/comment/reply/insert")
    public String addRepliedComment(@RequestParam String commentContent, @RequestParam String parentId) {
        Result<Boolean> result = new Result<>();

        if (commentService.getCommentById(parentId) == null) {
            result.setSuccess(false);
            return JSON.toJSONString(result);
        }

        String id = UuidUtil.acquireUuid();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setPromulgatorId(User.obtainCurrentUser().getStuId());
        comment.setParentId(parentId);
        comment.setContent(commentContent);
        comment.setGmtCreate(new Date());

        commentService.insert(comment);

        result.setSuccess(true);
        return JSON.toJSONString(result);
    }

    @GetMapping("/app/login/comment/reply/list")
    public String listCommentsOfRepliedComment(@RequestParam String parentId) {
        List<Comment> comments = commentService.listCommentsOfRepliedComment(parentId);

        Result<List<Comment>> result = new Result<>();
        result.setData(comments);
        result.setSuccess(true);

        return JSON.toJSONString(result);
    }

    @GetMapping("/app/login/comment/reply/delete")
    public String deleteCommentOfRepliedComment(@RequestParam String id) {
        Result<List<Comment>> result = new Result<>();

        if (commentService.getCommentById(id) == null) {
            result.setSuccess(false);
            return JSON.toJSONString(result);
        }

        commentService.deleteCommentById(id);

        result.setSuccess(true);
        return JSON.toJSONString(result);
    }
}