package com.zhi.blog.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhi.blog.domain.Comment;
import com.zhi.blog.mapper.CommentMapper;
import com.zhi.blog.strategy.DeleteType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import static com.zhi.common.constant.blog.CommonConst.ARTICLE_TYPE;

/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/2/27 21:06
 */
@Service
public class ArticleCommentStrategy implements DeleteType {
    @Resource
    private  CommentMapper  commentMapper;


    /**
     * 文章删除对应评论
     */
    @Override
    public void operate(Collection<Long> ids) {
        ids.forEach( i ->{
            List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getType, ARTICLE_TYPE).eq(Comment::getTopicId,i));
            commentMapper.deleteBatchIds(comments.stream().map(Comment::getId).collect(Collectors.toList()));
        });

    }
}
