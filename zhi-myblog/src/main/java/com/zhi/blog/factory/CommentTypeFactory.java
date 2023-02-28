package com.zhi.blog.factory;

import com.zhi.blog.strategy.DeleteType;
import com.zhi.blog.strategy.impl.ArticleCommentStrategy;
import com.zhi.blog.strategy.impl.FriendLinkCommentStrategy;
import com.zhi.blog.strategy.impl.TalkCommentStrategy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.zhi.common.constant.blog.CommonConst.*;

/**
 * @author ftz-lover
 * @version 1.0
 * @date 2023/2/27 21:47
 */
@Service
public class CommentTypeFactory {

    private static final Map<Integer, DeleteType> COMMENT_TYPE_MAP =new HashMap<>();

    static {
        COMMENT_TYPE_MAP.put(ARTICLE_TYPE,new ArticleCommentStrategy());
        COMMENT_TYPE_MAP.put(FRIENDLINK_TYPE,new FriendLinkCommentStrategy());
        COMMENT_TYPE_MAP.put(TALK_TYPE,new TalkCommentStrategy());
    }

    public static void operate(Integer commenttype, Collection<Long> ids){
        COMMENT_TYPE_MAP.get(commenttype).operate(ids);
    }


}
