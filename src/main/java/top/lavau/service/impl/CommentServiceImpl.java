package top.lavau.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lavau.service.CommentService;


import javax.annotation.Resource;
import java.util.List;

/**
 * description: CommentService 接口实现类
 *
 * @author: Leet
 * @date: 2020-11-13 12:43
 **/
@Slf4j
@Service("miniprogramCommentService")
public class CommentServiceImpl implements CommentService {

    @Value("${page.size}")
    private int pageSize;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;

    @Resource
    private MixedDataMapper mixedDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<CommentModel> listComment(int pageNum, String id, boolean isParent, String openId) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentModel> list = commentMapper.listComment(id, isParent);
        PageInfo<CommentModel> pageInfo = new PageInfo<>(list);
        pageInfo.getList().forEach(commentModel -> {
            commentModel.setMine(commentModel.getPromulgatorId().equals(getStuId(openId)));
            commentModel.setLike(mixedDataMapper.isLike(commentModel.getId(), getStuId(openId)));
            commentModel.setLikeNum(commentMapper.getLikeNumById(id));
            commentModel.setCommentNum(commentMapper.getCommentNumById(commentModel.getId()));
            commentModel.setCreateTime(TimeStampUtil.timeStamp(commentModel.getGmtCreate()));
        });
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentModel getCommentById(String id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(CommentModel commentModel) {
        commentMapper.insertComment(commentModel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteComment(String id, boolean isParent) {
        commentMapper.deleteComment(id, isParent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommentByAttachedId(String attachedId) {
        commentMapper.deleteCommentByAttachedId(attachedId);
    }

    /**
     * 获取学号
     * @param openId openId
     * @return 如果查询不到，返回 “0”
     */
    private String getStuId(String openId) {
        UserModel user = userService.findUserByOpenId(openId);
        if (user == null) {
            return "0";
        } else {
            return user.getStuId();
        }
    }
}
