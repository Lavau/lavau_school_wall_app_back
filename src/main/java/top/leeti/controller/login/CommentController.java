package top.leeti.controller.login;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.leeti.entity.Comment;
import top.leeti.entity.MixedData;
import top.leeti.entity.User;
import top.leeti.entity.result.Result;
import top.leeti.exception.RecordOfDataBaseNoFoundException;
import top.leeti.service.CommentService;
import top.leeti.service.OtherTypesService;
import top.leeti.util.FileUtil;
import top.leeti.util.TimeStampUtil;
import top.leeti.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private OtherTypesService otherTypesService;

    @PostMapping("/miniprogram/login/comment/insert")
    public String addCommentOfMixedData(@RequestParam String commentContent, @RequestParam String attachedId) {
        if (otherTypesService.getMixedDataById(attachedId) == null) {
            throw new RecordOfDataBaseNoFoundException("id: " + attachedId + ", 该评论依附的主体信息在数据库中不存在");
        } else {
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

            Result<Boolean> result = new Result<>();
            result.setSuccess(true);
            result.setMsg("评论成功");
            return JSON.toJSONString(result);
        }
    }

    @PostMapping("/miniprogram/login/comment/list/typeData")
    public String listCommentsOfMixedData(@RequestParam int pageNum,
                                          @RequestParam String attachedId) {
        PageInfo<Comment> pageInfo = commentService.listCommentsOfMixedData(attachedId, pageNum);
        return retrieveResultOfCommentList(pageInfo);
    }

    @PostMapping("/miniprogram/login/comment/delete/typeData")
    public String deleteCommentOfMixedData(@RequestParam String parentId) {
        if (commentService.getCommentById(parentId) == null) {
            throw new RecordOfDataBaseNoFoundException("id: " + parentId + ", 该主评论信息在数据库中不存在");
        } else {
            commentService.deleteCommentByParentId(parentId);

            Result<List<Comment>> result = new Result<>();
            result.setSuccess(true);
            result.setMsg("评论删除成功");
            return JSON.toJSONString(result);
        }
    }

    @PostMapping("/miniprogram/login/comment/reply/insert")
    public String addRepliedComment(@RequestParam String commentContent, @RequestParam String parentId) {
        if (commentService.getCommentById(parentId) == null) {
            throw new RecordOfDataBaseNoFoundException("id: " + parentId + ", 该回复评论的主体评论信息在数据库中不存在");
        } else {
            String id = UuidUtil.acquireUuid();
            Comment comment = new Comment();
            comment.setId(id);
            comment.setPromulgatorId(User.obtainCurrentUser().getStuId());
            comment.setParentId(parentId);
            comment.setContent(commentContent);
            comment.setGmtCreate(new Date());

            commentService.insert(comment);

            Result<String> result = new Result<>();
            result.setSuccess(true);
            result.setMsg("回复评论成功！");
            return JSON.toJSONString(result);
        }
    }

    @PostMapping("/miniprogram/login/comment/reply/list")
    public String listCommentsOfRepliedComment(@RequestParam int pageNum,
                                               @RequestParam String parentId) {
        PageInfo<Comment> pageInfo = commentService.listCommentsOfRepliedComment(parentId, pageNum);
        return retrieveResultOfCommentList(pageInfo);
    }

    private String retrieveResultOfCommentList(PageInfo<Comment> pageInfo) {
        pageInfo.getList().forEach(comment -> {
            comment.setCreateTime(TimeStampUtil.timeStamp(comment.getGmtCreate()));
        });
        Result.MyPage<Comment> page = new Result.MyPage<>(pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getList());
        Result<Result.MyPage<Comment>> result = new Result<>(null, null, page, null);
        return JSON.toJSONString(result);
    }

    @PostMapping("/miniprogram/login/comment/reply/delete")
    public String deleteCommentOfRepliedComment(@RequestParam String id) {
        if (commentService.getCommentById(id) == null) {
            throw new RecordOfDataBaseNoFoundException("id: " + id + ", 该回复评论在数据库中不存在");
        } else {
            commentService.deleteCommentById(id);
            Result<String> result = new Result<>();
            result.setSuccess(true);
            result.setMsg("删除评论成功！");
            return JSON.toJSONString(result);
        }
    }
}